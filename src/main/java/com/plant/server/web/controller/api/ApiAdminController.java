package com.plant.server.web.controller.api;

import com.plant.server.business.services.admin.AdminService;
import com.plant.server.business.services.admin.cos.AdminCO;
import com.plant.server.util.collection.Chunk;
import com.plant.server.util.form.FormValidator;
import com.plant.server.web.controller.api.form.AdminForm;
import com.plant.server.web.controller.api.form.register.RegisterAdminForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(ApiURL.BASE + ApiURL.PUBLIC)
@Slf4j
public class ApiAdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping(path = ApiURL.ADMINS, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_0)
    public ResponseEntity<Chunk<AdminCO>> getAdmins(@RequestParam(name = ApiURL.PAGE_PARAM) Optional<Integer> page) {
        return ResponseEntity.ok().body(this.adminService.getAdmins(page));
    }

    @GetMapping(path = ApiURL.ADMINS + ApiURL.SLASH + ApiURL.VAR_INI + ApiURL.VAR_ID + ApiURL.VAR_END, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_0)
    public ResponseEntity<AdminCO> getAdminById(@PathVariable(ApiURL.VAR_ID) Long adminId) {
        return ResponseEntity.ok().body(this.adminService.getAdmin(adminId));
    }


    @PatchMapping(path = ApiURL.ADMINS + ApiURL.SLASH + ApiURL.VAR_INI + ApiURL.VAR_ID + ApiURL.VAR_END, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_0)
    public ResponseEntity<AdminCO> updateAdmin(@PathVariable(ApiURL.VAR_ID) Long adminId, @RequestBody AdminForm adminForm) {
        FormValidator.validate(adminForm);
        return ResponseEntity.ok().body(this.adminService.updateAdmin(adminId, adminForm));
    }

    @DeleteMapping(path = ApiURL.ADMINS + ApiURL.SLASH + ApiURL.VAR_INI + ApiURL.VAR_ID + ApiURL.VAR_END, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_0)
    public ResponseEntity<Void> deleteAdmin(@PathVariable(ApiURL.VAR_ID) Long adminId) {
        return ResponseEntity.ok().body(this.adminService.deleteAdmin(adminId));
    }

    @PostMapping(path = ApiURL.REGISTER + ApiURL.ADMIN, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_0)
    public ResponseEntity<AdminCO> registerAdmin(HttpServletRequest request, @RequestBody RegisterAdminForm registerAdminForm) {
        FormValidator.validate(registerAdminForm);
        return ResponseEntity.ok().body(this.adminService.registerAdmin(registerAdminForm));
    }

}
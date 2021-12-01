package com.plant.server.business.services.admin;

import com.plant.server.business.services.admin.cos.AdminCO;
import com.plant.server.util.collection.Chunk;
import com.plant.server.web.controller.api.form.AdminForm;
import com.plant.server.web.controller.api.form.register.RegisterAdminForm;

import java.util.Optional;

public interface AdminService {

    AdminCO registerAdmin(RegisterAdminForm adminForm);

    Chunk<AdminCO> getAdmins(Optional<Integer> page);

    AdminCO getAdmin(Long adminId);

    AdminCO updateAdmin(Long adminId, AdminForm adminForm);

    Void deleteAdmin(Long adminId);

}

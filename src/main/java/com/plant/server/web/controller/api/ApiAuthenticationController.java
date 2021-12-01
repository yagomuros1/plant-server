package com.plant.server.web.controller.api;

import com.plant.server.business.services.admin.AdminService;
import com.plant.server.business.services.userservice.UserService;
import com.plant.server.util.form.FormValidator;
import com.plant.server.util.jwt.AuthRefreshTokenRequest;
import com.plant.server.util.jwt.TokenCO;
import com.plant.server.web.controller.api.form.AuthLoginPasswordForm;
import com.plant.server.web.controller.api.form.register.RegisterMediatorForm;
import com.plant.server.web.controller.api.form.user.AuthChangePasswordForm;
import com.plant.server.web.controller.api.form.user.AuthChangePasswordRequestForm;
import com.plant.server.web.spring.UserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.AbstractMap;

@CrossOrigin
@RestController
@RequestMapping(ApiURL.BASE + ApiURL.AUTH)
@Slf4j
public class ApiAuthenticationController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @PostMapping(value = ApiURL.LOGIN, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_0)
    public ResponseEntity<TokenCO> authByUsernameAndPassword(@RequestBody AuthLoginPasswordForm authLoginPasswordRequest) {
        return ResponseEntity.ok(this.userDetailsService.checkCredentialsAndGetTokens(authLoginPasswordRequest.getEmail(), authLoginPasswordRequest.getPassword()));
    }

    @PostMapping(value = ApiURL.REFRESH_TOKEN, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_0)
    public ResponseEntity<TokenCO> authByRefreshToken(@RequestBody AuthRefreshTokenRequest authenticationRequest) {
        return ResponseEntity.ok(this.userDetailsService.checkCredentialsAndGetTokens(authenticationRequest.getRefresh_token()));
    }

    @PostMapping(path = ApiURL.CHANGE_PASSWORD_REQUEST, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_0)
    public ResponseEntity<AbstractMap.SimpleEntry<String, String>> requestChangePasswordEmail(@RequestBody AuthChangePasswordRequestForm authChangePasswordRequestForm) {
        this.userService.requestChangePasswordEmail(authChangePasswordRequestForm);
        return ResponseEntity.ok().body(new AbstractMap.SimpleEntry<>("sent", Boolean.TRUE.toString()));
    }

    @PostMapping(path = ApiURL.CHANGE_PASSWORD, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_0)
    public ResponseEntity<AbstractMap.SimpleEntry<String, String>> changePassword(@RequestBody AuthChangePasswordForm authChangePasswordForm) {
        this.userService.changePassword(authChangePasswordForm);
        return ResponseEntity.ok().body(new AbstractMap.SimpleEntry<>("changed", Boolean.TRUE.toString()));
    }

}
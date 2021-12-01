package com.plant.server.business.services.userservice;

import com.plant.server.web.controller.api.form.user.AuthChangePasswordForm;
import com.plant.server.web.controller.api.form.user.AuthChangePasswordRequestForm;

public interface UserService {

    void requestChangePasswordEmail(AuthChangePasswordRequestForm authChangePasswordRequestForm);

    void changePassword(AuthChangePasswordForm authChangePasswordForm);

}

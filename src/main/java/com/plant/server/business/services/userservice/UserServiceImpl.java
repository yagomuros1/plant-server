package com.plant.server.business.services.userservice;

import com.plant.server.business.entities.user.User;
import com.plant.server.business.entities.user.UserRepository;
import com.plant.server.business.services.exceptions.ApiRuntimeException;
import com.plant.server.business.services.exceptions.InstanceNotFoundException;
import com.plant.server.commons.properties.CommonProperties;
import com.plant.server.util.crypto.CryptUtil;
import com.plant.server.util.date.DateUtil;
import com.plant.server.util.email.EmailSender;
import com.plant.server.util.form.FormValidator;
import com.plant.server.web.controller.api.ApiErrorCodes;
import com.plant.server.web.controller.api.form.user.AuthChangePasswordForm;
import com.plant.server.web.controller.api.form.user.AuthChangePasswordRequestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private UserServiceHelper userServiceHelper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CommonProperties commonProperties;

    @Override
    public void requestChangePasswordEmail(AuthChangePasswordRequestForm authChangePasswordRequestForm) {
        try {
            FormValidator.validate(authChangePasswordRequestForm);
            String email = authChangePasswordRequestForm.getEmail();
            User user = this.userServiceHelper.findUserAndCheckNullAndState(email);
            String link = this.userServiceHelper.getChangePasswordUrl(user.getEmail());
            this.emailSender.sendChangePasswordEmail(user.getEmail(), link, Locale.getDefault());
        } catch (InstanceNotFoundException | IOException e) {
            // if not found, we return true as if everything went ok in order to avoid that a malicious user is able to get database emails
        }
    }

    @Override
    public void changePassword(AuthChangePasswordForm authChangePasswordForm) {
        try {
            FormValidator.validate(authChangePasswordForm);

            String hashCodeGenerated = CryptUtil.generateHash(this.commonProperties.getPrivateKeyHash(), authChangePasswordForm.getMilliseconds() + authChangePasswordForm.getKey());

            if (!CryptUtil.checkHashed(authChangePasswordForm.getCode(), hashCodeGenerated)) {
                throw new ApiRuntimeException(ApiErrorCodes.BAD_REQUEST, "URL has been modified");
            }

            long secondsSinceResponse = (DateUtil.getNow().getTimeInMillis() - authChangePasswordForm.getMilliseconds()) / 1000;

            if (secondsSinceResponse > this.commonProperties.getSecondsToExpireLink()) {
                throw new ApiRuntimeException(ApiErrorCodes.TIME_EXPIRED, "Time expired");
            }

            if (!authChangePasswordForm.getPassword().equals(authChangePasswordForm.getRepeatPassword())) {
                throw new ApiRuntimeException(ApiErrorCodes.INVALID_PASSWORD, "Passwords do not match");
            }

            User user = this.userRepository.findByEmail(authChangePasswordForm.getEmail());

            user.setPassword(this.passwordEncoder.encode(authChangePasswordForm.getPassword()));

            this.userRepository.save(user);

            this.emailSender.sendPasswordChanged(user.getEmail(), Locale.getDefault());

        } catch (InstanceNotFoundException | IOException e) {
            // if not found, we return true as if everything went ok in order to avoid that a malicious user is able to get database emails
        }
    }

}

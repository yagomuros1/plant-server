package com.plant.server.web.controller.api.form.user;

import com.plant.server.business.entities.user.UserConstants;
import com.plant.server.web.controller.api.form.GenericForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthChangePasswordForm implements Serializable, GenericForm, AuthCodeForm {

    @NotNull
    @NotEmpty
    @Size(max = UserConstants.EMAIL_MAX_LENGTH)
    private String email;
    @NotNull
    @Positive
    private Long milliseconds;
    @NotNull
    @NotEmpty
    private String code;
    @NotNull
    @NotEmpty
    @Size(max = UserConstants.PASSWORD_MAX_LENGTH)
    private String password;
    @NotNull
    @NotEmpty
    @Size(max = UserConstants.PASSWORD_MAX_LENGTH)
    private String repeatPassword;

    @Override
    public String getKey() {
        return this.email;
    }

}
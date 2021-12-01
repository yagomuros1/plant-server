package com.plant.server.web.controller.api.form.user;

import com.plant.server.business.entities.user.UserConstants;
import com.plant.server.web.controller.api.form.GenericForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthChangePasswordRequestForm implements Serializable, GenericForm {

    @NotNull
    @NotEmpty
    @Size(max = UserConstants.EMAIL_MAX_LENGTH)
    private String email;

}

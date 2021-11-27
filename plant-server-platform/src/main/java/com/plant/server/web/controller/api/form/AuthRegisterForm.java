package com.plant.server.web.controller.api.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRegisterForm implements Serializable, GenericForm {

    private static final long serialVersionUID = -4760749464913100794L;

    @NotNull
    @NotEmpty
    @Email
    private String email;
    @NotNull @NotEmpty
    private String password;

}
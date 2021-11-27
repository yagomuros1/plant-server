package com.plant.server.web.controller.api.form.register;

import com.plant.server.web.controller.api.form.GenericForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RegisterMediatorForm implements Serializable, GenericForm {

    @NotNull
    @NotEmpty
    protected String email;

    @NotNull
    @NotEmpty
    protected String password;

    protected String name;

    protected String firstName;

    protected String secondName;

    protected String passport;

    protected String phone;

    protected String iban;

    protected String address;

    protected String refererEmail;

    @NotNull
    protected MultipartFile sepaFile;
    @NotNull
    @NotEmpty
    protected String sepaDescription;

    @NotNull
    protected MultipartFile certFile;
    @NotNull
    @NotEmpty
    protected String certDescription;

    @NotNull
    protected MultipartFile policyFile;
    @NotNull
    @NotEmpty
    protected String policyDescription;

    @NotNull
    protected MultipartFile officialRegisterFile;
    @NotNull
    @NotEmpty
    protected String officialRegisterDescription;

    @NotNull
    protected MultipartFile penalFile;
    @NotNull
    @NotEmpty
    protected String penalDescription;

    @NotNull
    protected MultipartFile skillsFile;
    @NotNull
    @NotEmpty
    protected String skillsDescription;

}

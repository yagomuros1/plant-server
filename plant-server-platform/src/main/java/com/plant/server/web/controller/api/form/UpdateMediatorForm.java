package com.plant.server.web.controller.api.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UpdateMediatorForm implements Serializable, GenericForm {

    protected String name;

    protected String firstName;

    protected String secondName;

    protected String passport;

    protected String phone;

    protected String iban;

    protected String address;

    protected String refererEmail;

    protected Boolean isActive;

    protected Boolean isMediatedEnable;

    protected Boolean hasPermissions;

    protected MultipartFile sepaFile;

    protected String sepaDescription;

    protected MultipartFile certFile;

    protected String certDescription;

    protected MultipartFile policyFile;

    protected String policyDescription;

    protected MultipartFile officialRegisterFile;

    protected String officialRegisterDescription;

    protected MultipartFile penalFile;

    protected String penalDescription;

    protected MultipartFile skillsFile;

    protected String skillsDescription;

}
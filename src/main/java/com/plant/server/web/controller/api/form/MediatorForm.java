package com.plant.server.web.controller.api.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediatorForm implements Serializable, GenericForm {

    private String name;

    private String firstName;

    private String secondName;

    private String passport;

    private String phone;

    private String iban;

    private String address;

    private Boolean isActive;

    private Boolean isMediatedEnable;

    private Boolean hasPermissions;

    private Long refererId;

}
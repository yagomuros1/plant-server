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
public class AdminForm implements Serializable, GenericForm {

    private String name;

    private String firstName;

    private String secondName;

}
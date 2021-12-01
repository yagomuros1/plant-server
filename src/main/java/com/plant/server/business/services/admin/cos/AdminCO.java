package com.plant.server.business.services.admin.cos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AdminCO implements Serializable {

    protected Long id;

    protected String email;

    protected String name;

    protected String firstName;

    protected String secondName;

}
package com.plant.server.business.services.companion.cos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CompanionCO implements Serializable {

    protected Long externalId;

    protected String name;

    protected String image;

}
package com.plant.server.business.services.property.cos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PropertyCO implements Serializable {

    protected Long id;

    protected String title;

    protected String subtitle;

    protected String value;

}
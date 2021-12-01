package com.plant.server.business.services.test.cos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestCO implements Serializable {

    private static final long serialVersionUID = 9135960993167948332L;

    private String test;

}

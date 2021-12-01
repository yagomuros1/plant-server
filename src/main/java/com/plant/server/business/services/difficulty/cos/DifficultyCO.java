package com.plant.server.business.services.difficulty.cos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DifficultyCO implements Serializable {

    protected Long id;

    protected String name;

}

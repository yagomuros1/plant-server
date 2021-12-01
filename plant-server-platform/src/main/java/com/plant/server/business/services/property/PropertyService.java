package com.plant.server.business.services.property;

import com.plant.server.business.services.property.cos.PropertyCO;
import com.plant.server.util.collection.Chunk;

import java.util.Optional;

public interface PropertyService {
    Chunk<PropertyCO> getProperties(Optional<Integer> page);
}

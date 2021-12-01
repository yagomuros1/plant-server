package com.plant.server.business.services.crop;

import com.plant.server.business.services.crop.cos.CropCO;
import com.plant.server.util.collection.Chunk;

import java.util.Optional;

public interface CropService {
    Chunk<CropCO> getCrops(Optional<Integer> page);
}


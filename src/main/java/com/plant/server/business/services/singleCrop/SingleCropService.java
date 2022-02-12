package com.plant.server.business.services.singleCrop;

import com.plant.server.business.services.singleCrop.cos.SingleCropCO;
import com.plant.server.util.collection.Chunk;

import java.util.List;
import java.util.Optional;

public interface SingleCropService {
    Chunk<SingleCropCO> getSingleCrops(Optional<Integer> page);
    List<SingleCropCO> getTopCrops();
}
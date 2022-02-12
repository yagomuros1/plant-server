package com.plant.server.business.services.singleCrop;

import com.plant.server.business.entities.singleCrop.SingleCrop;
import com.plant.server.business.services.singleCrop.cos.SingleCropCO;
import com.plant.server.commons.properties.CommonProperties;
import com.plant.server.util.collection.IterableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingleCropServiceHelper {

    @Autowired
    private CommonProperties commonProperties;

    // Convert SingleCrop in SingleCompanion
    public SingleCropCO toSingleCompanionCropCO(SingleCrop singleCrop) {
        return IterableUtil.to(singleCrop, s -> SingleCropCO.builder()
                .externalId(s.getExternalId())
                .name(s.getName())
                .image(s.getImage())
                .build());
    }

    // Convert SingleCrop in SingleCrop
    public SingleCropCO toSingleCropCO(SingleCrop singleCrop) {
        return IterableUtil.to(singleCrop, s -> SingleCropCO.builder()
                .externalId(s.getExternalId())
                .name(s.getName())
                .image(s.getImage())
                .topPriority(s.getTopPriority())
                .build());
    }

}
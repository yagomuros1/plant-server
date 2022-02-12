package com.plant.server.business.services.companion;

import com.plant.server.business.entities.companion.Companion;
import com.plant.server.business.services.companion.cos.CompanionCO;
import com.plant.server.commons.properties.CommonProperties;
import com.plant.server.util.collection.IterableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanionServiceHelper {

    @Autowired
    private CommonProperties commonProperties;

    public CompanionCO toCompanionCO (Companion companion) {
        return IterableUtil.to(companion, c -> CompanionCO.builder()
                .externalId(c.getExternalId())
                .name(c.getName())
                .image(c.getImage())
                .build());
    }

}
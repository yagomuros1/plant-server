package com.plant.server.business.services.situation;

import com.plant.server.business.entities.difficulty.Difficulty;
import com.plant.server.business.entities.situation.Situation;
import com.plant.server.business.services.difficulty.cos.DifficultyCO;
import com.plant.server.business.services.situation.cos.SituationCO;
import com.plant.server.commons.properties.CommonProperties;
import com.plant.server.util.collection.IterableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SituationServiceHelper {

    @Autowired
    private CommonProperties commonProperties;

    public SituationCO toSituationCO (Situation situation) {
        return IterableUtil.to(situation, a -> SituationCO.builder()
                .id(a.getId())
                .name(a.getName())
                .build());
    }

}
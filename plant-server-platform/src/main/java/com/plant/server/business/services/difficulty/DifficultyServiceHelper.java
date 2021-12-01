package com.plant.server.business.services.difficulty;

import com.plant.server.business.entities.difficulty.Difficulty;
import com.plant.server.business.services.difficulty.cos.DifficultyCO;
import com.plant.server.commons.properties.CommonProperties;
import com.plant.server.util.collection.IterableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DifficultyServiceHelper {

    @Autowired
    private CommonProperties commonProperties;

    public DifficultyCO toDifficultyCO (Difficulty difficulty) {
        return IterableUtil.to(difficulty, a -> DifficultyCO.builder()
                .id(a.getId())
                .name(a.getName())
                .build());
    }

}


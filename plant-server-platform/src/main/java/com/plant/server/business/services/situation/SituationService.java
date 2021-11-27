package com.plant.server.business.services.situation;

import com.plant.server.business.services.situation.cos.SituationCO;
import com.plant.server.util.collection.Chunk;

import java.util.Optional;

public interface SituationService {
    Chunk<SituationCO> getSituations(Optional<Integer> page);
}

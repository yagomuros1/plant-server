package com.plant.server.business.services.companion;

import com.plant.server.business.services.companion.cos.CompanionCO;
import com.plant.server.util.collection.Chunk;

import java.util.Optional;

public interface CompanionService {
    Chunk<CompanionCO> getCompanions(Optional<Integer> page);
}
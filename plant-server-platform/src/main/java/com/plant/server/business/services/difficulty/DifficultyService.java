package com.plant.server.business.services.difficulty;

import com.plant.server.business.services.difficulty.cos.DifficultyCO;
import com.plant.server.util.collection.Chunk;
import java.util.Optional;

public interface DifficultyService {
    Chunk<DifficultyCO> getDifficulties(Optional<Integer> page);
}

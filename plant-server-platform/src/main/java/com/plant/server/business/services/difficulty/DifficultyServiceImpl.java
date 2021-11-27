package com.plant.server.business.services.difficulty;

import com.plant.server.business.entities.difficulty.Difficulty;
import com.plant.server.business.entities.difficulty.DifficultyRepository;
import com.plant.server.business.services.difficulty.cos.DifficultyCO;
import com.plant.server.commons.properties.CommonProperties;
import com.plant.server.util.collection.Chunk;
import com.plant.server.util.collection.IterableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DifficultyServiceImpl implements DifficultyService {

    @Autowired
    private DifficultyRepository difficultyRepository;

    @Autowired
    private CommonProperties commonProperties;

    @Override
    public Chunk<DifficultyCO> getDifficulties(Optional<Integer> page) {
        int pageReceived;

        Integer pageConversion = IterableUtil.getRequestedPage(page);
        pageReceived = IterableUtil.getPageToRequestToRepositoryAndCheck(pageConversion);

        return IterableUtil.toChunk(
                IterableUtil.toStream(this.difficultyRepository.findAll())
                        .map(this::toDifficultyCO)
                        .collect(Collectors.toList()), this.difficultyRepository.countAll(), pageConversion, this.commonProperties.getDefaultChunkSize());

    }

    public DifficultyCO toDifficultyCO (Difficulty difficulty) {
        return IterableUtil.to(difficulty, a -> DifficultyCO.builder()
                .id(a.getId())
                .name(a.getName())
                .build());
    }

}

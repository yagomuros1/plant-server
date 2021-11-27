package com.plant.server.business.services.situation;

import com.plant.server.business.entities.situation.Situation;
import com.plant.server.business.entities.situation.SituationRepository;
import com.plant.server.business.services.situation.cos.SituationCO;
import com.plant.server.commons.properties.CommonProperties;
import com.plant.server.util.collection.Chunk;
import com.plant.server.util.collection.IterableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SituationServiceImpl implements SituationService {

    @Autowired
    private SituationRepository situationRepository;

    @Autowired
    private CommonProperties commonProperties;

    @Override
    public Chunk<SituationCO> getSituations(Optional<Integer> page) {
        int pageReceived;

        Integer pageConversion = IterableUtil.getRequestedPage(page);
        pageReceived = IterableUtil.getPageToRequestToRepositoryAndCheck(pageConversion);

        return IterableUtil.toChunk(
                IterableUtil.toStream(this.situationRepository.findAll())
                        .map(this::toSituationCO)
                        .collect(Collectors.toList()), this.situationRepository.countAll(), pageConversion, this.commonProperties.getDefaultChunkSize());

    }

    public SituationCO toSituationCO (Situation situation) {
        return IterableUtil.to(situation, a -> SituationCO.builder()
                .id(a.getId())
                .name(a.getName())
                .build());
    }

}
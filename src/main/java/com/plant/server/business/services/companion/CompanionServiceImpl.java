package com.plant.server.business.services.companion;

import com.plant.server.business.entities.category.CategoryRepository;
import com.plant.server.business.entities.companion.CompanionRepository;
import com.plant.server.business.services.category.CategoryService;
import com.plant.server.business.services.category.CategoryServiceHelper;
import com.plant.server.business.services.category.cos.CategoryCO;
import com.plant.server.business.services.companion.cos.CompanionCO;
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
public class CompanionServiceImpl implements CompanionService {

    @Autowired
    private CompanionRepository companionRepository;

    @Autowired
    private CompanionServiceHelper companionServiceHelper;

    @Autowired
    private CommonProperties commonProperties;

    @Override
    public Chunk<CompanionCO> getCompanions(Optional<Integer> page) {
        int pageReceived;

        Integer pageConversion = IterableUtil.getRequestedPage(page);
        pageReceived = IterableUtil.getPageToRequestToRepositoryAndCheck(pageConversion);

        return IterableUtil.toChunk(
                IterableUtil.toStream(this.companionRepository.findAll())
                        .map(companion -> companionServiceHelper.toCompanionCO(companion))
                        .collect(Collectors.toList()), this.companionRepository.countAll(), pageConversion, this.commonProperties.getDefaultChunkSize());

    }

}

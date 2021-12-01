package com.plant.server.business.services.crop;

import com.plant.server.business.entities.crop.Crop;
import com.plant.server.business.entities.crop.CropRepository;
import com.plant.server.business.services.category.CategoryServiceHelper;
import com.plant.server.business.services.crop.cos.CropCO;
import com.plant.server.business.services.difficulty.DifficultyServiceHelper;
import com.plant.server.business.services.property.PropertyServiceHelper;
import com.plant.server.business.services.situation.SituationServiceHelper;
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
public class CropServiceImpl implements CropService {

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private DifficultyServiceHelper difficultyServiceHelper;

    @Autowired
    private SituationServiceHelper situationServiceHelper;

    @Autowired
    private CategoryServiceHelper categoryServiceHelper;

    @Autowired
    private PropertyServiceHelper propertyServiceHelper;

    @Autowired
    private CommonProperties commonProperties;

    @Override
    public Chunk<CropCO> getCrops(Optional<Integer> page) {
        int pageReceived;

        Integer pageConversion = IterableUtil.getRequestedPage(page);
        pageReceived = IterableUtil.getPageToRequestToRepositoryAndCheck(pageConversion);

        return IterableUtil.toChunk(
                IterableUtil.toStream(this.cropRepository.findAll())
                        .map(this::toCropCO)
                        .collect(Collectors.toList()), this.cropRepository.countAll(), pageConversion, this.commonProperties.getDefaultChunkSize());

    }

    public CropCO toCropCO (Crop crop) {
        return IterableUtil.to(crop, a -> CropCO.builder()
                .id(a.getId())
                .name(a.getName())
                .description(a.getDescription())
                .image(a.getImage())
                .categories(a.getCategories().stream().map(category -> this.categoryServiceHelper.toCategoryCO(category)).collect(Collectors.toList()))
                .properties(a.getProperties().stream().map(property -> this.propertyServiceHelper.toPropertyCO(property)).collect(Collectors.toList()))
                .conservation(a.getConservation())
                .difficulty(difficultyServiceHelper.toDifficultyCO(a.getDifficulty()))
                .situation(situationServiceHelper.toSituationCO(a.getSituation()))
                .build());
    }

}
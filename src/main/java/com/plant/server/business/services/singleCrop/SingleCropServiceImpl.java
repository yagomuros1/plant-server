package com.plant.server.business.services.singleCrop;

import com.plant.server.business.entities.singleCrop.SingleCropRepository;
import com.plant.server.business.services.singleCrop.cos.SingleCropCO;
import com.plant.server.commons.properties.CommonProperties;
import com.plant.server.util.collection.Chunk;
import com.plant.server.util.collection.IterableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SingleCropServiceImpl implements SingleCropService {

    @Autowired
    private SingleCropRepository singleCropRepository;

    @Autowired
    private SingleCropServiceHelper singleCropServiceHelper;

    @Autowired
    private CommonProperties commonProperties;

    @Override
    public Chunk<SingleCropCO> getSingleCrops(Optional<Integer> page) {
        int pageReceived;

        Integer pageConversion = IterableUtil.getRequestedPage(page);
        pageReceived = IterableUtil.getPageToRequestToRepositoryAndCheck(pageConversion);

        return IterableUtil.toChunk(
                IterableUtil.toStream(this.singleCropRepository.findAll())
                        .map(singleCrop -> singleCropServiceHelper.toSingleCropCO(singleCrop))
                        .collect(Collectors.toList()), this.singleCropRepository.countAll(), pageConversion, this.commonProperties.getDefaultChunkSize());

    }

    @Override
    public List<SingleCropCO> getTopCrops() {
        return this.singleCropRepository.getTopCrops().stream()
                .map(topCrops -> singleCropServiceHelper.toSingleCropCO(topCrops)).collect(Collectors.toList());
    }

}

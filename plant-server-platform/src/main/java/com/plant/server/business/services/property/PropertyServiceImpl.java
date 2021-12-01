package com.plant.server.business.services.property;

import com.plant.server.business.entities.property.PropertyRepository;
import com.plant.server.business.services.property.cos.PropertyCO;
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
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyServiceHelper propertyServiceHelper;

    @Autowired
    private CommonProperties commonProperties;

    @Override
    public Chunk<PropertyCO> getProperties(Optional<Integer> page) {
        int pageReceived;

        Integer pageConversion = IterableUtil.getRequestedPage(page);
        pageReceived = IterableUtil.getPageToRequestToRepositoryAndCheck(pageConversion);

        return IterableUtil.toChunk(
                IterableUtil.toStream(this.propertyRepository.findAll())
                        .map(property -> propertyServiceHelper.toPropertyCO(property))
                        .collect(Collectors.toList()), this.propertyRepository.countAll(), pageConversion, this.commonProperties.getDefaultChunkSize());

    }

}

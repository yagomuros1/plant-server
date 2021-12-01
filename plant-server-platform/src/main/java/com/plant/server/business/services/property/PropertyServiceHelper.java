package com.plant.server.business.services.property;

import com.plant.server.business.entities.property.Property;
import com.plant.server.business.services.property.cos.PropertyCO;
import com.plant.server.commons.properties.CommonProperties;
import com.plant.server.util.collection.IterableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceHelper {

    @Autowired
    private CommonProperties commonProperties;

    public PropertyCO toPropertyCO (Property property) {
        return IterableUtil.to(property, p -> PropertyCO.builder()
                .id(p.getId())
                .title(p.getTitle())
                .subtitle(p.getSubtitle())
                .value(p.getValue())
                .build());
    }

}
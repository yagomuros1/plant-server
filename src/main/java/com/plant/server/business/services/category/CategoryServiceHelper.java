package com.plant.server.business.services.category;

import com.plant.server.business.entities.category.Category;
import com.plant.server.business.services.category.cos.CategoryCO;
import com.plant.server.commons.properties.CommonProperties;
import com.plant.server.util.collection.IterableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceHelper {

    @Autowired
    private CommonProperties commonProperties;

    public CategoryCO toCategoryCO (Category category) {
        return IterableUtil.to(category, a -> CategoryCO.builder()
                .id(a.getId())
                .name(a.getName())
                .build());
    }

}
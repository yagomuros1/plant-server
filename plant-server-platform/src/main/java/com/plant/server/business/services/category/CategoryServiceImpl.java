package com.plant.server.business.services.category;

import com.plant.server.business.entities.category.Category;
import com.plant.server.business.entities.category.CategoryRepository;
import com.plant.server.business.services.category.cos.CategoryCO;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommonProperties commonProperties;

    @Override
    public Chunk<CategoryCO> getCategories(Optional<Integer> page) {
        int pageReceived;

        Integer pageConversion = IterableUtil.getRequestedPage(page);
        pageReceived = IterableUtil.getPageToRequestToRepositoryAndCheck(pageConversion);

        return IterableUtil.toChunk(
                IterableUtil.toStream(this.categoryRepository.findAll())
                        .map(this::toCategoryCO)
                        .collect(Collectors.toList()), this.categoryRepository.countAll(), pageConversion, this.commonProperties.getDefaultChunkSize());

    }

    public CategoryCO toCategoryCO (Category category) {
        return IterableUtil.to(category, a -> CategoryCO.builder()
                .id(a.getId())
                .name(a.getName())
                .build());
    }

}

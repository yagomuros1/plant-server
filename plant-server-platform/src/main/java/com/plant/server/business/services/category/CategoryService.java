package com.plant.server.business.services.category;

import com.plant.server.business.services.category.cos.CategoryCO;
import com.plant.server.util.collection.Chunk;
import java.util.Optional;

public interface CategoryService {
    Chunk<CategoryCO> getCategories(Optional<Integer> page);
}

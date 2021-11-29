package com.plant.server.business.entities.crop;

import com.plant.server.business.entities.category.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CropRepository extends CrudRepository<Crop, Long> {

    Crop findByName(String name);

    @Query("select c from Crop c")
    List<Crop> findAll(Pageable pageable);

    @Query("select c from Crop c")
    List<Crop> findAll();

    @Query("select count(c) from Crop c")
    long countAll();

}
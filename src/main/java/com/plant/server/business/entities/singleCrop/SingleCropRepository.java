package com.plant.server.business.entities.singleCrop;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SingleCropRepository extends CrudRepository<SingleCrop, Long> {

    SingleCrop findByName(String name);

    @Query("select c from SingleCrop c")
    List<SingleCrop> findAll(Pageable pageable);

    @Query("select c from SingleCrop c")
    List<SingleCrop> findAll();

    @Query("select c from SingleCrop c where topPriority >= 50 order by topPriority DESC")
    List<SingleCrop> getTopCrops();

    @Query("select count(u) from SingleCrop u")
    long countAll();

}
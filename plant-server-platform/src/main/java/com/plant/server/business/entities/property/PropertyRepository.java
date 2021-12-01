package com.plant.server.business.entities.property;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PropertyRepository extends CrudRepository<Property, Long> {

    Property findByTitle(String name);

    @Query("select p from Property p")
    List<Property> findAll(Pageable pageable);

    @Query("select count(p) from Property p")
    long countAll();

}
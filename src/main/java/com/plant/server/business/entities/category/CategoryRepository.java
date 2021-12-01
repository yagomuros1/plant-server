package com.plant.server.business.entities.category;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findByName(String name);

    @Query("select c from Category c")
    List<Category> findAll(Pageable pageable);

    @Query("select c from Category c")
    List<Category> findAll();

    @Query("select count(u) from Category u")
    long countAll();

}
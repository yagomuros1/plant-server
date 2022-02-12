package com.plant.server.business.entities.companion;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanionRepository extends CrudRepository<Companion, Long> {

    Companion findByName(String name);

    @Query("select c from Companion c")
    List<Companion> findAll(Pageable pageable);

    @Query("select c from Companion c")
    List<Companion> findAll();

    @Query("select count(u) from Companion u")
    long countAll();

}
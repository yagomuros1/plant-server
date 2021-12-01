package com.plant.server.business.entities.situation;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SituationRepository extends CrudRepository<Situation, Long> {

    Situation findByName(String name);

    @Query("select s from Situation s")
    List<Situation> findAll(Pageable pageable);

    @Query("select s from Situation s")
    List<Situation> findAll();

    @Query("select count(u) from Situation u")
    long countAll();

}
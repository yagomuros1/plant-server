package com.plant.server.business.entities.difficulty;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface DifficultyRepository extends CrudRepository<Difficulty, Long> {

    Difficulty findByName(String name);

    @Query("select d from Difficulty d")
    List<Difficulty> findAll(Pageable pageable);

    @Query("select d from Difficulty d")
    List<Difficulty> findAll();

    @Query("select count(u) from Difficulty u")
    long countAll();

}

package com.plant.server.business.entities.admin;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminRepository extends CrudRepository<Admin, Long> {

    Admin findByEmailIgnoreCase(String username);

    Admin findByEmail(String email);

    @Query("select m from Admin m")
    List<Admin> findAll(Pageable pageable);

    @Query("select m from Admin m")
    List<Admin> findAll();

    @Query("select count(a) from Admin a")
    long countAll();

}
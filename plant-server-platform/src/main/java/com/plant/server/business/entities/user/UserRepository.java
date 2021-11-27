package com.plant.server.business.entities.user;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailIgnoreCase(String username);

    User findByEmail(String email);

    @Query("select u from User u")
    List<User> findAll(Pageable pageable);

    @Query("select count(u) from User u")
    long countAll();

}
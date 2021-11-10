package com.example.botscrew_testtask.repository;

import com.example.botscrew_testtask.domain.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    List<Lector> findAllByNameContainsOrLastNameContains(String name, String lastName);

}

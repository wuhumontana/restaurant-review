package com.ex.base.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ex.base.entity.Registrar;

@Repository
public interface RegistrarRepository extends CrudRepository<Registrar, Long> {

    @Query(value = "SELECT r FROM Registrar r where r.courseNumber = ?1")

    Optional<Registrar> findCourseById(Long id);
}
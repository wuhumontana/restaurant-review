package com.ex.base.jpa;

import com.ex.base.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiUserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}

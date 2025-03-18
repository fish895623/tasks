package com.github.fish895623.tasks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.fish895623.tasks.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
}

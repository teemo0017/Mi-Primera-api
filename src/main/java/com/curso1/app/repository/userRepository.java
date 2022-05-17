package com.curso1.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso1.app.entity.User;

@Repository
public interface userRepository extends JpaRepository<User, Long> {

}

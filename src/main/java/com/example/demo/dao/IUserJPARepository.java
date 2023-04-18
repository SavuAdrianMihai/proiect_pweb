package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserJPARepository extends JpaRepository<User, Integer> {

    List<User> findByEmailAndPassword(String email, String password);
    Optional<User> findById(int id);
}

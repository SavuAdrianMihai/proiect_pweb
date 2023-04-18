package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILegoSetJPARepository extends JpaRepository<LegoSet, Integer> {
    List<LegoSet> findBySetIdAndName(long setId, String name);
}

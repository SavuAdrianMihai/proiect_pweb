package com.example.demo.dao;

import com.example.demo.dto.LegoSetDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="legoset")
public class LegoSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    private long setId;
    private String name;
    private int numberOfBricks;
    private String collection;
    private String releaseYear;

    public LegoSet(LegoSetDTO legoSetDTO) {
        this.setId = legoSetDTO.getSetId();
        this.name = legoSetDTO.getName();
        this.numberOfBricks = legoSetDTO.getNumberOfBricks();
        this.collection = legoSetDTO.getCollection();
        this.releaseYear = legoSetDTO.getReleaseYear();
    }
}

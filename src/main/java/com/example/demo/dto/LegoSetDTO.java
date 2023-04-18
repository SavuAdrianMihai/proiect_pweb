package com.example.demo.dto;

import com.example.demo.dao.LegoSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LegoSetDTO {
    private long setId;
    private String name;
    private int numberOfBricks;
    private String collection;
    private String releaseYear;

    public LegoSetDTO(LegoSet legoSet) {
        this.setId = legoSet.getSetId();
        this.name = legoSet.getName();
        this.numberOfBricks = legoSet.getNumberOfBricks();
        this.collection = legoSet.getCollection();
        this.releaseYear = legoSet.getReleaseYear();
    }
}

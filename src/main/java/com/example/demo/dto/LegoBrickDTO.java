package com.example.demo.dto;

import com.example.demo.dao.LegoBrick;
import com.example.demo.dao.LegoSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LegoBrickDTO {
    private long brickId;
    private String name;
    private String color;
    private String condition;
    private String rarity;

    public LegoBrickDTO(LegoBrick legoBrick) {
        this.brickId = legoBrick.getBrickId();
        this.name = legoBrick.getName();
        this.color = legoBrick.getColor();
        this.condition = legoBrick.getCondition();
        this.rarity = legoBrick.getRarity();
    }
}

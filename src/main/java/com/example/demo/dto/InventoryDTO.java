package com.example.demo.dto;

import com.example.demo.dao.LegoBrick;
import com.example.demo.dao.LegoSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class InventoryDTO {
    Set<LegoBrick> legoBricks;
    Set<LegoSet> legoSets;
}

package com.example.demo.dto;

import com.example.demo.dao.LegoBrick;
import com.example.demo.dao.LegoSet;
import com.example.demo.dao.Listing;
import com.example.demo.dao.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ListingDTO {
    private String name;
    private String description;
    private double price;
    private User user;
    private Set<LegoBrick> legoBricks;
    private Set<LegoSet> legoSets;

    public ListingDTO(Listing listing) {
        this.name = listing.getName();
        this.description = listing.getDescription();
        this.price = listing.getPrice();
        this.user = listing.getUser();
        this.legoBricks = listing.getLegoBricks();
        this.legoSets = listing.getLegoSets();
    }
}

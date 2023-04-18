package com.example.demo.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name="listing")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private double price;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Listing_LegoSet",
            joinColumns = { @JoinColumn(name = "listing_id") },
            inverseJoinColumns = { @JoinColumn(name = "legoset_id") }
    )
    Set<LegoSet> legoSets = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Listing_LegoBrick",
            joinColumns = { @JoinColumn(name = "listing_id") },
            inverseJoinColumns = { @JoinColumn(name = "legobrick_id") }
    )
    Set<LegoBrick> legoBricks = new HashSet<>();

    public Listing(String name, String description, double price, User user) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.user = user;
    }
}

package com.example.demo.dao;

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
@Table(name="inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Inventory_LegoSet",
            joinColumns = { @JoinColumn(name = "inventory_id") },
            inverseJoinColumns = { @JoinColumn(name = "legoset_id") }
    )
    Set<LegoSet> legoSets = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Inventory_LegoBrick",
            joinColumns = { @JoinColumn(name = "inventory_id") },
            inverseJoinColumns = { @JoinColumn(name = "brick_id") }
    )
    Set<LegoBrick> legoBricks = new HashSet<>();
}

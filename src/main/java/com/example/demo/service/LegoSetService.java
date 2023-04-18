package com.example.demo.service;

import com.example.demo.dao.ILegoSetJPARepository;
import com.example.demo.dao.LegoSet;
import com.example.demo.exceptions.NonexistentLegoSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LegoSetService {
    @Autowired
    ILegoSetJPARepository legoSetRepository;
    public Optional<LegoSet> getLegoSetById(int id) throws NonexistentLegoSet {
        LegoSet lS = legoSetRepository.findById(id).orElse(null);
        if (lS == null) {
            throw new NonexistentLegoSet();
        }
        return legoSetRepository.findById(id);
    }

    public LegoSet createLegoSet(LegoSet legoSet) {
        return legoSetRepository.save(legoSet);
    }

    public void updateLegoSet(LegoSet legoSet) {
        legoSetRepository.save(legoSet);
    }

    public void deleteLegoSet(LegoSet legoSet) {
        legoSetRepository.delete(legoSet);
    }

    public List<LegoSet> getAllLegoSets() {
        return legoSetRepository.findAll();
    }
}

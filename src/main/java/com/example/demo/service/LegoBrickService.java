package com.example.demo.service;

import com.example.demo.dao.ILegoBrickJPARepository;
import com.example.demo.dao.ILegoBrickJPARepository;
import com.example.demo.dao.LegoBrick;
import com.example.demo.dao.LegoBrick;
import com.example.demo.exceptions.NonexistentLegoBrick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LegoBrickService {
    @Autowired
    ILegoBrickJPARepository legoBrickRepository;
    public Optional<LegoBrick> getLegoBrickById(int id) throws NonexistentLegoBrick {
        LegoBrick lS = legoBrickRepository.findById(id).orElse(null);
        if (lS == null) {
            throw new NonexistentLegoBrick();
        }
        return legoBrickRepository.findById(id);
    }

    public LegoBrick createLegoBrick(LegoBrick LegoBrick) {
        return legoBrickRepository.save(LegoBrick);
    }

    public void updateLegoBrick(LegoBrick LegoBrick) {
        legoBrickRepository.save(LegoBrick);
    }

    public void deleteLegoBrick(LegoBrick LegoBrick) {
        legoBrickRepository.delete(LegoBrick);
    }

    public List<LegoBrick> getAllLegoBricks() {
        return legoBrickRepository.findAll();
    }
}

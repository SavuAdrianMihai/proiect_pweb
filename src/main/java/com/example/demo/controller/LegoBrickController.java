package com.example.demo.controller;

import com.example.demo.dao.LegoBrick;
import com.example.demo.dto.LegoBrickDTO;
import com.example.demo.exceptions.NonexistentLegoBrick;
import com.example.demo.service.LegoBrickService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/legobricks")
public class LegoBrickController {
    @Autowired
    private LegoBrickService legoBrickService;

    @GetMapping("/{id}")
    @ApiOperation(value = "finds LegoBrick by id", notes = "prints relevant info about the LegoBrick")
    @ApiResponses({
            @ApiResponse(code=404, message = "No lego set has been found", response = NonexistentLegoBrick.class),
            @ApiResponse(code=200, message = "lego set found", response = LegoBrick.class),
            @ApiResponse(code=500, message = "unkown error", response = Exception.class)
    })
    public ResponseEntity<LegoBrickDTO> getLegoBrickById(@PathVariable("id") int id) throws NonexistentLegoBrick {
        Optional<LegoBrick> optionalLegoBrick = legoBrickService.getLegoBrickById(id);
        if (optionalLegoBrick.isPresent()) {
            LegoBrickDTO LegoBrickDTO = new LegoBrickDTO(optionalLegoBrick.get());
            return ResponseEntity.ok(LegoBrickDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<LegoBrickDTO> createLegoBrick(@RequestBody LegoBrick legoBrick) {
        LegoBrickDTO LegoBrickDTO = new LegoBrickDTO(legoBrick);
        legoBrickService.createLegoBrick(legoBrick);
        return ResponseEntity.ok(LegoBrickDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LegoBrickDTO> updateLegoBrick(@PathVariable("id") int id, @RequestBody LegoBrick legoBrick) throws NonexistentLegoBrick {
        Optional<LegoBrick> optionalLegoBrick = legoBrickService.getLegoBrickById(id);
        if (optionalLegoBrick.isPresent()) {
            LegoBrick existingLegoBrick = optionalLegoBrick.get();
            existingLegoBrick.setBrickId(legoBrick.getBrickId());
            existingLegoBrick.setName(legoBrick.getName());
            existingLegoBrick.setColor(legoBrick.getColor());
            existingLegoBrick.setCondition(legoBrick.getCondition());
            existingLegoBrick.setRarity(legoBrick.getRarity());
            legoBrickService.updateLegoBrick(existingLegoBrick);
            LegoBrickDTO LegoBrickDTO = new LegoBrickDTO(legoBrick);
            return ResponseEntity.ok(LegoBrickDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLegoBrick(@PathVariable("id") int id) throws NonexistentLegoBrick{
        Optional<LegoBrick> optionalLegoBrick = legoBrickService.getLegoBrickById(id);
        if (optionalLegoBrick.isPresent()) {
            legoBrickService.deleteLegoBrick(optionalLegoBrick.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public List<LegoBrick> getAllLegoBricks() {
        return legoBrickService.getAllLegoBricks();
    }
}

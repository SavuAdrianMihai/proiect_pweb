package com.example.demo.controller;

import com.example.demo.dao.LegoSet;
import com.example.demo.dto.LegoSetDTO;
import com.example.demo.exceptions.NonexistentLegoSet;
import com.example.demo.service.LegoSetService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/legosets")
public class LegoSetController {

    @Autowired
    private LegoSetService legoSetService;

    @GetMapping("/{id}")
    @ApiOperation(value = "finds legoset by id", notes = "prints relevant info about the legoset")
    @ApiResponses({
            @ApiResponse(code=404, message = "No lego set has been found", response = NonexistentLegoSet.class),
            @ApiResponse(code=200, message = "lego set found", response = LegoSet.class),
            @ApiResponse(code=500, message = "unkown error", response = Exception.class)
    })
    public ResponseEntity<LegoSetDTO> getLegoSetById(@PathVariable("id") int id) throws NonexistentLegoSet {
        Optional<LegoSet> optionalLegoSet = legoSetService.getLegoSetById(id);
        if (optionalLegoSet.isPresent()) {
            LegoSetDTO legoSetDTO = new LegoSetDTO(optionalLegoSet.get());
            return ResponseEntity.ok(legoSetDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<LegoSetDTO> createLegoSet(@RequestBody LegoSet legoSet) {
        LegoSetDTO legoSetDTO = new LegoSetDTO(legoSet);
        legoSetService.createLegoSet(legoSet);
        return ResponseEntity.ok(legoSetDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LegoSetDTO> updateLegoSet(@PathVariable("id") int id, @RequestBody LegoSet legoSet) throws NonexistentLegoSet {
        Optional<LegoSet> optionalLegoSet = legoSetService.getLegoSetById(id);
        if (optionalLegoSet.isPresent()) {
            LegoSet existingLegoSet = optionalLegoSet.get();
            existingLegoSet.setSetId(legoSet.getSetId());
            existingLegoSet.setName(legoSet.getName());
            existingLegoSet.setNumberOfBricks(legoSet.getNumberOfBricks());
            existingLegoSet.setCollection(legoSet.getCollection());
            existingLegoSet.setReleaseYear(legoSet.getReleaseYear());
            legoSetService.updateLegoSet(existingLegoSet);
            LegoSetDTO legoSetDTO = new LegoSetDTO(legoSet);
            return ResponseEntity.ok(legoSetDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLegoSet(@PathVariable("id") int id) throws NonexistentLegoSet{
        Optional<LegoSet> optionalLegoSet = legoSetService.getLegoSetById(id);
        if (optionalLegoSet.isPresent()) {
            legoSetService.deleteLegoSet(optionalLegoSet.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public List<LegoSet> getAllLegoSets() {
        return legoSetService.getAllLegoSets();
    }
}

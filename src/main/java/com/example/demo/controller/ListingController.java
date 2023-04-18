package com.example.demo.controller;

import com.example.demo.dao.Listing;
import com.example.demo.dto.ListingDTO;
import com.example.demo.exceptions.NoListingsFound;
import com.example.demo.exceptions.NonexistentListing;
import com.example.demo.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/listings")
public class ListingController {

    @Autowired
    private ListingService listingService;

    @GetMapping("/")
    public List<Listing> getAllListings() throws NoListingsFound {
        return listingService.getAllListings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingDTO> getListingById(@PathVariable(name = "id") int id) throws NonexistentListing {
        Optional<Listing> optionalListing = listingService.getListingById(id);
        if (optionalListing.isPresent()) {
            ListingDTO listingDTO = new ListingDTO(optionalListing.get());
            return ResponseEntity.ok(listingDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

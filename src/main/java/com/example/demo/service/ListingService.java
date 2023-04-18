package com.example.demo.service;

import com.example.demo.dao.IListingJPARepository;
import com.example.demo.dao.LegoSet;
import com.example.demo.dao.Listing;
import com.example.demo.exceptions.NoListingsFound;
import com.example.demo.exceptions.NonexistentLegoSet;
import com.example.demo.exceptions.NonexistentListing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListingService {

    @Autowired
    private IListingJPARepository listingJPARepository;

    public List<Listing> getAllListings() throws NoListingsFound {
        List<Listing> listings = listingJPARepository.findAll();
        if (listings.isEmpty()) {
            throw new NoListingsFound();
        }
        return listingJPARepository.findAll();
    }

    public Optional<Listing> getListingById(int id) throws NonexistentListing {
        Listing listing = listingJPARepository.findById(id).orElse(null);
        if (listing == null) {
            throw new NonexistentListing();
        }
        return listingJPARepository.findById(id);
    }
}

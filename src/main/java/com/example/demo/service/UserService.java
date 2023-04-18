package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.NonexistentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService {
    @Autowired
    private IUserJPARepository iUserJPARepository;

    public boolean addUser(User u) {
        u.setInventory(new Inventory());
        iUserJPARepository.save(u);
        return true;
    }

    public User findUser(UserDTO u) throws NonexistentUser {
        String email = u.getMail();
        String password = u.getPassword();
        List<User> users = iUserJPARepository.findByEmailAndPassword(email, password);
        if (users.size() > 1){
            return null;
        } else if (users.size() == 0){
            throw new NonexistentUser();
        }
        User user = users.get(0);
        return user;
    }

    public Optional<User> findById(int id) throws NonexistentUser {
        User u = iUserJPARepository.findById(id).orElse(null);
        if (u == null) {
            throw new NonexistentUser();
        }
        return iUserJPARepository.findById(id);
    }

    public List<User> getAllUsers() {
        return iUserJPARepository.findAll();
    }

    public User updateUserInventoryWithBrick(int id, LegoBrick lB) throws NonexistentUser {
        User u = iUserJPARepository.findById(id).orElse(null);
        if (u == null) {
            throw new NonexistentUser();
        }
        Set<LegoBrick> updatedSet = u.getInventory().getLegoBricks();
        updatedSet.add(lB);
        u.getInventory().setLegoBricks(updatedSet);
        return iUserJPARepository.save(u);
    }

    public User updateUserInventoryWithSet(int id, LegoSet lS) throws NonexistentUser {
        User u = iUserJPARepository.findById(id).orElse(null);
        if (u == null) {
            throw new NonexistentUser();
        }
        Set<LegoSet> updatedSet = u.getInventory().getLegoSets();
        updatedSet.add(lS);
        u.getInventory().setLegoSets(updatedSet);
        return iUserJPARepository.save(u);
    }

    public User createListingForUser(int id, Listing listing) throws NonexistentUser {
        User u = iUserJPARepository.findById(id).orElse(null);
        if (u == null) {
            throw new NonexistentUser();
        }
        Set<Listing> updatedSet = u.getListings();
        listing.setUser(u);
        updatedSet.add(listing);
        u.setListings(updatedSet);
        //iListingJPARepository.save(listing);
        return iUserJPARepository.save(u);
    }
}

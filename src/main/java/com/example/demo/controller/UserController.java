package com.example.demo.controller;

import com.example.demo.component.UserSession;
import com.example.demo.dao.*;
import com.example.demo.dto.InventoryDTO;
import com.example.demo.dto.ListingDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.NonexistentUser;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserSession userSession;

    @PostMapping("/register")
    @ApiOperation(value = "register new user", notes = "adds new user to the db")
    @ApiResponses({
            @ApiResponse(code=200, message = "registered", response = User.class),
            @ApiResponse(code=500, message = "unkown error", response = Exception.class)
    })
    public ModelAndView registerUser (UserDTO u) {
        User user = new User();
        user.setEmail(u.getMail());
        user.setUsername(u.getUsername());
        user.setPassword(u.getPassword());
        this.userService.addUser(user);
        ModelAndView modelAndView = new ModelAndView("redirect:/views/login");
        return modelAndView;
    }

    @GetMapping("views/register")
    public ModelAndView registerView() {
        ModelAndView mv = new ModelAndView("register");
        return mv;
    }

    @PostMapping("/login")
    @ApiResponses({
            @ApiResponse(code=404, message = "No user has been found", response = NonexistentUser.class),
            @ApiResponse(code=200, message = "logged in", response = User.class),
            @ApiResponse(code=500, message = "unkown error", response = Exception.class)
    })
    public ModelAndView login(UserDTO u) throws NonexistentUser {
        User user = this.userService.findUser(u);
        this.userSession.setUserID(user.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/views/listings");
        return modelAndView;
    }

    @GetMapping("views/login")
    public ModelAndView loginView() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @GetMapping("/users/{id}")
    @ApiOperation(value = "finds user by id", notes = "prints relevant info about the user")
    @ApiResponses({
            @ApiResponse(code=404, message = "No user has been found", response = NonexistentUser.class),
            @ApiResponse(code=200, message = "logged in", response = User.class),
            @ApiResponse(code=500, message = "Something broke :(", response = Exception.class)
    })
    public UserDTO getUser(@PathVariable int id) throws NonexistentUser, UnknownError {
        // retrieve user logic
        Optional<User> userResponse = userService.findById(id);
        User user = userResponse.get();
        UserDTO userDTO = new UserDTO(user.getUsername(), user.getEmail(), user.getPassword());
        return userDTO;
    }

    @GetMapping("users/{id}/inventory")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable("id") int id) throws NonexistentUser, UnknownError {
        Inventory inventory = userService.findById(id).get().getInventory();
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setLegoBricks(inventory.getLegoBricks());
        inventoryDTO.setLegoSets(inventory.getLegoSets());
        return ResponseEntity.ok(inventoryDTO);
    }

    @PutMapping("users/{id}/inventory/bricks")
    public ResponseEntity<InventoryDTO> updateInventoryBricks(@PathVariable("id") int id, @RequestBody LegoBrick legoBrick) throws NonexistentUser {
        Inventory updatedInventory = userService.updateUserInventoryWithBrick(id, legoBrick).getInventory();
        if (updatedInventory == null) {
            return ResponseEntity.notFound().build();
        }
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setLegoSets(updatedInventory.getLegoSets());
        inventoryDTO.setLegoBricks(updatedInventory.getLegoBricks());
        return ResponseEntity.ok(inventoryDTO);
    }

    @PutMapping("users/{id}/inventory/sets")
    public ResponseEntity<InventoryDTO> updateInventorySets(@PathVariable("id") int id, @RequestBody LegoSet legoSet) throws NonexistentUser {
        Inventory updatedInventory = userService.updateUserInventoryWithSet(id, legoSet).getInventory();
        if (updatedInventory == null) {
            return ResponseEntity.notFound().build();
        }
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setLegoSets(updatedInventory.getLegoSets());
        inventoryDTO.setLegoBricks(updatedInventory.getLegoBricks());
        return ResponseEntity.ok(inventoryDTO);
    }

    @PostMapping("users/{id}/listings/")
    public ResponseEntity<ListingDTO> createListing(@PathVariable("id") int id, @RequestBody Listing listing) throws NonexistentUser {
        User updatedUser = userService.createListingForUser(id, listing);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        ListingDTO listingDTO = new ListingDTO(listing);
        return ResponseEntity.ok(listingDTO);
    }

    @PostMapping("/logout")
    public String logout(){
        this.userSession.setUserID(-1);
        return "You have been logged out";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}

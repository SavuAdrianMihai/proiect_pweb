package com.example.demo.exceptions;

public class NonexistentListing extends Exception {
    public NonexistentListing() {
        super("Listing nonexistent");
    }
}

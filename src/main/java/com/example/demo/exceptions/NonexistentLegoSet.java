package com.example.demo.exceptions;

public class NonexistentLegoSet extends Exception {
    public NonexistentLegoSet() {
        super("Lego set nonexistent");
    }
}
package com.example.demo.exceptions;

public class NonexistentLegoBrick extends Exception {
    public NonexistentLegoBrick() {
        super("Lego brick nonexistent");
    }
}

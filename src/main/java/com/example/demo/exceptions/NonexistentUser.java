package com.example.demo.exceptions;

public class NonexistentUser extends Exception {
    public NonexistentUser() {
        super("User nonexistent");
    }
}

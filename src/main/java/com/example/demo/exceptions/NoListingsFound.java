package com.example.demo.exceptions;

public class NoListingsFound extends Exception {
    public NoListingsFound() {
        super("no listings found");
    }
}

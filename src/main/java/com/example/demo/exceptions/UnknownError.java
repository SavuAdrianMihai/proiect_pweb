package com.example.demo.exceptions;

public class UnknownError extends Exception {
    public UnknownError() {
        super("Something broke :(");
    }
}

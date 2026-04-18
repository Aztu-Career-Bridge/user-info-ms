package com.example.userinfoms.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String field, String value) {
        super("Student not found with " + field + ": " + value);
    }

}
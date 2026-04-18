package com.example.userinfoms.exception;

public class StudentAlreadyExistsException extends RuntimeException {

    public StudentAlreadyExistsException(String email) {
        super("Student with email " + email + " already exists");
    }

}

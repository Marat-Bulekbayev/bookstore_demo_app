package com.epam.setbasicbackend.exception;

public class EmailHadBeenTakenException extends Exception {

    public EmailHadBeenTakenException(String message) {
        super(message);
    }
}

package com.example.eCommerce.prac1.utility;

public class DataNotFoundException extends RuntimeException {
    /**
     * Used for giving default message..
     */
    public DataNotFoundException() {
        super("Data Not Found.");
    }

    /**
     * we can set message given in constructor argument
     * @param message String
     */
    public DataNotFoundException(String message) {
        super(message);
    }
}

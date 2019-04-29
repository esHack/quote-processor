package com.zopa.quote.Exception;

public class QuoteException extends RuntimeException{

    private String message;

    public QuoteException(String message) {
        super(message);
    }

    public QuoteException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

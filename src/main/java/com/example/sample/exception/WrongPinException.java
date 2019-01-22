package com.example.sample.exception;

public class WrongPinException extends Exception {
	private static final long serialVersionUID = 1L;

    private String message = null;

    public WrongPinException(final String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
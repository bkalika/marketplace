package com.intellistart.marketplace.exception;

/**
 * @author @bkalika
 * Created on 22.07.2022 5:55 PM
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final Long serialVersionUID = 412341234123412341L;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(final String message) {
        super(message);
    }

    public ResourceNotFoundException(final Throwable cause) {
        super(cause);
    }
}

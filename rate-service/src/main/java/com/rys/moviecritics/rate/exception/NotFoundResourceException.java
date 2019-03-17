package com.rys.moviecritics.rate.exception;

public class NotFoundResourceException extends RuntimeException {

    private static final long serialVersionUID = -7110634628689499596L;

    public NotFoundResourceException(final String message) {
        super(message);
    }
}

package com.rys.moviecriticts.rate.domain.exception;

public class DomainException extends RuntimeException {

    private static final long serialVersionUID = 1165467390131401143L;

    public DomainException(final String message) {
        super(message);
    }
}

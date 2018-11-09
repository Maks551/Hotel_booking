package com.example.hotel_booking.util.exception;

import javax.validation.constraints.NotNull;

/**
 * Throws when bean is not new.
 */
public class IllegalRequestDataException extends RuntimeException {
    public IllegalRequestDataException(@NotNull String msg) {
        super(msg);
    }
}

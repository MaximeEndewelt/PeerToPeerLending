package com.landbay.peer2peer.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * A utility class converting an exception into a HTTP response
 */
public class ExceptionConverter
{
    /**
     * Converts an exception into a HTTP Response
     * @param exception
     * @return
     */
    public static ResponseEntity<?> convertException(Exception exception)
    {
        ResponseEntity output = null;
        if(exception instanceof IllegalArgumentException)
        {
            output = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                   .body(exception.getMessage());
        }
        else
        {
            output = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                   .body(exception.getMessage());
        }

        return output;
    }
}

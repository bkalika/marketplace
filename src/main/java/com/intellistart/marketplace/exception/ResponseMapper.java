package com.intellistart.marketplace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author @bkalika
 * Created on 24.07.2022 12:02 AM
 */
public class ResponseMapper {
    public static ResponseEntity<Object> errorToEntity(ResponseError error, HttpStatus status) {
        return new ResponseEntity<>(error, status);
    }
}

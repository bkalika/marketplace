package com.intellistart.marketplace.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author @bkalika
 * Created on 24.07.2022 12:00 AM
 */

@Data
@AllArgsConstructor
public class ResponseError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private List<String> errors;
}

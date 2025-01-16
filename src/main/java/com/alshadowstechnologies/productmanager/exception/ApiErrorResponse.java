package com.alshadowstechnologies.productmanager.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class ApiErrorResponse {

    private Integer status;
    private String message;
    private String error;
    private String timestamp;

    public ApiErrorResponse(Integer status, String message, String error) {
        this.status = status;
        this.message = message;
        this.error = error;
        this.timestamp = Instant.now().toString();
    }
}

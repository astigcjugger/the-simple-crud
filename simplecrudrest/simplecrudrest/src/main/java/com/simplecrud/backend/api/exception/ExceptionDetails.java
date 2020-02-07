package com.simplecrud.backend.api.exception;

import java.util.Date;

public class ExceptionDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ExceptionDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
         return message;
    }

    public String getDetails() {
         return details;
    }
}

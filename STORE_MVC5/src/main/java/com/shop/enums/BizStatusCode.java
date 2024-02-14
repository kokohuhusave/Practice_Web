package com.shop.enums;

import lombok.Getter;
import java.util.Date;

@Getter
public enum BizStatusCode implements StatusCode {
    SUCCESS(new Date(), "200", "SuccessTest", "Operation completed successfully"),
    FAILURE(new Date(), "500", "FailureTest", "Operation failed"),
    NOT_FOUND(new Date(), "404", "Not Found", "Resource not found"),
    UNAUTHORIZED(new Date(), "401", "Unauthorized", "Access unauthorized"),
	BAD_REQUEST(new Date(), "400", "BizStatusCode.badrequest", "The request could not be understood by the server due to malformed syntax"),
    FORBIDDEN(new Date(), "403", "Forbidden", "The server understood the request, but is refusing to fulfill it"),
    METHOD_NOT_ALLOWED(new Date(), "405", "Method Not Allowed", "The method specified in the Request-Line is not allowed for the resource identified by the Request-URI"),
    CONFLICT(new Date(), "409", "Conflict", "The request could not be completed due to a conflict with the current state of the resource"),
    INTERNAL_SERVER_ERROR(new Date(), "500", "Internal Server Error", "The server encountered an unexpected condition which prevented it from fulfilling the request"),
    SERVICE_UNAVAILABLE(new Date(), "503", "Service Unavailable", "The server is currently unable to handle the request due to a temporary overloading or maintenance of the server");
	
	
    private final Date timestamp;
    private final String code;
    private final String message;
    private final String details;

    BizStatusCode(Date timestamp, String code, String message, String details) {
        this.timestamp = timestamp;
        this.code = code;
        this.message = message;
        this.details = details;
    }
}
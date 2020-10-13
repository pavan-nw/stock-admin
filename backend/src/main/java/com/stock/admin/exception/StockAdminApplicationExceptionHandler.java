package com.stock.admin.exception;

import com.stock.admin.model.response.ErrorResponse;
import com.stock.admin.model.response.Response;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The type Rest exception handler.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class StockAdminApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle error response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(value = {StockAdminApplicationException.class})
    protected ResponseEntity<Object> handleErrorResponse(StockAdminApplicationException ex, WebRequest request) {
        if (ex.getCause() != null) {
            ex.getCause().printStackTrace();
        }
        Object response = Response.buildResponse("error", ex.getErrorObject(), false);
        return handleExceptionInternal(ex, response, new HttpHeaders(), ex.getHttpStatus(), request);
    }

    /**
     * Handle unexpected error response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleUnexpectedError(Exception ex, WebRequest request) {
        ex.printStackTrace();
        Object response = Response.buildResponse("error", new ErrorResponse(500, ex.getMessage()), false);
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}

package com.stock.admin.exception;

import com.stock.admin.model.response.ErrorResponse;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

/**
 * The type Stock admin application exception.
 */
public class StockAdminApplicationException extends RuntimeException {

    private final Object errorObject;
    private final HttpStatus httpStatus;

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param cause       the cause (which is saved for later retrieval by the                    {@link #getCause()} method).  (A <tt>null</tt> value is                    permitted, and indicates that the cause is nonexistent or unknown.)
     * @param errorObject the error object
     * @param httpStatus  the http status
     * @since 1.4
     */
    public StockAdminApplicationException(Throwable cause, @NotNull Object errorObject, @NotNull HttpStatus httpStatus) {
        super(cause);
        Objects.requireNonNull(errorObject);
        Objects.requireNonNull(httpStatus);
        this.errorObject = errorObject;
        this.httpStatus = httpStatus;
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param errorObject the error object
     * @param httpStatus  the http status
     * @since 1.4
     */
    public StockAdminApplicationException(@NotNull Object errorObject, @NotNull HttpStatus httpStatus) {
        super();
        Objects.requireNonNull(errorObject);
        Objects.requireNonNull(httpStatus);
        this.errorObject = errorObject;
        this.httpStatus = httpStatus;
    }

    public StockAdminApplicationException(Integer errorCode, String errorMessage, @NotNull HttpStatus httpStatus) {
        super();
        Objects.requireNonNull(httpStatus);
        this.errorObject = new ErrorResponse(errorCode, errorMessage);
        this.httpStatus = httpStatus;
    }

    public StockAdminApplicationException(String errorMessage, @NotNull HttpStatus httpStatus) {
        super();
        Objects.requireNonNull(httpStatus);
        this.errorObject = new ErrorResponse(httpStatus.value(), errorMessage);
        this.httpStatus = httpStatus;
    }

    /**
     * Gets error object.
     *
     * @return the error object
     */
    public Object getErrorObject() {
        return errorObject;
    }

    /**
     * Gets http status.
     *
     * @return the http status
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

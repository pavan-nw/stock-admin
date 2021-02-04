package com.stock.admin.model.response;

/**
 * The type Error response.
 */
public class ErrorResponse {

    /**
     * The Error code.
     */
    int errorCode;
    /**
     * The Error message.
     */
    String errorMessage;

    /**
     * Instantiates a new Error response.
     */
    public ErrorResponse() {
    }

    /**
     * Instantiates a new Error response.
     *
     * @param errorCode    the error code
     * @param errorMessage the error message
     */
    public ErrorResponse(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Sets error code.
     *
     * @param errorCode the error code
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets error message.
     *
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

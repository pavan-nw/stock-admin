package com.stock.admin.model.response;

/**
 * The type Response.
 */
public class Response {
    /**
     * The enum Response status.
     */
    public enum ResponseStatus {
        /**
         * Success response status.
         */
        SUCCESS,
        /**
         * Failure response status.
         */
        FAILURE
    } // Check is this th right way??

    private String type;
    private ResponseStatus status;
    private Object payload;

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public ResponseStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    /**
     * Gets payload.
     *
     * @return the payload
     */
    public Object getPayload() {
        return payload;
    }

    /**
     * Sets payload.
     *
     * @param payload the payload
     */
    public void setPayload(Object payload) {
        this.payload = payload;
    }
}

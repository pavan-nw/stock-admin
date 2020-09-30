package com.stock.admin.model.response;

/**
 * The type Response.
 */
public class Response {

    private String type;
    private ResponseStatus status;
    private Object payload;

    /**
     * Instantiates a new Response.
     */
    public Response() {
    }

    /**
     * Instantiates a new Response.
     *
     * @param type           the type
     * @param payload        the payload
     * @param responseStatus the response status
     */
    public Response(String type, Object payload, ResponseStatus responseStatus) {
        this.type = type;
        this.payload = payload;
        this.status = responseStatus;
    }

    /**
     * Build response response.
     *
     * @param type    the type
     * @param payload the payload
     * @param success the success
     * @return the response
     */
    public static Response buildResponse(String type, Object payload, boolean success) {
        return new Response(type, payload, success ? ResponseStatus.SUCCESS : ResponseStatus.FAILURE);
    }

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

package com.stock.admin.model;

public class Response {
    public enum ResponseStatus {
        SUCCESS,
        FAILURE
    } // Check is this th right way??

    private String type;
    private ResponseStatus status;
    private Object payload;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}

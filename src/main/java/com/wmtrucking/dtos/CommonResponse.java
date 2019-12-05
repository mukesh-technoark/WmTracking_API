package com.wmtrucking.dtos;

import java.util.List;

public class CommonResponse {


    private String message;
    private Object data;
    private int status;
    private List<String> errors;

    public CommonResponse(String message, Object data, int status, List<String> errors) {
        this.message = message;
        this.data = data;
        this.status = status;
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int code) {
        this.status = code;
    }

}

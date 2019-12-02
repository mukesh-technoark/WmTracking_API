package com.wmtrucking.exceptions;

import java.util.ArrayList;
import java.util.List;

public class InvalidTokenException extends Exception {

    List<String> errors = new ArrayList<>();

    public InvalidTokenException(String msg) {
        super(msg);
    }

    public InvalidTokenException(String msg, List<String> errors) {
        super(msg);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}

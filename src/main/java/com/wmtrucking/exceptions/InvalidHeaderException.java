package com.wmtrucking.exceptions;

import java.util.ArrayList;
import java.util.List;


public class InvalidHeaderException extends Exception {

    List<String> errors = new ArrayList<>();

    public InvalidHeaderException(String msg, List<String> errors) {
        super(msg);
        this.errors = errors;
    }

    public InvalidHeaderException(String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}

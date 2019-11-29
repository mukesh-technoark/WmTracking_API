package com.wmtrucking.exceptions;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.ObjectError;

public class CustomValidationException extends Exception {

    List<String> errors = new ArrayList<>();

    public CustomValidationException(String exception, List<String> errors) {
        super(exception);
        this.errors = errors;
    }

    public CustomValidationException(List<ObjectError> errors) {
        super("Error");
        for (ObjectError objectError : errors) {
            this.errors.add(objectError.getDefaultMessage());
        }

    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}

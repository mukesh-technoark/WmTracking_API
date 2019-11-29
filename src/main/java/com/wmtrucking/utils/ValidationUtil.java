package com.wmtrucking.utils;

import org.springframework.validation.ObjectError;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.Errors;

public class ValidationUtil {

    public static List<String> fromBindingErrors(Errors errors) {
        List<String> validErrors = new ArrayList<String>();
        for (ObjectError objectError : errors.getAllErrors()) {
            validErrors.add(objectError.getDefaultMessage());
        }
        return validErrors;
    }
}

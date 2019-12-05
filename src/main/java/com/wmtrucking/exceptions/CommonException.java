package com.wmtrucking.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.wmtrucking.dtos.CommonResponse;
import com.wmtrucking.utils.ValidationUtil;

import java.util.Arrays;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@CrossOrigin(origins = "*")
public class CommonException {

    @ExceptionHandler(value = {TransactionSystemException.class})
    protected CommonResponse handleConflict(TransactionSystemException ex, WebRequest request) {
        Throwable cause = ex.getRootCause();

        if (cause instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
            return new CommonResponse("Validation Error", null, 0, Arrays.asList(constraintViolations.toString()));
        }
        return null;
    }

    // this is most serious error
    @ExceptionHandler(InvalidHeaderException.class)
    public CommonResponse invalidHeader(InvalidHeaderException ex) {
        return new CommonResponse("Header-Fraud Detected", null, 0, ex.getErrors());
    }

    @ExceptionHandler(InvalidTokenException.class)
    public CommonResponse invalidHeader(InvalidTokenException ex) {
        return new CommonResponse("Validation Error", null, 0, ex.getErrors());
    }

    // General Exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CommonResponse handleException(final Exception exception, final HttpServletRequest request) {
        return new CommonResponse("Error", null, 0, Arrays.asList(exception.getMessage()));
    }

//    @ExceptionHandler(FileUploadException.class)
//    public CommonResponse invalidHeader(FileUploadException ex) {
//        return new CommonResponse("Validation Error", null, 6666, ex.getErrors());
//    }

    @ExceptionHandler(CustomValidationException.class)
    public CommonResponse customValidation(CustomValidationException ex) {
        return new CommonResponse("Validation Error", null, 0, ex.getErrors());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse invalidInput(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();
        return new CommonResponse("Validation Error", null, 0, ValidationUtil.fromBindingErrors(result));
    }

    @ExceptionHandler(JWTVerificationException.class)
    public CommonResponse invalidRuntimeToken(JWTVerificationException exception) {
        return new CommonResponse("Validation Error", null, 0, Arrays.asList(exception.getMessage()));
    }
}

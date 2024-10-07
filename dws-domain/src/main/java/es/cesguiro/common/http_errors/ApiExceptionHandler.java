package es.cesguiro.common.http_errors;

import es.cesguiro.common.http_errors.ErrorMessage;
import es.cesguiro.domain.exception.ResourceAlreadyExistsException;
import es.cesguiro.domain.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            ResourceNotFoundException.class
    })
    @ResponseBody
    public ErrorMessage notFoundRequest(ResourceNotFoundException exception) {
        exception.printStackTrace();
        return new ErrorMessage(exception, HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(HttpMessageNotReadableException exception) {
        exception.printStackTrace();
        return new ErrorMessage(new RuntimeException("Invalid JSON format or missing body"), HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
            ResourceAlreadyExistsException.class
    })
    @ResponseBody
    public ErrorMessage resourceAlreadyExists(ResourceAlreadyExistsException exception) {
        exception.printStackTrace();
        return new ErrorMessage(exception, HttpStatus.CONFLICT.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorMessage handleGeneralException(Exception exception) {
        exception.printStackTrace();
        return new ErrorMessage(new RuntimeException("Internal error"), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}

package es.cesguiro.common.http_errors;

import es.cesguiro.domain.exception.ResourceAlreadyExistsException;
import es.cesguiro.domain.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            ResourceNotFoundException.class
    })
    @ResponseBody
    public ErrorMessage notFoundRequest(ResourceNotFoundException exception) {
        log.error(exception.getMessage());
        return new ErrorMessage(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(HttpMessageNotReadableException exception) {
        log.error(exception.getMessage());
        return new ErrorMessage(new RuntimeException("Invalid JSON format or missing body"));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
            ResourceAlreadyExistsException.class
    })
    @ResponseBody
    public ErrorMessage resourceAlreadyExists(ResourceAlreadyExistsException exception) {
        log.error(exception.getMessage());
        return new ErrorMessage(exception);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorMessage handleGeneralException(Exception exception) {
        log.error(exception.getMessage());
        return new ErrorMessage(exception);
    }
}
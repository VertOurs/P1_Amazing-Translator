package fr.vertours.translator.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InaccurateNumberOrLangException.class)
    public final ResponseEntity<String> handledInaccurateNumberException(InaccurateNumberOrLangException e) {
        log.error("Handling " + e.getClass().getSimpleName() + " due to " + e.getMessage() + ". More informations : "+e.getStackTrace());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<String> handledNumberFormatException(MethodArgumentTypeMismatchException e) {
        log.error("Handling " + e.getClass().getSimpleName() + " due to " + e.getMessage() + ". More informations : "+e.getStackTrace());
        return ResponseEntity.badRequest().body("your request does not seem to contain a integer number");
    }
}
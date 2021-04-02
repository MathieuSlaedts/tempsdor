package be.tempsdor.tempsdor.controllers;

import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.reports.Report;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerAdviser extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ElementsNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected Report HandleElementsNotFoundException(ElementsNotFoundException ex, HttpServletRequest request) {
        return Report.builder()
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        //return super.handleMethodArgumentNotValid(ex, headers, status, request);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}

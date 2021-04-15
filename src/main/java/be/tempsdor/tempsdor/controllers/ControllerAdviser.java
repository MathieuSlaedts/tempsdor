package be.tempsdor.tempsdor.controllers;

import be.tempsdor.tempsdor.exceptions.*;
import be.tempsdor.tempsdor.reports.Report;
import be.tempsdor.tempsdor.reports.ReportWithDetail;
import be.tempsdor.tempsdor.reports.ReportWithDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ControllerAdviser extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            ElementNotFoundException.class,
            ElementsNotFoundException.class,
            MismatchingIdentifersException.class,
            OwnRoomBookingException.class,
            RoomUnavailableException.class,
            NullPropertyException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected Report HandleElementSNotFoundException(Exception ex, HttpServletRequest request) {

        return Report.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler({
            ElementAlreadyExistsException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ReportWithDetail HandleBadRequestException(Exception ex, HttpServletRequest request) {

        return ReportWithDetail.childBuilder()
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .message("Erreur(s) de contrainte d'unicité")
                .detail(ex.getMessage())
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = new ArrayList<>();

        if( ex.getGlobalErrorCount() > 0 ) {
            for (ObjectError globalError : ex.getBindingResult().getGlobalErrors()) {
                errors.add("[erreur globale] " + globalError.getObjectName() + " : " + globalError.getDefaultMessage());
            }
        }

        if( ex.getFieldErrorCount() > 0 ) {
            for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
                errors.add("[erreur de champs] " + fieldError.getField() + " : " + fieldError.getDefaultMessage() );
            }
        }

        return ResponseEntity.badRequest().body(
                ReportWithDetails.childBuilder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .path(request.getDescription(false).replaceFirst("uri=", ""))
                        .message("Erreur(s) de validation")
                        .details(errors)
                        .build());
    }
}

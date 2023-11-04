package fr.sncf.d2d.up2dev.tortycolis.packages.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fr.sncf.d2d.up2dev.tortycolis.packages.exceptions.CreatePackageException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice(assignableTypes = PackagesController.class)
public class PackagesControllerAdvice {
    
    @ExceptionHandler({ CreatePackageException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleCreateError(){}
}

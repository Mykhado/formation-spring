package fr.sncf.d2d.up2dev.tortycolis.common.rest;

import java.util.Optional;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ApplicationErrorController implements ErrorController {
    
    @RequestMapping("/error")
    public ResponseEntity<?> handleError(HttpServletRequest request){
        return ResponseEntity
            .status(
                Optional.ofNullable((Integer)request.getAttribute("jakarta.servlet.error.status_code"))
                    .map(HttpStatus::valueOf)
                    .orElse(HttpStatus.INTERNAL_SERVER_ERROR)
            )
            .build();
    }
}

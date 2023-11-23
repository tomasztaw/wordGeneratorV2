/**
 * Created by tomasz_taw
 * Date: 02.11.2023
 * Time: 20:12
 * Project Name: wordGenerator
 * Description:
 */
package pl.taw.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.taw.exception.BadRequestException;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<String> handleTimeoutException(TimeoutException e) {
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(e.getMessage());
    }

    @ExceptionHandler(SocketTimeoutException.class)
    @ResponseBody
    public ResponseEntity<String> handleSocketTimeoutException(SocketTimeoutException ex) {
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                .body("Żądanie przekroczyło limit czasu: " + ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}

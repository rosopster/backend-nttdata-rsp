package com.nttdata.backend_nttdata_rsp.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nttdata.backend_nttdata_rsp.common.exception.BadRequestException;
import com.nttdata.backend_nttdata_rsp.common.exception.ClienteNotFoundException;
import com.nttdata.backend_nttdata_rsp.common.exception.ErrorInfo;
import com.nttdata.backend_nttdata_rsp.common.util.MessageUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final MessageUtil messageUtil;

    GlobalExceptionHandler(MessageUtil messageUtil) {
        this.messageUtil = messageUtil;
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorInfo> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        logger.error("BadRequestException: {}", ex.getMessage());
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);        
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleClienteNotFoundException(ClienteNotFoundException ex, HttpServletRequest request) {
        logger.warn("ClienteNotFoundException: {}", ex.getMessage());
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleGeneralException(Exception ex, HttpServletRequest request) {
        logger.error("Exception: {}", ex.getMessage(), ex);
        String message = messageUtil.getMessage("error.interno");
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), message + " - " + ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
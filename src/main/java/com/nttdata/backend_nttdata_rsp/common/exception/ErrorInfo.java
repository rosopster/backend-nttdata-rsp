package com.nttdata.backend_nttdata_rsp.common.exception;

import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ErrorInfo {

    @JsonProperty("message")
    private String message;
    @JsonProperty("status_code")
    private int statusCode;
    @JsonProperty("uri")
    private String uriRequested;
 
    public ErrorInfo(HttpClientErrorException  exception, String uriRequested) {
        this.message = exception.getMessage();
        this.statusCode = exception.getStatusCode().value();
        this.uriRequested = uriRequested;
    }
 
    public ErrorInfo(int statusCode, String message, String uriRequested) {
        this.message = message;
        this.statusCode = statusCode;
        this.uriRequested = uriRequested;
    }
}    
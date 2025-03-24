package com.email.envio.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

public class ErrorResponse {

    public static final  String INVALID_FIELD = "invalid field";

    private String detail;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, String> additionalDetails;


    public ErrorResponse(String detail) {
        this.detail = detail;
    }

    public ErrorResponse(String detail, Map<String, String> additionalDetails) {
        this.detail = detail;
        this.additionalDetails = additionalDetails;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Map<String, String> getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(Map<String, String> additionalDetails) {
        this.additionalDetails = additionalDetails;
    }
}

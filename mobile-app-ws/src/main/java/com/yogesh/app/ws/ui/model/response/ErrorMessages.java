package com.yogesh.app.ws.ui.model.response;

public enum ErrorMessages {

    MISSING_REQUIRED_FIELD("Missing required fields, please check documentation for required fields"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    NO_RECORD_FOUND("Record with provided id is not found"),
    AUTHENTICATION_FAILED("Authentication Failed"),
    COULD_NOT_UPDATE_RECORD("Could not update Record"),
    COULD_NOT_DELETE_RECORD("Could not delete Record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email Address count not verified");

    private String errorMessage;

    ErrorMessages(String errorMessage)
    {
        this.errorMessage=errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}

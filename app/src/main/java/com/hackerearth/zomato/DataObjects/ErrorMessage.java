package com.hackerearth.zomato.DataObjects;

/**
 * Created by santosh on 9/24/2016.
 */

public class ErrorMessage {

    private byte errorCode;
    private String strErrorMessage;

    public byte getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(byte errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMessage() {
        return strErrorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.strErrorMessage = errorMessage;
    }

}
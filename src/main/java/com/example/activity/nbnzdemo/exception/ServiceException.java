package com.example.activity.nbnzdemo.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by pavankumar on 22/03/16.
 */
@JsonIgnoreProperties
public class ServiceException extends RuntimeException {

  private String errorCode;
  private String errorMessage;
  private int httpStatus;
  private String status;

  public ServiceException() {

  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(Throwable cause) {
    super(cause);
  }

  public ServiceException(String errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

    public ServiceException(String errorCode, String errorMessage, int httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus=httpStatus;
    }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

  public String toString() {
    return new StringBuilder().append("[ServiceException: {")
            .append(" errorCode: ").append(errorCode)
            .append(" errorMessage: ").append(errorMessage)
            .append(" httpStatus: ").append(httpStatus)
            .append("}]").toString();
  }
}
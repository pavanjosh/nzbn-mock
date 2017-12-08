package com.example.activity.nbnzdemo.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * Created by pavankumarjoshi on 6/12/2017.
 */
public class NZBNDefaultErrorHandler extends DefaultResponseErrorHandler{

  @Override public boolean hasError(ClientHttpResponse response) throws IOException {
    return super.hasError(response);
  }

  @Override public void handleError(ClientHttpResponse response) throws IOException {
    HttpStatus statusCode = response.getStatusCode();
    ServiceException se = new ServiceException();
    se.setErrorCode(String.format("%d",  statusCode.value()));
    se.setErrorMessage(response.getStatusText());
    throw se;
  }
}

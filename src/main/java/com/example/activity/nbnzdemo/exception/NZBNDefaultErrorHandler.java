package com.example.activity.nbnzdemo.exception;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import com.google.gson.Gson;

/**
 * Created by pavankumarjoshi on 6/12/2017.
 */
public class NZBNDefaultErrorHandler extends DefaultResponseErrorHandler{

  private final int UNAUTHORISED_CODE = 401;
  @Override public boolean hasError(ClientHttpResponse response) throws IOException {
    return super.hasError(response);
  }

  @Override public void handleError(ClientHttpResponse response) throws IOException {
    HttpStatus statusCode = response.getStatusCode();
    String statusText = response.getStatusText();
    if(statusCode.value() == UNAUTHORISED_CODE){
      ServiceException se = new ServiceException();
      se.setErrorCode(String.valueOf(statusCode));
      se.setErrorDescription(statusText);
      se.setHttpStatus(UNAUTHORISED_CODE);
      throw se;
    }
    else{
      String errorBody = new String(getResponseBody(response), "UTF-8");
      Gson gson = new Gson();
      ServiceException se = gson.fromJson(errorBody,ServiceException.class);
      se.setErrorCode(se.getStatus());
      se.setHttpStatus(statusCode.value());
      throw se;
    }

  }

  public byte[] getResponseBody(ClientHttpResponse response) {
    try {
      InputStream responseBody = response.getBody();
      if (responseBody != null) {
        return FileCopyUtils.copyToByteArray(responseBody);
      }
    } catch (IOException ex) {
      // ignore
    }
    return new byte[0];
  }

}

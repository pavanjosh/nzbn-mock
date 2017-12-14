package com.example.activity.nbnzdemo.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpException;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.RequestAttributes;

import com.google.gson.Gson;

/**
 * Created by pavankumarjoshi on 5/12/2017.
 */

@ControllerAdvice
public class NZBNExceptionHandler {

  @ExceptionHandler(ServiceException.class)
  public void handleServiceException(ServiceException se, HttpServletResponse response) throws IOException {
    response.sendError(se.getHttpStatus());
//    String body = se.getResponseBodyAsString();
//    Gson gson = new Gson();
//    ServiceException serviceException = gson.fromJson(body, ServiceException.class);
//    throw serviceException;
  }
//  @ExceptionHandler(ResourceAccessException.class)
//  public void handleResourceAccessException(HttpClientErrorException se, HttpServletResponse response) throws IOException {
//    response.sendError(response.getStatus(),se.getMessage());
//    String body = se.getResponseBodyAsString();
//    Gson gson = new Gson();
//    ServiceException serviceException = gson.fromJson(body, ServiceException.class);
//    throw serviceException;
//  }
  @Bean
  public ErrorAttributes errorAttributes() {

    return new DefaultErrorAttributes() {
      @Override
      public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> defaultErrorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
        Throwable error = getError(requestAttributes);
        if(error instanceof ServiceException){
          ServiceException se = (ServiceException) error;
          Map<String, Object> map = new HashMap<>();
          map.put("errorCode", se.getErrorCode());
          map.put("errorMessage", se.getErrorMessage());
          map.put("errorDescription",se.getErrorDescription());
          return map;
        }
        System.out.println("Throwing SpringBoot Exception " + defaultErrorAttributes);
        return defaultErrorAttributes;
      }
    };
  }
}

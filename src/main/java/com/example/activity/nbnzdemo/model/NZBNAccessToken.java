package com.example.activity.nbnzdemo.model;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by pavankumarjoshi on 6/12/2017.
 */


@JsonIgnoreProperties(value = "true")
public class NZBNAccessToken implements Serializable {

  private static final long serialVersionUID = -3870277753944232263L;

  private String scope;
  private String token_type;
  private long expires_in;
  private String access_token;
  private String error;
  private String error_description;
  private DateTime lastFetchTime;

  public DateTime getLastFetchTime() {
    return lastFetchTime;
  }

  public void setLastFetchTime(DateTime lastFetchTime) {
    this.lastFetchTime = lastFetchTime;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public String getToken_type() {
    return token_type;
  }

  public void setToken_type(String token_type) {
    this.token_type = token_type;
  }

  public long getExpires_in() {
    return expires_in;
  }

  public void setExpires_in(long expires_in) {
    this.expires_in = expires_in;
  }

  public String getAccess_token() {
    return access_token;
  }

  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getError_description() {
    return error_description;
  }

  public void setError_description(String error_description) {
    this.error_description = error_description;
  }
}

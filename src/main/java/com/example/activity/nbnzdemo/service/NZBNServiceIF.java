package com.example.activity.nbnzdemo.service;

/**
 * Created by pavankumarjoshi on 5/12/2017.
 */
public interface NZBNServiceIF {

  public String getEntityDetails(String nzbnNumber);
  public String getEntityDetails();
  public String getToken();
}

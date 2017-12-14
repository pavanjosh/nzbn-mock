package com.example.activity.nbnzdemo.wrapper;

import com.example.activity.nbnzdemo.model.NZBNAccessToken;

/**
 * Created by pavankumarjoshi on 5/12/2017.
 */
public interface NZBNWrapperIF {

  public String getEntityDetails(String NZBNNumber,String bearerAccessToken);
  public NZBNAccessToken getServiceToken();
  public NZBNAccessToken getNewServiceToken();

}

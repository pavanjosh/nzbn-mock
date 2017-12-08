package com.example.activity.nbnzdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.activity.nbnzdemo.model.NZBNAccessToken;
import com.example.activity.nbnzdemo.wrapper.NZBNWrapper;
import com.example.activity.nbnzdemo.wrapper.NZBNWrapperIF;

/**
 * Created by pavankumarjoshi on 4/12/2017.
 */

@Service
public class NZBNService implements NZBNServiceIF{


  @Autowired
  private NZBNWrapperIF nzbnWrapper;


  @Override public String getEntityDetails(String nzbnNumber) {

    // call the wrapper to fetch the response
    NZBNAccessToken serviceToken = nzbnWrapper.getServiceToken();
    return nzbnWrapper.getEntityDetails(nzbnNumber,serviceToken.getAccess_token());

  }

  @Override public String getEntityDetails() {
    return null;
  }

  @Override public String getToken() {
    NZBNAccessToken serviceToken = nzbnWrapper.getServiceToken();
    return serviceToken.getAccess_token();
  }
}

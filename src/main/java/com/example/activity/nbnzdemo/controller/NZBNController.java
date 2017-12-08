package com.example.activity.nbnzdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.activity.nbnzdemo.service.NZBNServiceIF;

/**
 * Created by pavankumarjoshi on 4/12/2017.
 */

@RestController
@RequestMapping(value = {"/api"})
public class NZBNController {

  @Autowired
  private NZBNServiceIF nzbnService;

  @RequestMapping(value = "/nzbn/entity/{nzbnnumber}",name="MockIntegration")
  public String getEntityDetailsWIthNZBNNumber(@PathVariable("nzbnnumber") String nzbnNumber){

    // call the service layer to get the data from govt site
    return nzbnService.getEntityDetails(nzbnNumber);
  }

  @RequestMapping(value = "/nzbn/service/token")
  public String getToken(){

    // call the service layer to get the data from govt site
    return nzbnService.getToken();
  }


  @RequestMapping(value = "/nzbn/entity}",name="MockIntegration")
  public String getEntityDetails(){

    // call the service layer to get the data from govt site
    return nzbnService.getEntityDetails();
  }

}



package com.example.activity.nbnzdemo.wrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.xml.ws.Response;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.activity.nbnzdemo.exception.NZBNDefaultErrorHandler;
import com.example.activity.nbnzdemo.model.NZBNAccessToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by pavankumarjoshi on 5/12/2017.
 */
@Service
public class NZBNWrapper implements NZBNWrapperIF {

  @Value("${nzbn.basic}")
  private String basic;

  @Value("${nzbn.entity.url}")
  private String entityUrl;

  @Value("${nzbn.service.token.url}")
  private String serviceTokenUrl;

  @Autowired
  @Qualifier("nZBNClient")
  private RestTemplate template;

  @Override
  public String getEntityDetails(String nZBNNumber,String bearerAccessToken) {

    Map<String, String> uriMap = new HashMap<String, String>();
    uriMap.put("nzbn", nZBNNumber);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("Authorization","Bearer "+bearerAccessToken);
    //headers.add("Accept","application/json");
    HttpEntity<?> entity = new HttpEntity<>(headers);
    System.out.println("Entity headers " +entity.getHeaders());
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(new NZBNDefaultErrorHandler());
    System.out.println("url headers " +restTemplate.getUriTemplateHandler().expand(entityUrl, uriMap));
    ResponseEntity<String> response = restTemplate.exchange(entityUrl, HttpMethod.GET,entity, String.class, uriMap);
    return response.getBody();
  }

  @Cacheable("NZBNAccessToken")
  @Override
  public NZBNAccessToken getServiceToken() {

    return getNZBNToken();
  }

  @CachePut("NZBNAccessToken")
  @Override public NZBNAccessToken getNewServiceToken() {

    return getNZBNToken();
  }

  private NZBNAccessToken getNZBNToken(){
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(serviceTokenUrl)
        .queryParam("grant_type", "client_credentials");

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/x-www-form-urlencoded");
    System.out.println("Basic " +basic);
    headers.add("Authorization","Basic "+basic);

    HttpEntity<?> entity = new HttpEntity<>(headers);
    System.out.println("Entity headers " +entity.getHeaders());
    System.out.println("Final url "+ builder.toUriString());
    ResponseEntity<NZBNAccessToken> response = template.exchange(builder.toUriString(), HttpMethod.POST,entity, NZBNAccessToken.class);
    NZBNAccessToken body = response.getBody();
    body.setLastFetchTime(DateTime.now());
    return body;
  }
}

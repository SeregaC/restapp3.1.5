package org.example;

import org.example.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class Communication {

   private RestTemplate restTemplate;
   private String sessionID;
   private final String URL = "http://94.198.50.185:7081/api/users";

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

 public List<User> showAllUser (){
     ResponseEntity<List<User>> responseEntity =
     restTemplate.exchange(URL, HttpMethod.GET, null,
             new ParameterizedTypeReference<List<User>>() {
     }) ;
     List<User> allUser = responseEntity.getBody();
     sessionID = responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

     return allUser;
 }

 public void saveUser (User user){
     HttpHeaders requestHeaders = new HttpHeaders();
     requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
     requestHeaders.add("Cookie",sessionID);
     HttpEntity requestEntity = new HttpEntity<>(user,requestHeaders);
     ResponseEntity<String> responseEntity =
             restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class);
     System.out.println(responseEntity.getBody());
     // 5ebfeb
 }

    public void update(User user) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        requestHeaders.add("Cookie", sessionID);
        HttpEntity requestEntity = new HttpEntity(user, requestHeaders);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class);
       System.out.println(responseEntity.getBody());
       //e7cb97
    }
    public void delete(User user){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        requestHeaders.add("Cookie", sessionID);
        HttpEntity requestEntity = new HttpEntity(user, requestHeaders);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URL + "/" + user.getId(),
                        HttpMethod.DELETE, requestEntity, String.class);
        System.out.println(responseEntity.getBody());
    }
    //5dfcf9


}

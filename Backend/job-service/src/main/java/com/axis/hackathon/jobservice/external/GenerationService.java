package com.axis.hackathon.jobservice.external;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

@Component
public class GenerationService {

    @Autowired
    RestTemplate restTemplate;


    private static final Logger logger = LoggerFactory.getLogger(GenerationService.class);
    public List<String> getSkills(String jobDescription) {

        String url = "http://127.0.0.1:5000/generate/job/skills?job_description=" + jobDescription;
        logger.info("Calling generation service with url: {}", url);
        ResponseEntity<List<String>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {
                }
        );
        return response.getBody();
    }

    public Void updateApplication(String applicationId,String job_description,String resume_path){

        String url = "http://127.0.0.1:5000/generate/score?job_description=" + job_description + "&resume_path=" + resume_path+"&application_id="+applicationId;
        logger.info("Calling generation service with url: {}", url);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                }
        );
        logger.info("Response from generation service: {}", response.getBody());
        return null;
    }

//    write a method to make a post request to a url using request body as a hashmap. String is the return type from url.
       public String sendMail(Map<String, String> requestBody) {
          String url = "http://127.0.0.1:5000/send/emails";
          logger.info("Calling generation service with url: {}", url);
          restTemplate.postForObject(url,requestBody,String.class);
          return "success";
       }
}

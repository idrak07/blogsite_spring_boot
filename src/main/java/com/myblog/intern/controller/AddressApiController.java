package com.myblog.intern.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/data")
public class AddressApiController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/countries")
    public String getAllCountries(){
        final String uri = "https://restcountries.eu/rest/v2/all";
        RestTemplate restTemplate = new RestTemplate();
        return  restTemplate.getForObject(uri, String.class);
    }
}

package com.myblog.intern.controller;


import com.myblog.intern.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        return  restTemplate.getForObject(uri, String.class);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/post")
    public String getAllPosts(@RequestBody Post details){
        System.out.println(details.toString());

        return details.toString();
    }

}

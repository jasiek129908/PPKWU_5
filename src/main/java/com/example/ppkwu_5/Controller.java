package com.example.ppkwu_5;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class Controller {
    public static final String EXTERNAL_API = "https://panoramafirm.pl/";

    @GetMapping("/{profession}")
    public String getBusinessCard(@PathVariable String profession) {
        String response = null;
        try {
            response = Parse.parseResponse(EXTERNAL_API + "/" + profession);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/card")
    public String getBusinessCard2(@RequestParam String name, @RequestParam String telephone,
                                   @RequestParam String email, @RequestParam String website,
                                   @RequestParam String street, @RequestParam String postalCode,
                                   @RequestParam String addressLocality) {
        System.out.println(name);
        System.out.println(telephone);
        System.out.println(email);
        System.out.println(website);
        System.out.println(street);
        System.out.println(postalCode);
        System.out.println(addressLocality);
        return "";
    }
}



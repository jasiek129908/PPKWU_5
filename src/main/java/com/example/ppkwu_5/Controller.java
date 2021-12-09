package com.example.ppkwu_5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class Controller {
    public static final String EXTERNAL_API = "https://panoramafirm.pl/";

    @GetMapping("/card/{profession}")
    public void getBusinessCard(@PathVariable String profession) {
        try {
            Parse.parseResponse(EXTERNAL_API + "/" + profession);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



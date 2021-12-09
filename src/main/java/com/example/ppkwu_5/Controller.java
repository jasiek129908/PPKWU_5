package com.example.ppkwu_5;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class Controller {
    public static final String EXTERNAL_API = "https://panoramafirm.pl/";

    @GetMapping("/{profession}")
    public String getBusinessCard(@PathVariable String profession) {
        String response = null;
        try {
            response = PPKWU_5.parseResponse(EXTERNAL_API + "/" + profession);
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
        return PPKWU_5.generateCard(name, telephone, email, website, street, postalCode, addressLocality);
    }
}



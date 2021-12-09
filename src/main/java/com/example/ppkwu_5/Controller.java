package com.example.ppkwu_5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api")
public class Controller {
    public static final String EXTERNAL_API = "https://panoramafirm.pl/";

    @GetMapping("/card/{profession}")
    public void getBusinessCard(@PathVariable String profession) {
        WebClient webClient = getWebClientBuilder();
        String out = webClient
                .get()
                .uri(EXTERNAL_API + "/" + profession)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(out);
    }

    public WebClient getWebClientBuilder() {
        return WebClient.builder().exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                .build())
                .build();
    }
}



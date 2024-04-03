package com.shubham.shubham.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

    private static final String API_URL = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_5KifiwnltojSS2cmApeuD7R2ur7TS5h8s9PKgOu4";
    @GetMapping("/convert-currency")
    public double convertCurrency(
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency,
            @RequestParam double amount
    ) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(API_URL, String.class);

        JSONObject jsonObject = new JSONObject(response);
        JSONObject data = jsonObject.getJSONObject("data");

        double fromRate = data.getDouble(fromCurrency);
        double toRate = data.getDouble(toCurrency);
        System.out.println((amount / fromRate) * toRate);

        return (amount / fromRate) * toRate;
    }
}

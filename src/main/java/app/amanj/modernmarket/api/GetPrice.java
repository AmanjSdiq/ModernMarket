package app.amanj.modernmarket.api;

import app.amanj.modernmarket.core.Price;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Log4j2
@RestController
@RequestMapping("get/price")
public class GetPrice {
    @GetMapping("/gold")
    public String getPrice(@RequestParam(defaultValue = "oz") String unit) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.gold-api.com/price/XAU";
        String result = restTemplate.getForObject(url, String.class);
        log.info(result);
        ResponseEntity<Price> exchange = restTemplate.exchange(url, HttpMethod.GET, null, Price.class);
        log.info(exchange.getBody());

        double pricePerOunce = exchange.getBody().getPrice();

        double ouncesPerKg = 32.1507;

        double priceKg = pricePerOunce * ouncesPerKg;

        double priceGram = priceKg / 1000;

        double priceMithqal = priceGram * 4.25;

        return switch (unit) {
            case "oz" -> String.valueOf(pricePerOunce);
            case "kg" -> String.valueOf(priceKg);
            case "g" -> String.valueOf(priceGram);
            case "mithqal" -> String.valueOf(priceMithqal);
            default -> "Invalid unit";
        };
    }
}

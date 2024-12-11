package com.example.EmpRoleManager.WebClientService;


import com.example.EmpRoleManager.Entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class StockWebClientService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    // Add Stock
    public Mono<Map> addStock(Stock stock) {
        return webClientBuilder.build()
                .post()
                .uri("/addstock")
                .bodyValue(stock)
                .retrieve()
                .bodyToMono(Map.class);
    }

    // Get Stock by Category
    public Mono<Map> getStockByCategory(String category) {
        return webClientBuilder.build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getcategorydata")
                        .queryParam("category", category)
                        .build())
                .retrieve()
                .bodyToMono(Map.class);
    }
}


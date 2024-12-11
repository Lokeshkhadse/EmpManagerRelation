package com.example.EmpRoleManager.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class WebclientService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static  final String Stock_url = "http://localhost:2000/stock";

    public Mono<Map> getStockByCategorythorughwebClient(String category) {
        return webClientBuilder.build()
                .get()
                .uri(Stock_url + "/getcategorydata/{category}",category)
                .retrieve()
                .bodyToMono(Map.class);
    }

    public Mono<Map> addStock(Map<String, Object> stockData) {
        return webClientBuilder.build()
                .post()
                .uri(Stock_url + "/add")  // Assuming the add stock endpoint is /add
                .bodyValue(stockData)
                .retrieve()
                .bodyToMono(Map.class);
    }



}

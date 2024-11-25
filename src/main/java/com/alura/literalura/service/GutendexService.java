// src/main/java/com/alura/literalura/service/GutendexService.java
package com.alura.literalura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;

@Service
public class GutendexService {
    private final String BASE_URL = "https://gutendex.com/books/";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode buscarLibro(String titulo) {
        String url = BASE_URL + "?search=" + titulo.replace(" ", "%20");
        String response = restTemplate.getForObject(url, String.class);
        try {
            JsonNode root = objectMapper.readTree(response);
            if (root.get("results").size() > 0) {
                return root.get("results").get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
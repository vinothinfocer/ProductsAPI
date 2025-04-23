package com.api.products.controller;

import com.api.products.entity.Tags;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ProductController {

    private static final String url = "https://dummyjson.com/products";

    @GetMapping("/products")
    public ResponseEntity<Void> getProducts() {

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("topPriceProduct")
    public ResponseEntity<List<String>> getTagByTopPriceProducts() throws URISyntaxException, IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder(new URI(url)).GET().build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement json = JsonParser.parseString(response.body());
        JsonArray products = json.getAsJsonObject().get("products").getAsJsonArray();
        int i=0;

        Set<String> result = new HashSet<>();
        while(i<products.size()) {
            JsonObject product = products.get(i).getAsJsonObject();
            double price = product.get("price").getAsDouble();

            if(price>10) {
//                Type listType = new TypeToken<List<String>>() {}.getType();
               ArrayList<String> li = new Gson().fromJson(product.getAsJsonArray("tags"), ArrayList.class);
               result.addAll(li);
            }
            i++;
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>(result));
//        return 0;
    }
}

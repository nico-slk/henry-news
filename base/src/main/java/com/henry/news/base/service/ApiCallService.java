package com.henry.news.base.service;

import com.google.gson.Gson;
import com.henry.news.base.model.response.ApiWeatherResponse;
import com.henry.news.base.model.response.Location;
import com.henry.news.base.model.response.Main;
import com.henry.news.base.model.response.OpenWeatherResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

@Service
@Slf4j
public class ApiCallService {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    @CircuitBreaker(name = "ApiWeather",fallbackMethod = "fallback")
    public ApiWeatherResponse callAPI() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.weatherapi.com/v1/current.json?key=f1852745fb8c45cfbd4224130211404&q=Mendoza&aqi=no"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        final ApiWeatherResponse apiWeatherResponse = new Gson().fromJson(response.body(), ApiWeatherResponse.class);

        if(RandomUtils.nextBoolean()){
            throw new IOException("Probando Circuit Breaker...");
        }

        return apiWeatherResponse;
    }

    private ApiWeatherResponse fallback(final Throwable t) throws IOException, InterruptedException{
        log.error(t.getStackTrace().toString()); //todo el error
        return ApiWeatherResponse.builder().build();
    }

    @CircuitBreaker(name = "ApiWeather2",fallbackMethod = "fallback2")
    public OpenWeatherResponse callAPI2() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.weatherapi.com/v1/current.json?key=f1852745fb8c45cfbd4224130211404&q=Mendoza&aqi=no"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


        final OpenWeatherResponse openWeatherResponse = new Gson().fromJson(response.body(), OpenWeatherResponse.class);

        if(RandomUtils.nextBoolean()){
            throw new IOException("Probando Circuit Breaker...");
        }


        return openWeatherResponse;
    }

    private OpenWeatherResponse fallback2(final Throwable t){
        log.error(t.getStackTrace().toString()); //todo el error
        Main main = new Main(0d,0d,0d,0d,0,0);
        return OpenWeatherResponse
                .builder()
                .main(main)
                .name("cayeron ambas Api's")
                .build();
    }


}

package com.yashdev.journalApp.services;

import com.yashdev.journalApp.api.response.WeatherResponse;
import com.yashdev.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    
    @Value("${weather.api.key}")
    private  String apikey;
    //https://api.openweathermap.org/data/2.5/weather?q=${city.toLowerCase()}&appid=${apiKey}


//    @Value("${weather.api.url}")
//    private String apiUrl;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_"+ city,WeatherResponse.class);
        if(weatherResponse != null){
            return weatherResponse;
        }else{
            String finalAPi = appCache.APP_CACHE.get(AppCache.keys.WEATHER_API.toString()) +
                    "?q=" + city.toLowerCase() +
                    "&appid=" + apikey;
            //The process of converting the JSON response from the API into a Java object(POJO (Plain old Java Object)) is called deserialization.
            ResponseEntity<WeatherResponse>response =  restTemplate.exchange(finalAPi, HttpMethod.GET,null, WeatherResponse.class);
            WeatherResponse body =  response.getBody();
            if(body != null){
                redisService.set("weather_of_"+ city, body, 300L);
            }
            return body;
        }

    }

}

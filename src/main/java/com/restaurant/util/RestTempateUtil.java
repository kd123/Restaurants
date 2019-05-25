package com.restaurant.util;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Kuldeep Gupta
 */
public class RestTempateUtil {

    private static final Integer CONNNECT_TIMEOUT = 5000; //5 seconds

    private static final Integer READ_TIMEOUT = 5000; //5 seconds

    private static RestTemplate restTemplate;

    public static RestTemplate getRestTemplate(){
        if( restTemplate != null) {
            return restTemplate;
        }
        //HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        //httpRequestFactory.setConnectTimeout(CONNNECT_TIMEOUT);
        //httpRequestFactory.setReadTimeout(READ_TIMEOUT);
        restTemplate = new RestTemplate();
        return restTemplate;
    }
}

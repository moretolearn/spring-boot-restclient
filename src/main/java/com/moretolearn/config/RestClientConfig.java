package com.moretolearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.moretolearn.client.JsonPlaceholderClient;

@Configuration
public class RestClientConfig {
	
	@Bean
	JsonPlaceholderClient jsonPlaceholderClient() {
		return createClient(JsonPlaceholderClient.class, "https://jsonplaceholder.typicode.com");
	}
	
	public <T> T createClient(Class<T> clientType, String baseUrl) {
        RestClient restClient = RestClient.builder().baseUrl(baseUrl).build();
        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(clientType);
    }
}

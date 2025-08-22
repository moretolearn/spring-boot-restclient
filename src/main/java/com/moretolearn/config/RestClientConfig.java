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
		return createClientWithOutTOken(JsonPlaceholderClient.class, "https://jsonplaceholder.typicode.com");
	}

	public <T> T createClientWithOutTOken(Class<T> clientType, String baseUrl) {
		RestClient restClient = RestClient.builder().baseUrl(baseUrl).build();
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
				.build();
		return factory.createClient(clientType);
	}

	public <T> T createClientWithStaticToken(Class<T> clientType, String baseUrl) {
		RestClient restClient = RestClient.builder().baseUrl(baseUrl)
				.defaultHeader("Authorization", "Bearer YOUR_TOKEN_HERE")
				.defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
				.build();
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
				.build();
		return factory.createClient(clientType);
	}

	public <T> T createClientWithDynamicToken(Class<T> clientType, String baseUrl) {
		RestClient restClient = RestClient.builder().baseUrl(baseUrl).requestInterceptor((request, body, execution) -> {
			String token = "Bearer " + "token from server";
			request.getHeaders().set("Authorization", token);
			request.getHeaders().set("Content-Type", "application/json");
			request.getHeaders().set("Accept", "application/json");
			return execution.execute(request, body);
		}).build();
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
				.build();
		return factory.createClient(clientType);
	}

}

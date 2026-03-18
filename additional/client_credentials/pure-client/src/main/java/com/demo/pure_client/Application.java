package com.demo.pure_client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// ClientRegistrationRepository được Spring Boot tự động tạo ra dựa trên config
	// trong application.properties
	// Lưu trữ OAuth2AuthorizedClient (chứa access_token)
	// Bên trong OAuth2AuthorizedClientService có function call để lấy token mới nên
	// cần load config vào
	@Bean
	public OAuth2AuthorizedClientService auth2AuthorizedClientService(
			ClientRegistrationRepository clientRegistrationRepository) {
		return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
	}

	// Manager điều phối việc lấy token
	// Nếu có → dùng lại
	// Nếu không → lấy mới (client_credentials)
	// Refresh token khi hết hạn
	@Bean
	public OAuth2AuthorizedClientManager auth2AuthorizedClientManager(
			ClientRegistrationRepository repos,
			OAuth2AuthorizedClientService service) {
		var manager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(repos, service);
		OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder()
				.clientCredentials()
				.build();
		// Muốn lấy token thì dùng flow client_credentials
		manager.setAuthorizedClientProvider(provider);
		return manager;
	}

	// Client lấy token từ Keycloak để gọi Service 2
	// "keycloak-client" → mapping config trong application.properties
	// "machine" → đại diện cho system
	@Bean
	public CommandLineRunner run(
			OAuth2AuthorizedClientManager manager,
			RestTemplate rest,
			@Value("${service2.url}") String service2Url) {
		return args -> {
			var authRequest = OAuth2AuthorizeRequest
					.withClientRegistrationId("keycloak-client")
					.principal("machine")
					.build();

			var client = manager.authorize(authRequest);
			String token = client.getAccessToken().getTokenValue();

			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(token);

			var resp = rest.exchange(
					service2Url + "/data",
					HttpMethod.GET,
					new HttpEntity<>(headers),
					String.class);

			System.out.println("Response from Service 2: " + resp.getBody());

		};
	}

}

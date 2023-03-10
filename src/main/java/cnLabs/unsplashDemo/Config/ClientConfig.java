package cnLabs.unsplashDemo.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Configuration
public class ClientConfig {

    @Value("${search.uri}")
    private URI searchUri;

    @Value("${api.client-id}")
    private String secret;

    @Value("${searchPexels.uri}")
    private URI searchUriPexels;

    @Value("${apiPexels.client-id}")
    private String secretPexels;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(searchUri.toString())
                .defaultHeader(HttpHeaders.AUTHORIZATION, secret)
                .build();
    }

    @Bean
    public WebClient webClientPexels() {
        return WebClient.builder()
                .baseUrl(searchUriPexels.toString())
                .defaultHeader(HttpHeaders.AUTHORIZATION, secretPexels)
                .build();
    }
}
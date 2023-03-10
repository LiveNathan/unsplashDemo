package cnLabs.unsplashDemo.Service;

import cnLabs.unsplashDemo.Model.Photo;
import cnLabs.unsplashDemo.Model.UnsplashResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class UnsplashService {

    @Autowired
    WebClient webClient;

    public Flux<Photo> getPhotos(String searchText) {
        // get the total number of pages for this search term
        return getTotalPages(searchText)
                // if total pages is greater than 5, set to 5 since Unsplash limits requests-per-hour
                .flatMapMany(t -> Flux.range(1, t > 5 ? 5 : t))
                // for each page in the range, query the search API
                .flatMap(f -> searchUnsplash(searchText, f)
                        // when a response is received, get the results
                        .flatMapIterable(UnsplashResponse::getResults), 5);
    }

    public Mono<Integer> getTotalPages(String searchText) {
        return webClient.get()
                .uri(uri -> uri
                        .queryParam("page", "1")
                        .queryParam("query", searchText).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(UnsplashResponse.class)
                .map(UnsplashResponse::getTotalPages)
                .map(Integer::valueOf);
    }

    public Mono<UnsplashResponse> searchUnsplash(String searchText, int pageNumber) {
        return Mono.delay(Duration.ofSeconds(4))
                .then(webClient.get()
                        .uri(uri -> uri
                                .queryParam("page", pageNumber)
                                .queryParam("query", searchText)
                                .build())
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(UnsplashResponse.class));
    }

}
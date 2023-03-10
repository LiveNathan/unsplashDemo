package cnLabs.unsplashDemo.Service;

import cnLabs.unsplashDemo.Model.Photo;
import cnLabs.unsplashDemo.Model.UnsplashResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UnsplashService {

    @Autowired
    WebClient webClient;

    public Flux<Photo> getPhotos(String searchText, String orientation) {
        // get the total number of pages for this search term
        return getTotalPages(searchText, orientation)
                // if total pages is greater than 5, set to 5 since Unsplash limits requests-per-hour
                .flatMapMany(t -> Flux.range(1, t > 5 ? 5 : t))
                // for each page in the range, query the search API
                .flatMap(f -> searchUnsplash(searchText, f, orientation)
                        // when a response is received, get the results
                        .flatMapIterable(UnsplashResponse::getResults), 5);
    }

    public Mono<Integer> getTotalPages(String searchText, String orientation) {
        return webClient.get()
                .uri(uri -> uri
                        .queryParam("page", "1")
                        .queryParam("query", searchText)
                        .queryParam("orientation", orientation)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(UnsplashResponse.class)
                .map(UnsplashResponse::getTotalPages)
                .map(Integer::valueOf);  // Can be omitted.
    }

    public Mono<UnsplashResponse> searchUnsplash(String searchText, int pageNumber, String orientation) {
        return webClient.get()
                .uri(uri -> uri
                        .queryParam("page", pageNumber)
                        .queryParam("query", searchText)
                        .queryParam("orientation", orientation)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UnsplashResponse.class);
//                .delayElement(Duration.ofSeconds(1));
    }

}
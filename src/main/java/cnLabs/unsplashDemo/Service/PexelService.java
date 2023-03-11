package cnLabs.unsplashDemo.Service;

import cnLabs.unsplashDemo.Model.PexelsResponse;
import cnLabs.unsplashDemo.Model.PhotoPexel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PexelService {

    @Autowired
    @Qualifier("webClientPexels")
    WebClient webClient;

    public Flux<PhotoPexel> getPhotos(String searchText, String orientation) {
        if (orientation.equals("squarish")) {
            orientation = "square";
        }
        String finalOrientation2 = orientation;

        return getTotalPages(searchText, orientation)
                // if total pages is greater than 5, set to 5 since Pexel limits requests-per-hour
                .flatMapMany(totalPages -> Flux.range(1, totalPages > 5 ? 5 : totalPages))
                // for each page in the range, query the search API
                .flatMap(pageNumber -> searchPexel(searchText, pageNumber, finalOrientation2)
                        // when a response is received, get the results
                        .flatMapIterable(PexelsResponse::getPhotos), 5)
                .switchIfEmpty(Flux.error(new RuntimeException("No results found. Please try a different search.")));
    }

    public Mono<Integer> getTotalPages(String searchText, String orientation) {
        if (orientation.equals("squarish")) {
            orientation = "square";
        }
        String finalOrientation = orientation;  // Not sure why this is necessary, but Intellij suggested it.

        return webClient.get()
                .uri(uri -> uri
                        .queryParam("page", "1")
                        .queryParam("query", searchText)
                        .queryParam("orientation", finalOrientation)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(PexelsResponse.class)
                .map(PexelsResponse::getTotalPages)
                .flatMap(totalPages -> {
                    if (totalPages == 0) {
                        return Mono.empty();
                    } else {
                        return Mono.just(totalPages);
                    }
                });
    }

    public Mono<PexelsResponse> searchPexel(String searchText, int pageNumber, String orientation) {
        if (orientation.equals("squarish")) {
            orientation = "square";
        }
        String finalOrientation1 = orientation;

        return webClient.get()
                .uri(uri -> uri
                        .queryParam("page", pageNumber)
                        .queryParam("query", searchText)
                        .queryParam("orientation", finalOrientation1)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PexelsResponse.class);
//                .delayElement(Duration.ofSeconds(1));
    }

}
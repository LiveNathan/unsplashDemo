package cnLabs.unsplashDemo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PexelsResponse {

    /**
     * page : 1
     * per_page : 15
     * photos : []
     */

    private int page;
    @JsonProperty("per_page")
    private int perPage;
    @JsonProperty("total_results")
    private int totalResults;
    @JsonIgnore
    private int totalPages;
    private List<PhotoPexel> photos;

    public int getTotalPages() {
        int totalPages = totalResults % perPage == 0 ? totalResults / perPage : totalResults / perPage + 1;
        return totalPages;
    }
}

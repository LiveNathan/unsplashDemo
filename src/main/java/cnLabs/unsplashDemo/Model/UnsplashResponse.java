package cnLabs.unsplashDemo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UnsplashResponse {

    /**
     * total : 3075
     * total_pages : 308
     * results : [{}]
     */

    private int total;
    @JsonProperty("total_pages")
    private int totalPages;
    private List<Photo> results;

}

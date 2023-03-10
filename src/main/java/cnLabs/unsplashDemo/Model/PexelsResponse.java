package cnLabs.unsplashDemo.Model;

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
    private int per_page;
    private List<PhotoPexel> photos;
}

package cnLabs.unsplashDemo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PhotoPexel {

    /**
     * id : 15286
     * width : 2500
     * height : 1667
     * url : https://www.pexels.com/photo/person-walking-between-green-forest-trees-15286/
     * photographer : Luis del Río
     * photographer_url : https://www.pexels.com/@luisdelrio
     * photographer_id : 1081
     * avg_color : #283419
     * src : {}
     */

    private int id;
    private int width;
    private int height;
    private String url;
    private String photographer;
    @JsonProperty("photographer_url")
    private String photographerUrl;
    @JsonProperty("photographer_id")
    private int photographerId;
//    private String avg_color;
    private UrlsPexel urls;
}

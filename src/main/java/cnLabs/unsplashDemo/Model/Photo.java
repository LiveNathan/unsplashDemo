package cnLabs.unsplashDemo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Photo {

    /**
     * id : Kl3467edwsE
     * created_at : 2020-04-17T14:04:52Z
     * updated_at : 2023-03-09T20:13:12Z
     * promoted_at : null
     * width : 4048
     * height : 4048
     * color : #f3f3f3
     * blur_hash : LfSFz@Rjo~ogxuaeRiay.At7aJax
     * description : High-quality photo of a banana on a white background
     * alt_description : yellow banana on white background
     * urls : {}
     */

    private String id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private int width;
    private int height;
//    private String description;
    @JsonProperty("alt_description")
    private String description;
    private Urls urls;
}

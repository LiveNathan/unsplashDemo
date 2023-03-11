package cnLabs.unsplashDemo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Urls {

    /**
     * raw : https://images.unsplash.com/photo-1587132137056-bfbf0166836e?ixid=Mnw0MjA0MjN8MHwxfHNlYXJjaHwyfHxiYW5hbmF8ZW58MHx8fHwxNjc4NDEzMDMz&ixlib=rb-4.0.3
     * full : https://images.unsplash.com/photo-1587132137056-bfbf0166836e?crop=entropy&cs=tinysrgb&fm=jpg&ixid=Mnw0MjA0MjN8MHwxfHNlYXJjaHwyfHxiYW5hbmF8ZW58MHx8fHwxNjc4NDEzMDMz&ixlib=rb-4.0.3&q=80
     * regular : https://images.unsplash.com/photo-1587132137056-bfbf0166836e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=Mnw0MjA0MjN8MHwxfHNlYXJjaHwyfHxiYW5hbmF8ZW58MHx8fHwxNjc4NDEzMDMz&ixlib=rb-4.0.3&q=80&w=1080
     * small : https://images.unsplash.com/photo-1587132137056-bfbf0166836e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=Mnw0MjA0MjN8MHwxfHNlYXJjaHwyfHxiYW5hbmF8ZW58MHx8fHwxNjc4NDEzMDMz&ixlib=rb-4.0.3&q=80&w=400
     * thumb : https://images.unsplash.com/photo-1587132137056-bfbf0166836e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=Mnw0MjA0MjN8MHwxfHNlYXJjaHwyfHxiYW5hbmF8ZW58MHx8fHwxNjc4NDEzMDMz&ixlib=rb-4.0.3&q=80&w=200
     * small_s3 : https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1587132137056-bfbf0166836e
     */

    private String raw;
    private String full;
    private String regular;
    private String small;
    private String thumb;
    @JsonProperty("small_s3")
    private String smallS3;

}

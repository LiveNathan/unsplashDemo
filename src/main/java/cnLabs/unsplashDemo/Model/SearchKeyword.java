package cnLabs.unsplashDemo.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchKeyword {
    private String text;
    private String orientation;
    private String source;
}
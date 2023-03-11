package cnLabs.unsplashDemo.Controller;

import cnLabs.unsplashDemo.Model.Photo;
import cnLabs.unsplashDemo.Model.SearchKeyword;
import cnLabs.unsplashDemo.Service.PexelService;
import cnLabs.unsplashDemo.Service.UnsplashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

@Controller
public class ViewController {

    @Autowired
    UnsplashService unsplashService;

    @Autowired
    PexelService pexelService;

    @GetMapping("/")
    public String displayIndex(Model model) {
        model.addAttribute("searchKeyword", new SearchKeyword());
        return "index";
    }

    @PostMapping("/")
    public String performSearch(@ModelAttribute("searchKeyword") SearchKeyword searchKeyword, Model model) {
        ReactiveDataDriverContextVariable reactiveData = null;
        if (searchKeyword.getSource().equals("unsplash")) {
            reactiveData =
                    new ReactiveDataDriverContextVariable(unsplashService.getPhotos(searchKeyword.getText(), searchKeyword.getOrientation())
                            .onErrorResume(error -> {
                                model.addAttribute("errorMessage", "Test message");
                                return Flux.empty();
                            }), 1);
        } else if (searchKeyword.getSource().equals("pexels")) {
            reactiveData =
                    new ReactiveDataDriverContextVariable(pexelService.getPhotos(searchKeyword.getText(), searchKeyword.getOrientation())
                            .onErrorResume(error -> {
                                model.addAttribute("errorMessage", "Test message");
                                return Flux.empty();
                            }), 1);
        }
        model.addAttribute("photos", reactiveData);
        model.addAttribute("searchText", searchKeyword.getText());
        model.addAttribute("orientation", searchKeyword.getOrientation());
        return "index";
    }


}
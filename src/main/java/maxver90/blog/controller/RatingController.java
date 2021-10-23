package maxver90.blog.controller;


import maxver90.blog.entity.Rating;
import maxver90.blog.repository.ArticleRepository;
import maxver90.blog.repository.RatingRepository;
import maxver90.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/rating")
public class RatingController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @PostMapping(path = "/add")
    public String addRating(
            Authentication authentication,
            @RequestParam Long article_id,
            @RequestParam Integer rating,
            Model model
    ){
        String username = authentication.getName();
        Rating ratingValue = new Rating();
        ratingValue.setUser(userRepository.findByLogin(username).orElse(null));
        ratingValue.setArticle(articleRepository.findById(article_id).orElse(null));
        ratingValue.setRating(rating);
        ratingRepository.save(ratingValue);
        model.addAttribute("articleId", article_id);
        return "redirect:/articles/view/" + article_id;
    }
}

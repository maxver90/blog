package maxver90.blog.controller;

import maxver90.blog.entity.Article;
import maxver90.blog.entity.Category;
import maxver90.blog.entity.Rating;
import maxver90.blog.entity.User;
import maxver90.blog.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/articles")
public class ArticleController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private EntityManager manager;

    @GetMapping("/view/best")
    public String bestArticles(Model model) {
        TypedQuery<Article> bestArticlesQuery = manager.createQuery(
                "select a from Article a join Rating r on a.id = r.article.id group by a.id order by avg(r.rating)",
                Article.class
        );
        List<Article> bestArticleList = bestArticlesQuery.getResultList();
        model.addAttribute("bestArticles", bestArticleList);
        return "public/best_articles";
    }

    @GetMapping(path = "/view/{id}")
    public String viewArticleInfo(@PathVariable Long id, Model model, Authentication authentication) {
        model.addAttribute("article", articleRepository.getById(id));
        if (authentication != null) {
            String username = authentication.getName();
            model.addAttribute("rating",
                    ratingRepository.existsByUserAndArticle(userRepository.findByLogin(username).get(),
                            articleRepository.findById(id).get()));
        } else {
            model.addAttribute("rating", false);
        }
        Article article = articleRepository.findById(id).orElse(null);
        List<Rating> ratingList = article.getRatings();
        double count = 0;
        for (Rating rating : ratingList) {
            count += rating.getRating();
        }
        if (ratingList.size() != 0) {
            double averageRating = count / ratingList.size();
            model.addAttribute("averageRating", averageRating);
        } else {
            model.addAttribute("averageRating", 0);
        }
        return "public/view_article_info";

    }

    @GetMapping(path = "/create")
    public String showCreateArticlePage(Model model) {
        List<Category> categoryList = new ArrayList<>(categoryRepository.findAll());
        model.addAttribute("categoryList", categoryList);
        return "public/create_article";
    }

    @PostMapping(path = "/create")
    public String proceedArticleCreation(Authentication authentication,
                                         @RequestParam Long category_id,
                                         @RequestParam String heading,
                                         @RequestParam String text
    ) {
        String username = authentication.getName();
        Article article = new Article();
        article.setUser(userRepository.findByLogin(username).get());
        article.setCategory(categoryRepository.getById(category_id));
        article.setHeading(heading);
        article.setText(text);
        article.setIsPublished(false);
        article.setDate(LocalDate.now());
        articleRepository.save(article);
        return "public/publish_article";
    }
}

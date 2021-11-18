package maxver90.blog.controller;
import maxver90.blog.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Controller
@RequestMapping(path = "/main")
public class MainPageController {

    @Autowired
    private EntityManager manager;

    @GetMapping(path = "/navi")
    public String getMainPage(Model model, Authentication authentication) {
        TypedQuery<Article> articleTypedQuery = manager.createQuery(
                "select a from Article a group by a.id", Article.class
        );
        List<Article> articleList = articleTypedQuery.getResultList();
        model.addAttribute("articleList", articleList);
        return "public/main_page";
    }
}
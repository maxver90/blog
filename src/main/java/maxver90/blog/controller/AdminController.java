package maxver90.blog.controller;

import maxver90.blog.entity.Article;
import maxver90.blog.entity.User;
import maxver90.blog.entity.UserRole;
import maxver90.blog.repository.ArticleRepository;
import maxver90.blog.repository.UserRepository;
import maxver90.blog.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Optional;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager manager;

    @GetMapping(path = "/article_list")
    public String showArticles(@RequestParam Optional<Boolean> articleStatus, Model model) {
        List<Article> articleList;
        if (articleStatus.isPresent()) {
            articleList = articleRepository.findAllByIsPublished(articleStatus.get());
        } else {
            articleList = articleRepository.findAll();
        }
        model.addAttribute("articleList", articleList);
        return "/public/admin_articles_page";
    }

    @GetMapping(path = "/articles")
    public String adminPage(Model model) {
        TypedQuery<Article> articleTypedQuery = manager.createQuery(
                "select a from Article a group by a.id", Article.class
        );
        List<Article> articleList = articleTypedQuery.getResultList();
        model.addAttribute("articleList", articleList);
        return "/public/admin_articles_page";
    }

    @PostMapping(path = "/articles_edit")
    public String adminEditArticle(
            @RequestParam boolean is_published,
            @RequestParam Long article_id
    ) {
        Article article = articleRepository.findById(article_id).get();
        article.setIsPublished(is_published);
        articleRepository.save(article);
        return "redirect:/admin/articles";
    }

    @GetMapping(path = "/users")
    public String adminUserRoles(Model model,
                                 @RequestParam Optional<Long> userRoleId) {
        TypedQuery<UserRole> userRoleTypedQuery = manager.createQuery(
                "select u from UserRole u group by u.id", UserRole.class
        );
        List<UserRole> userRoleList = userRoleTypedQuery.getResultList();
        List<User> userList;
        if (userRoleId.isPresent()) {
            userList = userRepository.findAllByUserRoleId(userRoleId.get());
        } else {
            userList = userRepository.findAll();
        }
        model.addAttribute("userRoleList", userRoleList);
        model.addAttribute("userList", userList);
        return "/public/admin_user_roles_page";
    }

    @PostMapping(path = "/users_edit")
    public String adminUserRolesEdit(
            @RequestParam Long user_id,
            @RequestParam UserRole user_role
    ) {
        User user = userRepository.findById(user_id).get();
        user.setUserRole(user_role);
        userRepository.save(user);
        return "redirect:/admin/users";
    }
}

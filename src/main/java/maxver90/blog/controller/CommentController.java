package maxver90.blog.controller;

import maxver90.blog.entity.Article;
import maxver90.blog.entity.Category;
import maxver90.blog.entity.Comment;
import maxver90.blog.entity.User;
import maxver90.blog.repository.ArticleRepository;
import maxver90.blog.repository.CategoryRepository;
import maxver90.blog.repository.CommentRepository;
import maxver90.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/comment")
public class CommentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping(path = "/add")
    public String addComment(Authentication authentication,
                             @RequestParam Long article_id,
                             @RequestParam String text,
                             Model model) {
        String username = authentication.getName();
        Comment comment = new Comment();
        comment.setUser(userRepository.findByLogin(username).orElse(null));
        comment.setArticle(articleRepository.findById(article_id).get());
        comment.setText(text);
        comment.setDate(LocalDate.now());
        model.addAttribute("user", userRepository.findByLogin(username).orElse(null));
        model.addAttribute("articleId", article_id);
        commentRepository.save(comment);
        return "redirect:/articles/view/" + article_id;
        //return "/public/add_comment";
    }
}

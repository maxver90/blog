package maxver90.blog.controller;

import maxver90.blog.entity.Article;
import maxver90.blog.entity.User;
import maxver90.blog.repository.ArticleRepository;
import maxver90.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(path = "/show")
    public String showProfile(Model model, Authentication authentication) {
        String login = authentication.getName();
        model.addAttribute("user", userRepository.findByLogin(login).orElse(null));
        return "public/showProfile";
    }
}

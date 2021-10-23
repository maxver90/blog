package maxver90.blog.controller;

import maxver90.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/show")
    public String showProfile(Model model, Authentication authentication) {
        String login = authentication.getName();
        model.addAttribute("user", userRepository.findByLogin(login).orElse(null));
        return "public/showProfile";
    }
}

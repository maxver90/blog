package maxver90.blog.controller;

import maxver90.blog.entity.User;
import maxver90.blog.repository.UserRepository;
import maxver90.blog.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping(path = "/registration")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

//     GET /registration -> showRegistrationPage

    @GetMapping
    public String showRegistrationPage() {
        return "public/registration_page";
    }

//     POST /registration -> proceedRegistration

    @PostMapping
    public String proceedRegistration(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String login,
            @RequestParam String password
    ) {
        User user = new User();
        user.setName(firstName);
        user.setSurname(lastName);
        user.setLogin(login);
        user.setPassword(password);
        user.setUserRole(userRoleRepository.getById(6L));
        user.setDate(LocalDateTime.now());
        userRepository.save(user);
        return "public/success_registration";
    }
}

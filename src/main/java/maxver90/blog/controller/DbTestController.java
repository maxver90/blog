package maxver90.blog.controller;

import maxver90.blog.entity.UserRole;
import maxver90.blog.repository.UserRepository;
import maxver90.blog.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/db_test")
public class DbTestController {

    // Репозиторий - группа объектов, которые предоставляют функциональность для достуа и работы с данными.
    // Для каждой сущности должен описываться отдельный репозиторий.

    @Autowired
    private UserRoleRepository userRoleRepository;

    @GetMapping(path = "/part1")
    public String firstPart(Model model) {
        UserRole userRole = userRoleRepository.getById(1L);
        List<UserRole> userRoleList = userRoleRepository.findAll();
        System.out.println(userRoleList);
        model.addAttribute("userRoleList", userRoleList);
        return "test/db_part_1";
    }
}

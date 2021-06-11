package com.intech.test.controller;


import com.intech.test.domain.Role;
import com.intech.test.domain.User;
import com.intech.test.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          Model model) {
        User userFromDb = userRepo.findByUsername(username);
        System.out.println(username);
        if (userFromDb != null) {
            model.addAttribute("message", "Такой пользователь существует");
            return "registration";
        }

        User newUser = new User(username, password);
        newUser.setActive(true);
        newUser.setRoles(Collections.singleton(Role.USER));
        userRepo.save(newUser);

        return "redirect:/login";
    }
}

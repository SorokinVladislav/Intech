package com.intech.test.controller;

import com.intech.test.domain.Message;
import com.intech.test.domain.User;
import com.intech.test.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal User user,
                       Model model) {
        List<Message> messages = (List<Message>) messageRepo.findAll();
        model.addAttribute("messages", messages);
        model.addAttribute("user", user);
        return "main";
    }

    @GetMapping("/")
    public String main2 (Model model) {
        return "redirect:/main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            Model model
    ) {
        Message message = new Message(text, user);
        messageRepo.save(message);

        return "redirect:/main";
    }

}

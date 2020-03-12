package com.example.demo.controller;

import com.example.demo.domain.ROLE;
import com.example.demo.domain.User;
import com.example.demo.repos.UserRepo;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String addUser(User user, Map<String, Object> model) {
       User userFromDb =  userRepo.findByUsername(user.getUsername());
       if(userFromDb != null ) {
           model.put("message", "User Exist");
           return "registration";
       }

       user.setActive(true);
       user.setRoles(Collections.singleton(ROLE.USER));
       userRepo.save(user);

        return "redirect:/login";
    }
}
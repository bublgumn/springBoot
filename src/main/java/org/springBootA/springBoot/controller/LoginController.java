package org.springBootA.springBoot.controller;

import org.springBootA.springBoot.model.Role;
import org.springBootA.springBoot.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @GetMapping("/newLogin/user")
    public String RedirectNewUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        for (Role role : user.getRole()
        ) {
            if (role.getName().equals("ROLE_admin")) {
                return "redirect:/admin";
            }
        }
        return "redirect:/user";
    }
}

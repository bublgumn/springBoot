package org.springBootA.springBoot.controller;

import org.springBootA.springBoot.model.Role;
import org.springBootA.springBoot.model.User;
import org.springBootA.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.findAll());
        return "adminPage";
    }

    @PostMapping("/newuser")
    public String createUser(@ModelAttribute User user,
                             @RequestParam(required = false, name = "adminparam") String adminParam) {
        Role roleUser;
        Role roleAdmin;

        if (adminParam != null) {
            roleAdmin = userService.findRoleByName("ROLE_admin");
            roleUser = userService.findRoleByName("ROLE_user");
            user.setRole(new ArrayList<>());
            user.getRole().add(roleAdmin);
            user.getRole().add(roleUser);
            userService.saveUser(user);
        } else {
            roleUser = userService.findRoleByName("ROLE_user");
            user.setRole(new ArrayList<>());
            user.getRole().add(roleUser);
            userService.saveUser(user);
        }
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Long id) {
        System.out.println(id);
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/updateUser")
    public String updateUserPage(@RequestParam Long id, ModelMap model) {
        model.addAttribute("user", userService.findById(id));
        return "updateUserPage";
    }

    @PostMapping("/updateUser")
    public String editCar(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

}
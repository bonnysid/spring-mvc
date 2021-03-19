package org.bonnysid.bloom.controllers;

import org.bonnysid.bloom.models.user.User;
import org.bonnysid.bloom.models.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserDAO userDAO;

    @Autowired
    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String get(Model model) {
        model.addAttribute("users", userDAO.get());
        return "users/users";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.get(id));
        return "users/user";
    }

    @GetMapping("/new")
    public String save(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userDAO.insert(user);
        return "redirect:/users";
    }
}

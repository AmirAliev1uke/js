package ru.kata.spring.boot_security.demo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/user")
    public String userPage(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        return "userPage";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("roles", roleService.roleList());
        return "adminPage";
    }

    @PostMapping("/admin/new_user")
    public String addUser(@RequestParam ArrayList<Integer> roles, @RequestParam String name,
                          @RequestParam String lastName, @RequestParam String password, @RequestParam String email, @RequestParam Integer age) {
        userService.add(roles, name, lastName, password, email, age);
        return "redirect:/admin";
    }

    @PatchMapping("/admin/save")
    public String updateUser(@RequestParam ArrayList<Integer> roles, @RequestParam String name,
                             @RequestParam String lastName, @RequestParam String password, @RequestParam String email, @RequestParam Integer age, @RequestParam Integer id) {
        userService.updateUser(roles, name, lastName, password, email, age, id);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}

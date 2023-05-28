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
@RequestMapping()
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
        model.addAttribute("user", userService.getUserByName(principal.getName()));
        return "userPage";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal) {
        model.addAttribute("admin", userService.getUserByName(principal.getName()));
        return "adminPage";
    }

    @GetMapping("/admin/user_list")
    public String getUsersList(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "/userList";
    }
    @GetMapping("/admin/user_list/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/showUser";
    }

    @GetMapping("/admin/new_user")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.roleList());
        return "/addUser";
    }

    @PostMapping("/admin/new_user")
    public String addUser(@RequestParam ArrayList<Integer> roles, @RequestParam String name,
                          @RequestParam String lastName, @RequestParam String password, @RequestParam String email) {
        userService.add(roles, name, lastName, password, email);
        return "redirect:/admin/user_list";
    }

    @GetMapping("/admin/user_list/{id}/update")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("roles", roleService.roleList());
        model.addAttribute("user", userService.getUserById(id));
        return "/updateUser";
    }

    @PatchMapping("/admin/user_list/{id}")
    public String updateUser(@RequestParam ArrayList<Integer> roles, @RequestParam String name,
                             @RequestParam String lastName, @RequestParam String password, @RequestParam String email) {
        userService.updateUser(roles, name, lastName, password, email);
        return "redirect:/admin/user_list";
    }

    @DeleteMapping("/admin/user_list/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/user_list";
    }
}

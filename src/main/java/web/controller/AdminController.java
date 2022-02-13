package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/admin")
    public String redirect() {
        return "redirect:/admin/all";
    }

    @GetMapping(value = "/admin/all")
    public String showAllUsers(Model model) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) userService.loadUserByUsername(details.getUsername());
        model.addAttribute("userBar", user); //навбар
        model.addAttribute("users", userService.getAllUsers()); //тело таблицы all users
        model.addAttribute("listOfRoles", roleService.getAllRoles()); //модал на редактирование
        return "admin_allUsers";
    }

    @GetMapping(value = "/admin/new")
    public String newUserForm(Model model) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) userService.loadUserByUsername(details.getUsername());
        model.addAttribute("userBar", user); //навбар

        model.addAttribute("newUserForm", new User());
        model.addAttribute("newUserRoles", roleService.getAllRoles());
        return "admin_newUser";
    }

    @PostMapping(value = "/admin/add")
    public String addUser(@Validated(User.class) @ModelAttribute User newUser,
                          @RequestParam("authorities") List<String> listOfRoles,
                          BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        Set<Role> roleSet = userService.ListOfRolesToSet(listOfRoles);
        newUser.setRoles(roleSet);
        userService.saveUser(newUser);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(@Validated(User.class) @ModelAttribute User editedUser,
                          @RequestParam("authorities") List<String> listOfRoles,
                          BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        Set<Role> roleSet = userService.ListOfRolesToSet(listOfRoles);
        editedUser.setRoles(roleSet);
        userService.editUser(editedUser);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/delete")
    public String deleteUserById(@RequestParam Long id_del) {
        userService.deleteUser(id_del);
        return "redirect:/admin";
    }
}

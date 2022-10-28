package com.dav.kat.crud.server.controller;

import com.dav.kat.crud.server.model.User;
import com.dav.kat.crud.server.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/")
    public String getUsers(ModelMap modelMap) {

        List<User> userList = userService.listUsers();

        modelMap.addAttribute("userList", userList);

        return "user";
    }

    @RequestMapping(value = {"/addUser"})
    public String showAddUserPage(ModelMap modelMap) {

        User users = new User();
        modelMap.addAttribute("users", users);
        return "addUser";
    }

    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    public String create(@ModelAttribute("user") User user) {

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        if (firstName != null && lastName != null) {
            User user1 = new User(firstName, lastName);
            userService.create(user1);
        }
        return "redirect:/";
    }

    @RequestMapping("/{id}/edit")
    public String editUser(ModelMap modelMap, @ModelAttribute("id") Long id) {

        modelMap.addAttribute("user", userService.showUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user, user.getFirstName(), user.getLastName());

        return "redirect:/";
    }

    @DeleteMapping(value = {"/{id}"})
    public String deleteUser(@PathVariable("id") Long id) {

        userService.delete(id);

        return "redirect:/";
    }
}

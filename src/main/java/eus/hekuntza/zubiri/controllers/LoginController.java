package eus.hekuntza.zubiri.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import eus.hekuntza.zubiri.authentication.proxy.AuthenticationServerProxy;
import eus.hekuntza.zubiri.dto.UserDto;

@Controller
public class LoginController {

  @Autowired
  private AuthenticationServerProxy proxy;

  @GetMapping("/login")
  public String showLoginForm(Model model) {
    // create model object to store form data
    UserDto user = new UserDto();
    model.addAttribute("user", user);
    return "login";
  }

  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    // create model object to store form data
    UserDto user = new UserDto();
    model.addAttribute("user", user);
    return "register";
  }

  // handler method to handle user registration form submit request
  @PostMapping("/register/save")
  public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {

    proxy.sendAdd(userDto.getUsername(), userDto.getPassword());

    return "redirect:/register?success";
  }
}

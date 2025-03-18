package com.nnk.springboot.controllers;

import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestParam String username, 
                                   @RequestParam String password, 
                                   Model model, 
                                   HttpSession session) {
        Optional<User> userOpt = userService.findByUsername(username);
        System.out.println(userOpt);
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                session.setAttribute("loggedUser", user);
                
                Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), null, null);
                SecurityContextHolder.getContext().setAuthentication(auth);

                return "redirect:/user/list"; 
            }
        }

        model.addAttribute("error", "Identifiants incorrects.");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        SecurityContextHolder.clearContext();
        return "redirect:/login?logout";
    }

    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMsg", "Vous n'êtes pas autorisé à accéder à cette page.");
        mav.setViewName("403");
        return mav;
    }
}

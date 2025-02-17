package by.andersen.coworkingapp.controller;

import by.andersen.coworkingapp.exception.LoginException;
import by.andersen.coworkingapp.model.dto.RegistrationRequest;
import by.andersen.coworkingapp.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private final AuthServiceImpl authService;

    @Autowired
    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid credentials");
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new RegistrationRequest());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") RegistrationRequest dto,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        try {
            authService.registerUser(dto);
            return "redirect:/login?success";
        } catch (LoginException e) {
            result.rejectValue("login", "user.exists", e.getMessage());
            return "register";
        }
    }
}

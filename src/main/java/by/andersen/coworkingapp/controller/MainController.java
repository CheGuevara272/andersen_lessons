package by.andersen.coworkingapp.controller;

import by.andersen.coworkingapp.exception.InputException;
import by.andersen.coworkingapp.exception.LoginException;
import by.andersen.coworkingapp.model.dto.LoginRequest;
import by.andersen.coworkingapp.model.entity.User;
import by.andersen.coworkingapp.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/main")
public class MainController {
    private final AuthService authService;

    @Autowired
    public MainController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String showMainMenu(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "main-menu";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginRequest") LoginRequest loginRequest,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        try {
            User user = authService.authorization(loginRequest.getEmail(), loginRequest.getPassword());
            session.setAttribute("currentUser", user);

            return switch (user.getUserRole()) {
                case CUSTOMER -> "redirect:/customer/dashboard";
                case ADMIN -> "redirect:/admin/dashboard";
            };

        } catch (LoginException | InputException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

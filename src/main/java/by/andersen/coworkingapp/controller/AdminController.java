package by.andersen.coworkingapp.controller;

import by.andersen.coworkingapp.exception.InputException;
import by.andersen.coworkingapp.model.dto.CoworkingSpaceRequest;
import by.andersen.coworkingapp.model.entity.User;
import by.andersen.coworkingapp.model.enums.CoworkingType;
import by.andersen.coworkingapp.service.CoworkingService;
import by.andersen.coworkingapp.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CoworkingService coworkingService;
    private final ReservationService reservationService;

    @Autowired
    public AdminController(CoworkingService coworkingService,
                           ReservationService reservationService) {
        this.coworkingService = coworkingService;
        this.reservationService = reservationService;
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        model.addAttribute("user", user);
        return "admin/dashboard";
    }

    @GetMapping("/spaces/new")
    public String showAddSpaceForm(Model model) {
        model.addAttribute("spaceRequest", new CoworkingSpaceRequest());
        model.addAttribute("availableTypes", CoworkingType.values());
        return "admin/space-form";
    }

    @PostMapping("/spaces")
    public String addSpace(@ModelAttribute CoworkingSpaceRequest request,
                           RedirectAttributes redirectAttributes) {
        try {
            coworkingService.addCoworking(request);
            redirectAttributes.addFlashAttribute("success", "Space added successfully");
        } catch (InputException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/spaces";
    }

    @GetMapping("/spaces")
    public String listAllSpaces(Model model) {
        model.addAttribute("spaces", coworkingService.getAllCoworkingSpaces());
        return "admin/spaces-list";
    }

    @PostMapping("/spaces/{id}/delete")
    public String deleteSpace(@PathVariable Integer id,
                              RedirectAttributes redirectAttributes) {
        try {
            coworkingService.removeCoworking(id);
            redirectAttributes.addFlashAttribute("success", "Space deleted");
        } catch (InputException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/spaces";
    }

    @GetMapping("/reservations")
    public String listAllReservations(Model model) {
        model.addAttribute("reservations", reservationService.getReservations());
        return "admin/reservations-list";
    }
}

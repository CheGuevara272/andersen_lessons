package by.andersen.coworkingapp.controller;

import by.andersen.coworkingapp.exception.InputException;
import by.andersen.coworkingapp.model.dto.ReservationRequest;
import by.andersen.coworkingapp.model.entity.User;
import by.andersen.coworkingapp.security.SecurityUser;
import by.andersen.coworkingapp.service.CoworkingService;
import by.andersen.coworkingapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CoworkingService coworkingService;
    private final ReservationService reservationService;

    @Autowired
    public CustomerController(CoworkingService coworkingService,
                              ReservationService reservationService) {
        this.coworkingService = coworkingService;
        this.reservationService = reservationService;
    }

    @GetMapping("/dashboard")
    public String customerDashboard(Model model) {
        User user = getCurrentUser();
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "customer/dashboard";
    }

    @GetMapping("/spaces")
    public String listAvailableSpaces(Model model) {
        model.addAttribute("spaces", coworkingService.getAvailableCoworkingSpaces());
        return "customer/spaces-list";
    }

    @GetMapping("/reservations/new")
    public String showReservationForm(Model model) {
        model.addAttribute("reservationRequest", new ReservationRequest());
        model.addAttribute("spaces", coworkingService.getAvailableCoworkingSpaces());
        return "customer/reservation-form";
    }

    @PostMapping("/reservations")
    public String createReservation(@ModelAttribute ReservationRequest request,
                                    RedirectAttributes redirectAttributes) {
        try {
            User user = getCurrentUser();
            if (user == null) {
                return "redirect:/login";
            }
            reservationService.makeReservation(user, request.getSpaceName());
            redirectAttributes.addFlashAttribute("success", "Reservation created successfully");
        } catch (InputException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/customer/reservations";
    }

    @GetMapping("/reservations")
    public String listUserReservations(Model model) {
        User user = getCurrentUser();
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("reservations", reservationService.getUserReservations(user.getUserId()));
        return "customer/reservations-list";
    }

    @PostMapping("/reservations/{id}/cancel")
    public String cancelReservation(@PathVariable Integer id,
                                    RedirectAttributes redirectAttributes) {
        try {
            reservationService.cancelReservation(id);
            redirectAttributes.addFlashAttribute("success", "Reservation cancelled");
        } catch (InputException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/customer/reservations";
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SecurityUser securityUser) {
            return securityUser.getUser();
        }
        return null;
    }
}

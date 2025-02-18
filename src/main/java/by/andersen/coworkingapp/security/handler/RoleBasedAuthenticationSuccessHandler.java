package by.andersen.coworkingapp.security.handler;

import by.andersen.coworkingapp.model.enums.UserRole;
import by.andersen.coworkingapp.security.SecurityUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String targetUrl = determineTargetUrl(securityUser.getUser().getUserRole());
        response.sendRedirect(targetUrl);
    }

    private String determineTargetUrl(UserRole userRole) {
        return switch (userRole) {
            case ADMIN -> "/admin/dashboard";
            case CUSTOMER -> "/customer/profile";
        };
    }
}

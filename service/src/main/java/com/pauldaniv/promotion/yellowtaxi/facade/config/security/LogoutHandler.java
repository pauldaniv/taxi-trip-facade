package com.pauldaniv.promotion.yellowtaxi.facade.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class LogoutHandler extends SecurityContextLogoutHandler {

    /**
     * Delegates to {@linkplain SecurityContextLogoutHandler} to log the user out of the application, and then logs
     * the user out of Auth0.
     *
     * @param httpServletRequest the request.
     * @param httpServletResponse the response.
     * @param authentication the current authentication.
     */
    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       Authentication authentication) {

        // Invalidate the session and clear the security context
        super.logout(httpServletRequest, httpServletResponse, authentication);

        // Build the URL to log the user out of Auth0 and redirect them to the home page.
        // URL will look like https://YOUR-DOMAIN/v2/logout?clientId=YOUR-CLIENT-ID&returnTo=http://localhost:3000

        try {
            httpServletResponse.sendRedirect("http://localhost:8085");
        } catch (IOException ioe) {
            // Handle or log error redirecting to logout URL
        }
    }

}

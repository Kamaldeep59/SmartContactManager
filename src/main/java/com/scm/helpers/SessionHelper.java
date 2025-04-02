package com.scm.helpers;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {

    public static void removeMessage() {
        try {
            System.out.println("Removing message from session");

            // Get request attributes safely
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
                HttpSession session = servletRequestAttributes.getRequest().getSession();
                session.removeAttribute("message");
            } else {
                System.out.println("No active request context found.");
            }
        } catch (Exception e) {
            System.out.println("Error removing message from session: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

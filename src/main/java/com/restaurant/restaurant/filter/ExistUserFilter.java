package com.restaurant.restaurant.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebFilter(urlPatterns = {"/user/*", "/restaurantAdmin/*"})
public class ExistUserFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        HttpSession session = req.getSession(true);
        Object user = session.getAttribute("user");
        Object canteenAdmin = session.getAttribute("canteenAdmin");

        if (user == null && canteenAdmin == null) {
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/login.html");
        }
        else {
            chain.doFilter(request, response);
        }
    }
}

package com.restaurant.restaurant.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/GetCanteen")
public class ExistUserFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        Object user = req.getSession().getAttribute("user");
        if (user == null) {
            System.out.println("user为空");
            req.getRequestDispatcher("/login.html").forward(req,res);
        }
        else {
            chain.doFilter(request, response);
        }
    }
}

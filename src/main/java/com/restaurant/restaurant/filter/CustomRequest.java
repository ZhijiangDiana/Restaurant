package com.restaurant.restaurant.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class CustomRequest extends HttpServletRequestWrapper {
    private HttpServletRequest request;
    public CustomRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
//        Object user = this.request.getSession().getAttribute("user");
//        if (user == null){
//
//        }
//        String value = this.request.getParameter(name);
//        for (String sw : sensitiveWords) {
//            if (value.contains(sw))
//                value = value.replace(sw, "***");
//        }
//        return value;
        return null;
    }
}

package com.genx.base.config;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.stereotype.Component;

@Component
public class SimpleCORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        res.setHeader("Access-Control-Allow-Origin", (req.getHeader("Origin") == null) ? "null_origin" : req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, PUT, DELETE, PATCH");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, Authorization, Cache-Control");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}

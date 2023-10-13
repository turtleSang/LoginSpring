package com.ThankSen.BaiTap2.Security;


import com.ThankSen.BaiTap2.JwtUnit.JwtHelper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter implements Filter {
    private final String privateKey = "ThanhSen";
    private JwtHelper jwtHelper;


    @Autowired
    public JwtFilter(JwtHelper jwtHelper){
        this.jwtHelper = jwtHelper;
    }



    public String getToken(HttpServletRequest request){
        String data = request.getHeader("token");
        if (data != null){
            return data.substring(privateKey.length(), data.length());
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)  servletResponse;

        String token = getToken(request);

        if (token != null){
            Authentication authentication = jwtHelper.decodeJwt(token);
            if (authentication != null){
                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}

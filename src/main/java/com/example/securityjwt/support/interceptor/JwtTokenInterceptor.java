package com.example.securityjwt.support.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.securityjwt.support.constants.AuthConstants;
import com.example.securityjwt.support.utils.JwtTokenUtil;

import org.springframework.web.servlet.HandlerInterceptor;

public class JwtTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
            throws IOException {
        final String header = request.getHeader(AuthConstants.AUTH_HEADER);

        if (header != null) {
            final String token = JwtTokenUtil.getTokenFromHeader(header);
            if (JwtTokenUtil.isValidToken(token)) {
                return true;
            }
        }
        response.sendRedirect("/error/unauthorized");
        return false;

    }

}
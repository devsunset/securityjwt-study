package com.example.securityjwt.support.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.securityjwt.domain.MyUserDetails;
import com.example.securityjwt.domain.User;
import com.example.securityjwt.support.constants.AuthConstants;
import com.example.securityjwt.support.utils.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
            final Authentication authentication) {
        final User user = ((MyUserDetails) authentication.getPrincipal()).getUser();
        final String token = JwtTokenUtil.generateJwtToken(user);
        response.addHeader(AuthConstants.AUTH_HEADER, AuthConstants.TOKEN_TYPE + " " + token);
    }

}
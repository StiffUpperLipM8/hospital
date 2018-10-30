//package com.ssydorenko.hospital.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private static final String SUPPORTED_AUTH_METHOD = RequestMethod.POST.toString();
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
//            AuthenticationException {
//        if (!SUPPORTED_AUTH_METHOD.equals(request.getMethod())) {
//            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
//        }
//
//        UsernamePasswordAuthenticationToken authRequest = this.getUserNamePasswordAuthenticationToken(request);
//        setDetails(request, authRequest);
//
//        return getAuthenticationManager().authenticate(authRequest);
//    }
//
//    private UsernamePasswordAuthenticationToken getUserNamePasswordAuthenticationToken(HttpServletRequest request) {
//        LoginRequest loginRequest;
//        try {
//            loginRequest = new ObjectMapper().readValue(request.getReader(), LoginRequest.class);
//        } catch (IOException e) {
//            throw new IllegalArgumentException("Could not get username and password from request", e);
//        }
//        checkForValidLoginRequest(loginRequest);
//        return new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
//    }
//
//    private void checkForValidLoginRequest(LoginRequest loginRequest) {
//        if(loginRequest == null || StringUtils.isBlank(loginRequest.getUsername()) ||
//                StringUtils.isBlank(loginRequest.getPassword())) {
//            throw new IllegalArgumentException("Invalid login parameters!");
//        }
//    }
//
//    private class LoginRequest {
//
//        private String username;
//
//        private String password;
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//
//    }
//
//}
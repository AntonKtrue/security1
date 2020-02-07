package org.hyperskill.security.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BasicLoginPasswordFilter extends GenericFilterBean {

    public static final String LOGIN_HEADER = "Login";

    public static final String PASSWORD_HEADER = "Password";


    private final static Map<String, String> users = new ConcurrentHashMap<>();

    public static final String USER_1 = "user1";

    public static final String PASSWORD_1 = "password1";

    public static final String USER_2 = "user2";

    public static final String PASSWORD_2 = "password2";

    public static final String USER_3 = "user3";

    public static final String PASSWORD_3 = "password3";

    {
        users.put(USER_1, PASSWORD_1);
        users.put(USER_2, PASSWORD_2);
        users.put(USER_3, PASSWORD_3);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String login = httpServletRequest.getHeader(LOGIN_HEADER);
        String password = httpServletRequest.getHeader(PASSWORD_HEADER);

        //Verification procedures

        if(Objects.isNull(login) || Objects.isNull(password)) {
            throw new ServletException("Authentication error! (Login information doesn't present)");
        }

        String userPassword = users.get(login.toLowerCase());

        if(Objects.isNull(userPassword)) {
            throw new ServletException("Authentication error! (User not found)");
        }

        if(!userPassword.equals(password)) {
            throw new ServletException("Authentication error! (Password doesn't match)");
        }

        //Create security context for user

        Authentication auth = new Authentication() {

            boolean authentication;

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_USER")
                );
            }

            @Override
            public Object getCredentials() {
                return password;
            }

            @Override
            public Object getDetails() {
                return "Some extra data for user: " + login;
            }

            @Override
            public Object getPrincipal() {
                return login;
            }

            @Override
            public boolean isAuthenticated() {
                return authentication;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
                this.authentication = isAuthenticated;
            }

            @Override
            public String getName() {
                return login;
            }
        };
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);


        //Go on next filters

        chain.doFilter(request, response);

    }
}

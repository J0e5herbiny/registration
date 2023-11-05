package com.joe.project.security;

import com.joe.project.dto.UserDto;
import com.joe.project.entity.User;
import com.joe.project.service.UserSecurityService;
import com.joe.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

//    private UserService userService;
    @Autowired
    private UserSecurityService userSecurityService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        System.out.println(" AuthSuccessHandler ");

        String userName = authentication.getName();

        System.out.println("UserName : "+userName);

//        UserDto userDto = userSecurityService.readUserByUserName(userName);

        User user = userSecurityService.readUserByUserName(userName);
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("user", user);

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/");

    }
}

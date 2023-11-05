package com.joe.project.controller;

import com.joe.project.crm.CrmUser;
import com.joe.project.dto.UserDto;
import com.joe.project.entity.User;
import com.joe.project.service.UserSecurityService;
import com.joe.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.logging.Logger;

@Controller
@RequestMapping("register")
public class RegistrationHtmlController {


    private UserSecurityService userSecurityService;

    private UserService userService;

    private Logger logger = Logger.getLogger(getClass().getName());


    @Autowired
    public RegistrationHtmlController(UserSecurityService userSecurityService,
                                      UserService userService) {
        this.userSecurityService = userSecurityService;
        this.userService = userService;
    }


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/registrationForm")
    public String registrationForm(Model model){

        model.addAttribute("crmUser", new CrmUser());

        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("crmUser") CrmUser crmUser,
            BindingResult bindingResult,
            Model model){
        String userName = crmUser.getUserName();
        System.out.println(userName);
        logger.info("Processing registration form for user : "+userName);

        if ((bindingResult.hasErrors())){
            return "registration-form";
        }

//        UserDto userDto = userSecurityService.readUserByUserName(userName);
        User user = userSecurityService.readUserByUserName(userName);

        if (user != null){
            model.addAttribute("crmUser", new CrmUser());
            model.addAttribute("registrationError", "This UserName is already exists !");

            logger.warning("User name is already exist !");
            return "registration-form";
        }
        userSecurityService.create(crmUser);
        logger.info("User : "+userName+" is created !");

        return "registration-confirmation";
    }


}

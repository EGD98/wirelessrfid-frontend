package com.microcontrollersystem.wirelessrfidfrontend.controllers;

import com.google.gson.Gson;
import com.microcontrollersystem.wirelessrfidfrontend.configuration.SystemProperties;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.TokenData;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.UserData;
import com.microcontrollersystem.wirelessrfidfrontend.services.LoginService;
import com.microcontrollersystem.wirelessrfidfrontend.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
@Slf4j
public class IndexController {

    private final SystemProperties systemProperties;
    private final LoginService loginService;
    private final UserService userService;

    @GetMapping(value = "/login")
    public String loginView(Model model) {
        model.addAttribute("url", systemProperties.getUrl());
        model.addAttribute("icono", systemProperties.getIconUrl());
        model.addAttribute("title", "SCM - Login");
        return "/html/loginView";
    }

    @GetMapping(value = "/index")
    public String indexView(Model model, HttpSession httpSession) {
        model.addAttribute("url", systemProperties.getUrl());
        model.addAttribute("icono", systemProperties.getIconUrl());

        try {
            TokenData token = loginService.validateToken(httpSession);
            if (Objects.isNull(token)) {
                return "redirect:login";
            }
            Object tokenRetrieve = httpSession.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            model.addAttribute("user", token.getSub());

            List<UserData> userDataList = userService.getUserList( tokenRetrieveString);
            model.addAttribute("listUser",userDataList);
            return "/html/indexView";
        } catch (Exception e){
            log.error("Fallo la validación del token",e);
            return "redirect:login";
        }
    }
}

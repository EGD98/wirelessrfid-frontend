package com.microcontrollersystem.wirelessrfidfrontend.controllers;

import com.microcontrollersystem.wirelessrfidfrontend.configuration.SystemProperties;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
@AllArgsConstructor
@Slf4j
public class IndexController {

    private final SystemProperties systemProperties;

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
        model.addAttribute("title", "SCM - Index");
        Object tokenRetrieve = httpSession.getAttribute("token");

        tokenRetrieve = "admin";
        if (Objects.isNull(tokenRetrieve)) {
            return "redirect:login";
        }
        log.info(tokenRetrieve.toString());

        model.addAttribute("user", "admin");

        return "/html/indexView";
    }
}

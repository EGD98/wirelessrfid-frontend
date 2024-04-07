package com.microcontrollersystem.wirelessrfidfrontend.controllers;

import com.microcontrollersystem.wirelessrfidfrontend.configuration.SystemProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class InfoController {

    private final SystemProperties systemProperties;

    @GetMapping(value = {"/", "/info"})
    public String infoView(Model model) {
        model.addAttribute("url", systemProperties.getUrl());
        model.addAttribute("icono", systemProperties.getIconUrl());
        model.addAttribute("title", "SCM - Info");

        return "/html/infoView";
    }

    @GetMapping(value = "/contact" )
    private String contactView(Model model) {
        model.addAttribute("url", systemProperties.getUrl());
        model.addAttribute("icono", systemProperties.getIconUrl());
        model.addAttribute("title", "SCM - Contact");
        return "/html/contactView";
    }

    @GetMapping(value = "/getInto" )
    private String getIntoView(Model model) {
        model.addAttribute("url", systemProperties.getUrl());
        model.addAttribute("icono", systemProperties.getIconUrl());
        model.addAttribute("title", "SCM - Get Into");
        return "/html/getIntoView";
    }
}

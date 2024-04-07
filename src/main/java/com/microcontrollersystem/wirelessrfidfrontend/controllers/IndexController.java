package com.microcontrollersystem.wirelessrfidfrontend.controllers;

import com.microcontrollersystem.wirelessrfidfrontend.configuration.SystemProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class IndexController {

    private final SystemProperties systemProperties;

    @GetMapping(value = "/index")
    public String indexView(Model model) {
        model.addAttribute("url", systemProperties.getUrl());
        model.addAttribute("icono", systemProperties.getIconUrl());
        model.addAttribute("title", "SCM - Index");
        return "/html/indexView";
    }
}

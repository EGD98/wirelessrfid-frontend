package com.microcontrollersystem.wirelessrfidfrontend.controllers.system.view;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistryController {
    @GetMapping(value="/registry")
    public String registryView(HttpSession session){
        return "/html/system/registryView";
    }
}

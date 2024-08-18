package com.microcontrollersystem.wirelessrfidfrontend.controllers.system.view;

import com.microcontrollersystem.wirelessrfidfrontend.models.dto.ClientData;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.RegistryData;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.TokenData;
import com.microcontrollersystem.wirelessrfidfrontend.services.LoginService;
import com.microcontrollersystem.wirelessrfidfrontend.services.RegistryService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@Slf4j
@AllArgsConstructor
public class RegistryController {
    private final LoginService loginService;
    private final RegistryService registryService;

    @GetMapping(value="/registry")
    public String registryView(HttpSession session, Model model){
        try {
            TokenData token = loginService.validateToken(session);
            if (Objects.isNull(token)) {
                return "redirect:login";
            }
            Object tokenRetrieve = session.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            model.addAttribute("user", token.getSub());
            List<RegistryData> registryDataList = registryService.getRegistryList(tokenRetrieveString);
            model.addAttribute("registryList", registryDataList);
            return "/html/system/registryView";
        } catch (Exception e) {
            log.error("Fallo la validación del token: {}", e.getMessage());
            return "redirect:login";
        }

    }
}

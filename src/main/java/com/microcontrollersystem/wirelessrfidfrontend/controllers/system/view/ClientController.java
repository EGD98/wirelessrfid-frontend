package com.microcontrollersystem.wirelessrfidfrontend.controllers.system.view;

import com.microcontrollersystem.wirelessrfidfrontend.models.dto.ClientData;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.TokenData;
import com.microcontrollersystem.wirelessrfidfrontend.services.CatalogService;
import com.microcontrollersystem.wirelessrfidfrontend.services.ClientService;
import com.microcontrollersystem.wirelessrfidfrontend.services.LoginService;
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
public class ClientController {

    private final LoginService loginService;
    private final ClientService clientService;
    private final CatalogService catalogService;

    @GetMapping(value = "/clients")
    public String clientView(Model model, HttpSession session) {
        try {
            TokenData token = loginService.validateToken(session);
            if (Objects.isNull(token)) {
                return "redirect:login";
            }
            Object tokenRetrieve = session.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            model.addAttribute("user", token.getSub());

            List<ClientData> userDataList = clientService.getClientList(tokenRetrieveString);
            model.addAttribute("clientList", userDataList);
            //obtengo los catalogos de corporativo y tipo de usuario
            List<Map<String, Object>> catUserType =  catalogService.getCatalog(tokenRetrieveString, "USER_TYPE");
            model.addAttribute("catUserType",catUserType);
            List<Map<String, Object>> catCorporation =  catalogService.getCatalog(tokenRetrieveString, "CORPORATION");
            model.addAttribute("catCorporation",catCorporation);
            return "/html/system/clientView";
        } catch (Exception e) {
            log.error("Fallo la validación del token: {}", e.getMessage());
            return "redirect:login";
        }
    }
}

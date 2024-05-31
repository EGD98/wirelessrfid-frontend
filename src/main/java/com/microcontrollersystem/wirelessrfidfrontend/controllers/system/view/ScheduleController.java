package com.microcontrollersystem.wirelessrfidfrontend.controllers.system.view;

import com.microcontrollersystem.wirelessrfidfrontend.models.dto.ScheduleData;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.TokenData;
import com.microcontrollersystem.wirelessrfidfrontend.services.CatalogService;
import com.microcontrollersystem.wirelessrfidfrontend.services.LoginService;
import com.microcontrollersystem.wirelessrfidfrontend.services.ScheduleService;
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
public class ScheduleController {
    private final LoginService loginService;
    private final ScheduleService scheduleService;
    private final CatalogService catalogService;

    @GetMapping(value = "/schedule")
    public String scheduleView(Model model, HttpSession session) {
        try {
            TokenData token = loginService.validateToken(session);
            if (Objects.isNull(token)) {
                return "redirect:login";
            }
            Object tokenRetrieve = session.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            model.addAttribute("user", token.getSub());
            List<ScheduleData> scheduleDataList = scheduleService.getScheduleList(tokenRetrieveString);
            model.addAttribute("scheduleList",scheduleDataList);
            List<Map<String, Object>> catSpace =  catalogService.getCatalog(tokenRetrieveString, "SPACE_TYPE");
            model.addAttribute("catSpaceType",catSpace);
            return "/html/system/scheduleView";
        } catch (Exception e) {
            log.error("Fallo la validacion del token: {}", e.getMessage());
            return "redirect:login";
        }
    }

}

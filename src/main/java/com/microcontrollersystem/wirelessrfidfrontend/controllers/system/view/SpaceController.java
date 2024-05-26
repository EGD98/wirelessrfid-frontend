package com.microcontrollersystem.wirelessrfidfrontend.controllers.system.view;

import com.microcontrollersystem.wirelessrfidfrontend.models.dto.SpaceData;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.TokenData;
import com.microcontrollersystem.wirelessrfidfrontend.services.LoginService;
import com.microcontrollersystem.wirelessrfidfrontend.services.SpaceService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
@AllArgsConstructor
public class SpaceController {
    private final LoginService loginService;
    private final SpaceService spaceService;

    @GetMapping(value = "/spaces")
    public String spaceView(Model model, HttpSession session){
        try {
            TokenData token = loginService.validateToken(session);
            if (Objects.isNull(token)) {
                return "redirect:login";
            }
            Object tokenRetrieve = session.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            model.addAttribute("user", token.getSub());

            List<SpaceData> spaceDataList = spaceService.getSpaceList(tokenRetrieveString);
            model.addAttribute("spaceList", spaceDataList);
            return "/html/system/spaceView";
        }catch (Exception e){
            log.error("Fallo la validacion del token: {}",e.getMessage());
            return "redirect:login";
        }
    }
}

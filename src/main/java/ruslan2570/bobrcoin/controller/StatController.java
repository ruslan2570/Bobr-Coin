package ruslan2570.bobrcoin.controller;

import java.security.Principal;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ruslan2570.bobrcoin.service.StatService;

@Controller
@RequestMapping("/game/stats")
public class StatController {

    @Autowired
    StatService statService;

    @GetMapping
    public String stats(Model model) {

        statService.stat(model);

        return "stats";
    }

    @ModelAttribute("username")
    public String getUsername(Principal principal) {
        if (principal != null)
            return principal.getName();
        return null;
    }

}

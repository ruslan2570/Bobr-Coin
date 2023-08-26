package ruslan2570.bobrcoin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ruslan2570.bobrcoin.service.TopService;

@Controller
@RequestMapping("/game/top")
public class TopController {

    @Autowired
    TopService topService;

    @GetMapping
    public String top(Model model) {
        topService.top(model);
        return "top";
    }

    @ModelAttribute("username")
    public String getUsername(Principal principal) {
        if (principal != null)
            return principal.getName();
        return null;
    }
}

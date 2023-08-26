package ruslan2570.bobrcoin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;



@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping()
    public String index(Model model){
        model.addAttribute("title", "Bobr-Coin — игра про бобров");

        return "index";
    }

    @ModelAttribute("message")
    public String getMessage(@RequestParam(value = "message", defaultValue = "") String message){
        if(message.equals(""))
            return null;
        return message;
    }

    @ModelAttribute("username")
    public String getUsername(Principal principal){
        if(principal != null)
            return principal.getName();
        return null;
    }
}
package ruslan2570.bobrcoin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/game")
public class GameController {

    @GetMapping
    public String game(Model model, Authentication authentication){

        String username = authentication.getName();

        model.addAttribute("title", username);
        return "index";
    }

    @ModelAttribute("username")
    public String getUsername(Principal principal){
        if(principal != null)
            return principal.getName();
        return null;
    }
}

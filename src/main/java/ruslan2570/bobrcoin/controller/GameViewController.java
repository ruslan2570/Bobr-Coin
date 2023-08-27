package ruslan2570.bobrcoin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ruslan2570.bobrcoin.service.AchievementService;
import ruslan2570.bobrcoin.service.GameViewService;

import java.security.Principal;

@Controller
@RequestMapping("/game")
@PreAuthorize("hasRole('USER')")
public class GameViewController {

    @Autowired
    GameViewService gameViewService;

    @Autowired
    AchievementService achievementService;


    @GetMapping
    public String game(Model model){

        gameViewService.game(model);
        model.addAttribute("title", "Игра");
        return "game";
    }

    @GetMapping("/buy")
    public String buyPage(Model model){
        gameViewService.buy(model);
        model.addAttribute("title", "Покупка бобров");
        return "buy";
    }

    @GetMapping("/achievements")
    public String achievements(Principal principal, Model model) {
        achievementService.achievements(principal, model);
        model.addAttribute("title", "Достижения");
        return "achievements";
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

    @ModelAttribute("link")
    public String getLink() {
        return "game";
    }
}

package ruslan2570.bobrcoin.controller;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController("/api/game/")
public class GameController {

    @PostMapping
    public ResponseBody buyBobr(@RequestParam(name = "bobrstype-id") String bobrTypeId){

    }

    @ModelAttribute("username")
    public String getUsername(Principal principal){
        if(principal != null)
            return principal.getName();
        return null;
    }
}

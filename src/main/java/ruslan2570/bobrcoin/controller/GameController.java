package ruslan2570.bobrcoin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ruslan2570.bobrcoin.service.GameService;

import java.security.Principal;

@RestController
@RequestMapping("/api/game/")
public class GameController {

    @Autowired
    GameService gameService;


    @PostMapping("/buy")
    public ResponseEntity<?> buyBobr(
        @RequestParam(name = "bobrtype-id") long bobrTypeId,
        Principal principal
    ){
        System.out.println("Купи бобра!");
        return gameService.buy(bobrTypeId, principal);
    }

    @ModelAttribute("username")
    public String getUsername(Principal principal){
        if(principal != null)
            return principal.getName();
        return null;
    }
}

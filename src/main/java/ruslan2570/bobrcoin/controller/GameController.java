package ruslan2570.bobrcoin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/game")
public class GameController {

    @GetMapping
    public String game(){
        return "fragments/header";
    }
}

package ruslan2570.bobrcoin.controller;

import org.springframework.security.config.web.servlet.SecurityMarker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {


    @GetMapping()
    public String index(Model model){
        model.addAttribute("title", "Bobr-Coin — игра про бобров");
        return "index";
    }




}
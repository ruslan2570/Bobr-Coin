package ruslan2570.bobrcoin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping()
    public String index(Model model){
        model.addAttribute("title", "Bobr-Coin - игра про бобров");
        return "index";
    }


}
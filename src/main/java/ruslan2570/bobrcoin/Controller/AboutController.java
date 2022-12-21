package ruslan2570.bobrcoin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about/")
public class AboutController {

    @GetMapping("/")
    public String about(Model model){
        model.addAttribute("title", "О Bobr-Coin");
        return "about/index";
    }

    @GetMapping("/privacy-policy")
    public String privacyPolicy(Model model){
        model.addAttribute("title", "Политиика Конфиденциальности");
        return "about/privacy-policy";
    }

    @GetMapping("/user-agreement")
    public String userAgreement(Model model){
        model.addAttribute("title", "Пользовательское соглашение");
        return "about/user-agreement";
    }
}

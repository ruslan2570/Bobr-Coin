package ruslan2570.bobrcoin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/temp")
public class TemporaryController {
    @GetMapping("/{name}")
    public String index(@PathVariable String name, Model model){
        model.addAttribute("title", name);
        return "index";
    }


}

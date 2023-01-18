package ruslan2570.bobrcoin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public RedirectView handleError(Exception ex, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("message", ex.getMessage());
        return new RedirectView("/", true);
    }
}

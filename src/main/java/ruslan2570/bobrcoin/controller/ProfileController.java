package ruslan2570.bobrcoin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import ruslan2570.bobrcoin.service.ProfileService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping
    public String profile(Principal principal, Model model){

        profileService.profile(principal, model);

        return "profile";
    }

    @GetMapping("/reset")
    public RedirectView reset(Principal principal, RedirectAttributes redirectAttributes){

        profileService.reset(principal, redirectAttributes);
        return new RedirectView("/", true);
    }

     @ModelAttribute("username")
    public String getUsername(Principal principal){
        if(principal != null)
            return principal.getName();
        return null;
    }
}

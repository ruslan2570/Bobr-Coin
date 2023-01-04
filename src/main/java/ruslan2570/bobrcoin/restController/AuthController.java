package ruslan2570.bobrcoin.restController;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestParam("login") String login, @RequestParam("password") String password){
        return String.format("%s %s", login, password);
    }

    @PostMapping("/reg")
    public String reg(@RequestParam("login") String login,
                      @RequestParam("password") String password,
                      @RequestParam("email") String email){
        return String.format("%s %s %s", login, password, email);
    }
}

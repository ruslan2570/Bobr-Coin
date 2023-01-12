package ruslan2570.bobrcoin.controller;


import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ruslan2570.bobrcoin.exception.UserAlreadyExistsException;
import ruslan2570.bobrcoin.service.AuthService;

import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Enumeration;
import java.util.Map;
import java.util.logging.Logger;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public RedirectView login(@RequestParam("login") String login,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes){
        authService.login(login, password, redirectAttributes);
        final RedirectView redirectView = new RedirectView("/", true);

        return redirectView;
    }

    @PostMapping("/reg")
    public RedirectView reg(@RequestParam("login") String login,
                                  @RequestParam("password") String password,
                                  @RequestParam("email") String email,
                                  RedirectAttributes redirectAttributes){

        authService.reg(login, password, email, redirectAttributes);

        final RedirectView redirectView = new RedirectView("/", true);

        return redirectView;
    }

    /*
    if code == null:
        Попросить ввести email
        Отправить форму

    if code != null:
        Запросить новый пароль
        Отправить форму
    */

    @GetMapping("/forgot-password")
    public String forgotPassword(@RequestParam(name = "code", required = false) String code, Model model){

        if(code != null){
            model.addAttribute("code", code);
        }

        return "restore";
    }

    /*
    email:
        Сгенерировать код подтверждения
        Записать код в БД
        Отправить код на почту
        Перенаправить на главную с сообщением

    password:
        Захешировать пароль
        Сохранить пароль в БД
     */
    @PostMapping("/forgot-password")
    public RedirectView restorePassword(
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "new-password", required = false) String newPassword,
            @RequestParam(name = "code", required = false) String code,
            RedirectAttributes redirectAttributes){
        Log log = LogFactory.getFactory().getInstance(AuthController.class);

        log.info(String.format("%s %s %s", email, newPassword, code));

        if(email != null)
            authService.sendRestoreEmail(email, redirectAttributes);

        if(newPassword != null && code != null)
            authService.setUserPassword(code, newPassword, redirectAttributes);

        RedirectView redirectView = new RedirectView("/", true);
        return redirectView;
    }
}

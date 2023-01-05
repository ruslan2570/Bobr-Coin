package ruslan2570.bobrcoin.restController;


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
import org.springframework.web.bind.annotation.*;
import ruslan2570.bobrcoin.service.AuthService;

import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Enumeration;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam("login") String login, @RequestParam("password") String password){
        authService.login(login, password);

        return ResponseEntity.ok("Успешно");
    }

    @PostMapping("/reg")
    public ResponseEntity reg(@RequestParam("login") String login,
                      @RequestParam("password") String password,
                      @RequestParam("email") String email){

        authService.reg(login, password, email);

        return ResponseEntity.ok("Пользователь зарегистрирован");
    }

//    @PostMapping("/logout")
//    public ResponseEntity logout(){
//
//
//
//        return ResponseEntity.ok("До скорой встречи!");
//    }


}

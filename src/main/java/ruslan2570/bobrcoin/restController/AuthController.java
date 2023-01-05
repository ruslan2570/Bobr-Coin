package ruslan2570.bobrcoin.restController;


import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public String login(HttpServletRequest req, @RequestParam("login") String login, @RequestParam("password") String password){

        UsernamePasswordAuthenticationToken auth = new
                UsernamePasswordAuthenticationToken(login, password);

        authenticationProvider.authenticate(auth);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

//        HttpSession session = req.getSession(true);
//        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        return "some";
    }

    @PostMapping("/reg")
    public String reg(@RequestParam("login") String login,
                      @RequestParam("password") String password,
                      @RequestParam("email") String email){
        return String.format("%s %s %s", login, password, email);
    }


}

package ruslan2570.bobrcoin.service;

import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ruslan2570.bobrcoin.entity.UserEntity;
import ruslan2570.bobrcoin.exception.UserNotFoundException;
import ruslan2570.bobrcoin.repo.UserRepo;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepo userRepo;

    @Autowired
    MailService mailService;

    public void login(String login, String password, RedirectAttributes redirectAttributes) {
        UsernamePasswordAuthenticationToken auth = new
                UsernamePasswordAuthenticationToken(login, password);

        try {
            authenticationProvider.authenticate(auth);
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
        } catch (RuntimeException e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }
    }

    public void reg(String login, String password, String email, RedirectAttributes redirectAttributes) {

        if (userRepo.existsByLogin(login) || userRepo.existsByEmail(email)) {
            redirectAttributes.addAttribute(
                    "message",
                    "Логин или email уже занят");
            return;
        }

        UserEntity userEntity = new UserEntity(
                0,
                login,
                passwordEncoder.encode(password),
                new BigDecimal("0.01"),
                new BigDecimal("0.0"),
                email, UUID.randomUUID(), null, 0);

        userRepo.save(userEntity);

        mailService.sendConfirmationCode(userEntity);

        login(login, password, null);
    }

    public void confirmEmail(){

    }

    public void sendRestoreEmail(String email, Model model) {
        UserEntity user = userRepo.findUserByEmail(email);

        if (user == null){
            model.addAttribute("message", "Нет пользователя с данным email");
            return;
        }

        UUID uuid = UUID.randomUUID();

        user.setPasswordRestore(uuid);
        userRepo.save(user);

        mailService.sendPasswordRestoreCode(user);
        model.addAttribute("message", "Код восстановления успешно отправлен");
    }

    public void setUserPassword(String code, String newPassword, Model model) {

        UserEntity user = userRepo.findUserByPasswordRestore(UUID.fromString(code));

        if (user == null)
            model.addAttribute("message", "Код восстановления не найден");
        else{
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setPasswordRestore(null);
            userRepo.save(user);
            model.addAttribute("message", "Пароль успешно изменён");
        }
    }

}

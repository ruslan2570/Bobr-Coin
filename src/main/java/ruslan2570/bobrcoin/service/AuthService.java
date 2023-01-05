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
import ruslan2570.bobrcoin.entity.UserEntity;
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


    public void login(String login, String password) {
        UsernamePasswordAuthenticationToken auth = new
                UsernamePasswordAuthenticationToken(login, password);

        authenticationProvider.authenticate(auth);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
    }

    public void reg(String login, String password, String email) {

        UserEntity userEntity = new UserEntity(
                0,
                login,
                passwordEncoder.encode(password),
                new BigDecimal("0.01"),
                email, UUID.randomUUID());

        userRepo.save(userEntity);

        // emailService.sendConfirmationCode(UserEntity);

        login(login, password);
    }


}

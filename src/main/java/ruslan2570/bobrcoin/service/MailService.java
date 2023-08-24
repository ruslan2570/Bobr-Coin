package ruslan2570.bobrcoin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;
import ruslan2570.bobrcoin.entity.UserEntity;

import java.time.Instant;
import java.util.Date;

@Service
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String from;

    @Value("${spring.mail.server}")
    String serverUrl;

    public void sendConfirmationCode(UserEntity userEntity) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(userEntity.getEmail());
        message.setSubject("Активация аккаунта");
        String text = String.format("Привет, %s! Спасибо за интерес к Bobr-Coin." +
                " Для активации аккаунта перейди по следующей ссылке: " +
                "%sauth/confirm/%s", userEntity.getLogin(), serverUrl,
                userEntity.getEmailConfirmation().toString());

        message.setSentDate(Date.from(Instant.now()));
        message.setSubject("Подтверждение аккаунта Bobr-Coin");
        message.setText(text);

        javaMailSender.send(message);
    }

    public void sendPasswordRestoreCode(UserEntity userEntity) {
        SimpleMailMessage message = new SimpleMailMessage();

        String text = String.format("Привет, %s! Для восстановления пароля перейди по ссылке: " +
                "%sauth/forgot-password/?code=%s",
                userEntity.getLogin(),
                serverUrl,
                userEntity.getPasswordRestore());

        message.setFrom(from);
        message.setTo(userEntity.getEmail());
        message.setSubject("Восстановление пароля");
        message.setText(text);

        javaMailSender.send(message);
    }
}

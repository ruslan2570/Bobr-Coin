package ruslan2570.bobrcoin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ruslan2570.bobrcoin.entity.UserEntity;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.time.Instant;
import java.util.Date;


@Service
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;

//    @Autowired
//    Session session;

    public void sendConfirmationCode(UserEntity userEntity){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("bobr@new-bokino.ru");
        message.setTo(userEntity.getEmail());
//        message.setSubject("Активация аккаунта");
//        String text = String.format("Привет! Спасибо за интерес к Bobr-Coin." +
//                " Для активации аккаунта перейдите по следующей ссылке: " +
//                "https://bobr-coin.new-bokino.ru/api/auth/activate/%s",
//                userEntity.getEmailConfirmation().toString());

        String text = "test";
        message.setSentDate(Date.from(Instant.now()));
        message.setSubject(text);
        message.setText(text);

        javaMailSender.send(message);
    }


}

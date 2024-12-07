package dev.vanderloureiro.lembrarme.message;

import dev.vanderloureiro.lembrarme.message.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendTodayMessageService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private MessageRepository repository;

    @Autowired
    private JavaMailSender emailSender;

    public void execute() {
        List<Message> messages = repository.findAll();
        if (messages.isEmpty()) {
            System.out.println("Não tem mensagens");
            return;
        }
        var messageRequest = messages.get(0);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(from);
        email.setTo(messageRequest.getEmail());
        email.setSubject("Relembrar-me");
        email.setText(messageRequest.getBody());
        emailSender.send(email);
    }
}

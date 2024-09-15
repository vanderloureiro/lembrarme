package dev.vanderloureiro.email;

import dev.vanderloureiro.message.Message;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Random;

@ApplicationScoped
public class SendEmailService {

    @Inject
    Mailer mailer;

    @Transactional
    public void sendTodayMessage() {
        List<Message> messages = Message.getAllUnsent(1L);
        if (messages.isEmpty()) {
            return;
        }

        var random = new Random();
        int selected = random.nextInt(messages.size());
        var message = messages.get(selected);
        mailer.send(
                Mail.withText(message.user.email,"Relembrar-me", message.body)
        );
        message.registerDispatch();
        message.persist();
    }
}

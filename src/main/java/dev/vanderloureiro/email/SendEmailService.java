package dev.vanderloureiro.email;

import dev.vanderloureiro.Message;
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
        List<Message> messages = Message.getAllUnsent();
        if (messages.isEmpty()) {
            return;
        }

        var random = new Random();
        int selected = random.nextInt(messages.size());
        var message = messages.get(selected);
        mailer.send(
                Mail.withText(message.receiver,"Relembrar-me", message.content)
        );
        message.registerDispatch();
        message.persist();
    }
}

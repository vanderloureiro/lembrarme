package dev.vanderloureiro.message;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class SendMessageService {

    @Inject
    Mailer mailer;

    @Transactional
    public void sendTodayMessage() {

        List<Message> messages = Message.getAllUnsent();
        if (messages.isEmpty()) {
            return;
        }

        for (Message message : messages) {
            mailer.send(
                    Mail.withText(message.getEmail(),"Relembrar-me", message.getBody())
            );
            message.registerDispatch();
            message.persist();
        }
    }
}

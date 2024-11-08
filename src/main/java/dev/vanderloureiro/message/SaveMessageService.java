package dev.vanderloureiro.message;

import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import java.time.LocalDate;

@ApplicationScoped
public class SaveMessageService {

    private static final Logger log = Logger.getLogger(SaveMessageService.class);

    public void execute(MessageForm form) {

        var message = new Message(
                form.body,
                form.email,
                RecurrenceType.valueOf(form.recurrence),
                form.date,
                null);

        log.info("Body => " + form.body);
        log.info("Date => " + form.date);
        log.info("Message => " + message);
        // Message.persist(message);
    }
}

package dev.vanderloureiro.message;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

@ApplicationScoped
public class SaveMessageService {

    private static final Logger log = Logger.getLogger(SaveMessageService.class);

    @Transactional
    public void execute(MessageForm form) {

        var message = new Message(
                form.body,
                form.email,
                RecurrenceType.valueOf(form.recurrence),
                form.date,
                null);

        Message.persist(message);
    }
}

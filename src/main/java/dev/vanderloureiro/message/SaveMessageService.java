package dev.vanderloureiro.message;

import dev.vanderloureiro.user.CreateUserService;
import dev.vanderloureiro.user.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

@ApplicationScoped
public class SaveMessageService {

    @Inject
    private CreateUserService createUserService;

    private static final Logger log = Logger.getLogger(SaveMessageService.class);

    @Transactional
    public void execute(MessageForm form) {

        User user = User.findById(form.userId);

        if (!user.isPersistent()) {

        }

        var message = new Message(
                form.body,
                form.email,
                RecurrenceType.valueOf(form.recurrence),
                form.date,
                null,
                user);

        Message.persist(message);
    }
}

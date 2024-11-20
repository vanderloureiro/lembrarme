package dev.vanderloureiro.message;

import dev.vanderloureiro.user.CreateUserService;
import dev.vanderloureiro.user.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.Optional;

import static jakarta.transaction.Transactional.TxType.SUPPORTS;


@ApplicationScoped
public class SaveMessageService {

    @Inject
    private CreateUserService createUserService;

    private static final Logger log = Logger.getLogger(SaveMessageService.class);

    @Transactional
    public void execute(MessageForm form) {

        Optional<User> userOpt = User.findByIdOptional(form.userId);
        User user;

        if (userOpt.isEmpty()) {
            user = createUserService.execute(form.email, form.email);
        } else {
            user = userOpt.get();
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

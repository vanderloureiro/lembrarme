package dev.vanderloureiro;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;
import java.util.Random;

@Path("/mail")
public class MailResource {

    @ConfigProperty(name = "app.messages")
    List<String> messages;

    @Inject
    Mailer mailer;

    @GET
    @Blocking
    public void send() {
        var random = new Random();
        int selected = random.nextInt(messages.size() - 1);
        var message = messages.get(selected);
        mailer.send(
                Mail.withText("vanderloureiroleite@gmail.com","Relembrar-me", message)
        );
    }
}

package dev.vanderloureiro;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/mail")
public class MailResource {

    @Inject
    Mailer mailer;

    @GET
    @Blocking
    public void send() {
        mailer.send(
                Mail.withText("vanderloureiroleite@gmail.com","Relembrar-me", "- Preencha a planilha de horas!")
        );
    }
}

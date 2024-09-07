package dev.vanderloureiro;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/mail")
public class MailResource {

    @Inject
    SendEmailService sendEmailService;

    @GET
    public void send() {
        sendEmailService.sendTodayMessage();
    }
}

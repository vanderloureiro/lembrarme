package dev.vanderloureiro.startup;

import dev.vanderloureiro.email.SendEmailService;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StartupService {

    @Inject
    SendEmailService sendEmailService;

    @Startup
    public void sendEmails() {
        sendEmailService.sendTodayMessage();
    }
}

package dev.vanderloureiro.authcode;

import dev.vanderloureiro.user.User;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class GenerateAuthCodeService {

    public void execute(User user) {
        UUID uuid = UUID.randomUUID();
        var auth = new AuthCode();
        auth.key = uuid.toString();
        auth.user = user;
        auth.persist();

        // send_auth by email
    }
}

package dev.vanderloureiro.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CreateUserService {

    @Transactional
    public void execute(String username, String email) {
        User user = new User(username, email);
        User.persist(user);
    }
}

package dev.vanderloureiro.lembrarme.user;

import dev.vanderloureiro.lembrarme.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

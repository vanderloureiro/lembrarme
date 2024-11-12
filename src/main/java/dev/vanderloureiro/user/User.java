package dev.vanderloureiro.user;

import dev.vanderloureiro.message.Message;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "users_tb")
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean isEmailValid = false;

    @OneToMany
    private List<Message> messages;

    private String getEmail() {
        if (isEmailValid) {
            return this.email;
        }
        throw new RuntimeException();
    }

    public void confirmEmailValidity() {
        this.isEmailValid = true;
    }
}

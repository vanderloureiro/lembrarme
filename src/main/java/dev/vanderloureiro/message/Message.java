package dev.vanderloureiro.message;

import dev.vanderloureiro.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "messages")
public class Message extends PanacheEntity {

    @Column(nullable = false)
    public String body;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @Column(name = "last_dispatch")
    public LocalDate lastDispatch;

    public static List<Message> getAllUnsent(Long userId) {
        return list("from Message m where m.lastDispatch < ?1 and m.user_id = ?2", LocalDate.now(), userId);
    }

    public void registerDispatch() {
        this.lastDispatch = LocalDate.now();
    }
}

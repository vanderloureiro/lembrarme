package dev.vanderloureiro.message;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "messages")
public class Message extends PanacheEntity {

    @Column(nullable = false)
    public String content;

    @Column(nullable = false)
    public String receiver;

    @Column(name = "last_dispatch")
    public LocalDate lastDispatch;

    public static List<Message> getAllUnsent() {
        return list("from Message m where m.lastDispatch < ?1", LocalDate.now());
    }

    public void registerDispatch() {
        this.lastDispatch = LocalDate.now();
    }
}

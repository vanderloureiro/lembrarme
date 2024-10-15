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
    public String body;

    public String email;

    @Column(name = "recurrence_type")
    public RecurrenceType recurrenceType;

    @Column(name = "specific_day")
    public Integer specificDay;

    @Column(name = "last_dispatch")
    public LocalDate lastDispatch;

    @Column(name = "next_dispatch")
    public LocalDate nextDispatch;

    public static List<Message> getAllUnsent() {
        return list("from Message m where m.lastDispatch != ?1", LocalDate.now());
    }

    public void registerDispatch() {
        this.lastDispatch = LocalDate.now();
    }

}

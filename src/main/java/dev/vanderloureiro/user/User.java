package dev.vanderloureiro.user;

import dev.vanderloureiro.message.Message;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends PanacheEntity {

    public String username;

    @Column(nullable = false)
    public String email;

    @Column(name = "email_verified")
    public Boolean emailVerified;

    @CreationTimestamp
    @Column(name = "created_at")
    public ZonedDateTime createdAt;

    @OneToMany
    public List<Message> messages;
}

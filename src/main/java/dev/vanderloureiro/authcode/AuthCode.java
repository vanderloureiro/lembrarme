package dev.vanderloureiro.authcode;

import dev.vanderloureiro.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.ZonedDateTime;

@Entity
@Table(name = "auth_codes")
public class AuthCode extends PanacheEntity {

    @Column(nullable = false)
    public String key;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at")
    public ZonedDateTime createdAt;
}

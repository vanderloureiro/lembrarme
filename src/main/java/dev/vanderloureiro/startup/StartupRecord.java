package dev.vanderloureiro.startup;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "startup_record")
public class StartupRecord extends PanacheEntity {

    @Column(name = "last_startup", nullable = false)
    LocalDate lastStartup;
}
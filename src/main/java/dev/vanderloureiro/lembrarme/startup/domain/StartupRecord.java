package dev.vanderloureiro.lembrarme.startup.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "startup_records")
public class StartupRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_startup", nullable = false)
    LocalDate lastStartup;

    private StartupRecord () {}

    public static StartupRecord init() {
        var instance = new StartupRecord();
        instance.lastStartup = LocalDate.now();
        return instance;
    }

    public void updateRecord() {
        this.lastStartup = LocalDate.now();
    }

    public boolean isPendingShipment() {
        return this.lastStartup.isBefore(LocalDate.now());
    }
}

package dev.vanderloureiro.startup;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "startup_records")
public class StartupRecord extends PanacheEntity {

    @Column(name = "last_startup", nullable = false)
    LocalDate lastStartup;

    public void updateRecord() {
        this.lastStartup = LocalDate.now();
    }

    public boolean isPendingShipment() {
        return this.lastStartup.isBefore(LocalDate.now());
    }
}
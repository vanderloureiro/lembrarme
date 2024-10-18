package dev.vanderloureiro.message;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "messages")
public class Message extends PanacheEntity {

    @Column(nullable = false)
    public String body;

    public String email;

    @Column(name = "recurrence_type")
    @Enumerated(EnumType.STRING)
    public RecurrenceType recurrenceType;

    @Column(name = "specific_day")
    public Integer specificDay;

    @Column(name = "last_dispatch")
    public LocalDate lastDispatch;

    @Column(name = "next_dispatch")
    public LocalDate nextDispatch;

    public static List<Message> getAllUnsent() {
        return list("from Message m where m.lastDispatch != m.nextDispatch AND m.nextDispatch = ?1", LocalDate.now());
    }

    public void registerDispatch() {
        this.lastDispatch = LocalDate.now();
        this.calculateNextDispatchFromToday();
    }

    private void calculateNextDispatchFromToday() {
        switch (recurrenceType) {
            case EVERYDAY -> {
                nextDispatch = LocalDate.now().plusDays(1);
            }
            case RANDOM -> {
                int days = new Random().nextInt(30);
                nextDispatch = LocalDate.now().plusDays(days);
            }
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY -> {
                nextDispatch = LocalDate.now().plusDays(7);
            }
            case WEEKEND -> {
                if (LocalDate.now().getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                    nextDispatch = LocalDate.now().plusDays(1);
                } else if (LocalDate.now().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                    nextDispatch = LocalDate.now().plusDays(6);
                }
            }
            case WEEKDAYS -> {
                if (List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY)
                                .contains(LocalDate.now().getDayOfWeek())) {
                    nextDispatch = LocalDate.now().plusDays(1);
                } else if (LocalDate.now().getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                    nextDispatch = LocalDate.now().plusDays(3);
                }
            }
            case SPECIFIC_DAY -> {
                LocalDate nextMonth = LocalDate.now().plusMonths(1);
                nextDispatch = LocalDate.of(nextMonth.getYear(), nextMonth.getMonth(), this.specificDay);
            }
            case MONTHLY -> {
                nextDispatch = LocalDate.now().plusMonths(1);
            }
        }
    }

}

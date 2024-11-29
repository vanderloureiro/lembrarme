package dev.vanderloureiro.lembrarme.message;

import dev.vanderloureiro.lembrarme.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String body;

    private String email;

    @Column(name = "recurrence_type")
    @Enumerated(EnumType.STRING)
    private RecurrenceType recurrenceType;

    @Column(name = "specific_day")
    private Integer specificDay;

    @Column(name = "last_dispatch")
    private LocalDate lastDispatch;

    @Column(name = "next_dispatch")
    private LocalDate nextDispatch;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Message() {}

    public Message(String body, String email, RecurrenceType recurrence, LocalDate date, Integer specificDay, User user) {
        this.body = body;
        this.email = email;
        this.recurrenceType = recurrence;
        this.nextDispatch = date;
        this.specificDay = specificDay;
        this.user = user;
        if (!RecurrenceType.SINGLE.equals(recurrence)) {
            calculateNextDispatchFromToday();
        }
    }

    public void registerDispatch() {
        this.lastDispatch = LocalDate.now();
        this.calculateNextDispatchFromToday();
    }

    public void registerSingleDispatch(LocalDate date) {
        nextDispatch = date;
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

    public String getEmail() {
        return this.email;
    }

    public String getBody() {
        return this.body;
    }
}

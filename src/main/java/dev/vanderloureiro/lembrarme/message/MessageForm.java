package dev.vanderloureiro.lembrarme.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class MessageForm {

    @JsonProperty("body")
    public String body;

    @JsonProperty("email")
    public String email;

    @JsonProperty("date")
    public LocalDate date;

    @JsonProperty("recurrence")
    public String recurrence;

    @JsonProperty("user_id")
    public Long userId;
}
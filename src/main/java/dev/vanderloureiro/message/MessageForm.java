package dev.vanderloureiro.message;

import jakarta.ws.rs.FormParam;

import java.time.LocalDate;

public class MessageForm {

    @FormParam("body")
    public String body;

    @FormParam("email")
    public String email;

    @FormParam("date")
    public LocalDate date;

    @FormParam("recurrence")
    public String recurrence;

    @FormParam("user_id")
    public Long userId;
}

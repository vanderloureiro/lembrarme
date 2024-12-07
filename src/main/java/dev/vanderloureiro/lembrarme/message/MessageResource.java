package dev.vanderloureiro.lembrarme.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageResource {

    @Autowired
    private SendTodayMessageService sendTodayMessageService;

    @GetMapping
    public String execute() {
        return "home";
    }
}

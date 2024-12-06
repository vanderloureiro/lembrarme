package dev.vanderloureiro.lembrarme.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageResource {

    @Autowired
    private SendTodayMessageService sendTodayMessageService;

    @GetMapping("/send-email")
    public void execute() {
        sendTodayMessageService.execute();
    }
}

package dev.vanderloureiro.scheduler;

import dev.vanderloureiro.message.SendMessageService;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MessageScheduler {

    @Inject
    SendMessageService sendMessageService;

    @Scheduled(cron="0 0 6 * * ?")
    void cronJob() {
        sendMessageService.sendTodayMessage();
    }
}

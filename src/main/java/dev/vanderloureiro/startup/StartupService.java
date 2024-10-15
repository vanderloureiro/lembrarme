package dev.vanderloureiro.startup;

import dev.vanderloureiro.message.SendMessageService;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Optional;

import static jakarta.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
public class StartupService {

    @Inject
    SendMessageService sendMessageService;

    @Startup
    @Transactional(SUPPORTS)
    public void sendEmails() {
        Optional<StartupRecord> opt = StartupRecord.findByIdOptional(1);
        if (opt.isEmpty()) {
            return;
        }
        StartupRecord startup = opt.get();
        if (!startup.isPendingShipment()) {
            return;
        }
        startup.updateRecord();
        startup.persist();

        sendMessageService.sendTodayMessage();
    }
}

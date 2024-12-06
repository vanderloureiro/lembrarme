package dev.vanderloureiro.lembrarme.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/startup/registration")
public class RegisterNewStartup {

    @Autowired
    private StartupRepository repository;

    @PostMapping
    public ResponseEntity<Void> execute() {

        StartupRecord toSafe = StartupRecord.init();
        this.repository.save(toSafe);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity get() {
        return ResponseEntity.ok("Startup Records");
    }
}

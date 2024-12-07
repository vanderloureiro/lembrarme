package dev.vanderloureiro.lembrarme.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInResource {

    @GetMapping("/signin")
    public String execute() {
        return "signin";
    }
}

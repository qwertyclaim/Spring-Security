package security.springsecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String getHelloPage() {
        return "hello";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}
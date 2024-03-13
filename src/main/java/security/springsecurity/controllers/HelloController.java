package security.springsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import security.springsecurity.services.StaffService;

@Controller
public class HelloController {
    private final StaffService service;

    @Autowired
    public HelloController(StaffService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String getHelloPage() {
        return "hello";
    }

    @GetMapping("/admin")
    public String adminPage() {
        service.doStaffActions();
        return "admin";
    }
}
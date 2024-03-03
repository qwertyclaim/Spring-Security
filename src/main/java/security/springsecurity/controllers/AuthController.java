package security.springsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import security.springsecurity.models.Person;
import security.springsecurity.services.PeopleService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PeopleService service;

    @Autowired
    public AuthController(PeopleService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "registration";
    }

    @GetMapping("/authorize")
    public String getAuthorizeForm(@ModelAttribute("person")Person person) {
        return "authorize";
    }

    @PostMapping("/authorize")
    public String authorization(@ModelAttribute("person") Person person) {
        service.save(person);
        return "redirect:/hello";
    }

    @GetMapping("/logout")
    public String getLogout() {
        return "redirect:/auth/login";
    }
}
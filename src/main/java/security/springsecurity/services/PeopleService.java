package security.springsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.springsecurity.models.Person;
import security.springsecurity.repositories.PeopleRepository;

@Service
public class PeopleService {
    private final PeopleRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public PeopleService(PeopleRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public void save(Person person) {
        String encodedPassword = encoder.encode(person.getPassword());
        person.setPassword(encodedPassword);
        person.setRole("ROLE_USER");

        repository.save(person);
    }
}
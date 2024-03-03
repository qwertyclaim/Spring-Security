package security.springsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.springsecurity.models.Person;
import security.springsecurity.repositories.PeopleRepository;
import security.springsecurity.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository repository;

    @Autowired
    public PersonDetailsService(PeopleRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> user = repository.findUserByUsername(username);

        if(user.isEmpty())
            throw new UsernameNotFoundException("User didn't found");

        return new PersonDetails(user.get());
    }
}
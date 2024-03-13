package security.springsecurity.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void doStaffActions() {
        System.out.println("Admin action");
    }
}
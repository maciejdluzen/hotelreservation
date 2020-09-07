package pl.maciejdluzen.hotelreservation.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.Admin;
import pl.maciejdluzen.hotelreservation.domain.repositories.AdminRepository;
import pl.maciejdluzen.hotelreservation.domain.repositories.RoleRepository;
import pl.maciejdluzen.hotelreservation.services.AdminService;

@Service
public class DefaultAdminService implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public DefaultAdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public Admin createAdminAccount(Admin admin) {
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setUsername(admin.getEmailAddress());
        admin.setPassword(encodedPassword);
        admin.setRole(roleRepository.getByName("ROLE_ADMIN"));
        admin.setActive(true);
        return adminRepository.save(admin);
    }

}

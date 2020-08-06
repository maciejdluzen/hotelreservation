package pl.maciejdluzen.hotelreservation.setup;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.maciejdluzen.hotelreservation.domain.entities.Role;
import pl.maciejdluzen.hotelreservation.domain.repositories.RoleRepository;

@Component
public class StarterClass implements ApplicationRunner {

    private final RoleRepository roleRepository;

    public StarterClass(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        Role roleAdmin = new Role();
        roleAdmin.setId(1L);
        roleAdmin.setName("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        Role roleReceptionist = new Role();
        roleReceptionist.setId(2L);
        roleReceptionist.setName("ROLE_RECEPTIONIST");
        roleRepository.save(roleReceptionist);

        Role roleGuest = new Role();
        roleGuest.setId(3L);
        roleGuest.setName("ROLE_GUEST");
        roleRepository.save(roleGuest);   
    }
}

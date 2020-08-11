package pl.maciejdluzen.hotelreservation.setup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.maciejdluzen.hotelreservation.domain.entities.Hotel;
import pl.maciejdluzen.hotelreservation.domain.entities.Role;
import pl.maciejdluzen.hotelreservation.domain.repositories.HotelRepository;
import pl.maciejdluzen.hotelreservation.domain.repositories.RoleRepository;

@Component
public class StarterClass implements ApplicationRunner {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final RoleRepository roleRepository;
    private final HotelRepository hotelRepository;

    public StarterClass(RoleRepository roleRepository, HotelRepository hotelRepository) {
        this.roleRepository = roleRepository;
        this.hotelRepository = hotelRepository;
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

        Hotel hotel1 = new Hotel();
        hotel1.setName("Plaza Inn Wrocław - Bielany");
        hotel1.setStreet("Karkonoska");
        hotel1.setNumber("124");
        hotel1.setCity("Wrocław");
        hotel1.setPostCode("53-522");
        hotel1.setPhoneNumber("(+48) 71 342-44-78");
        hotel1.setEmailAddress("wroclawbielany@plazainn.pl");
        hotelRepository.save(hotel1);
    }
}

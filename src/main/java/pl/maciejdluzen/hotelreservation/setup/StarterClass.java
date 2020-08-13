package pl.maciejdluzen.hotelreservation.setup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.maciejdluzen.hotelreservation.domain.entities.Hotel;
import pl.maciejdluzen.hotelreservation.domain.entities.Role;
import pl.maciejdluzen.hotelreservation.domain.entities.Room;
import pl.maciejdluzen.hotelreservation.domain.entities.RoomType;
import pl.maciejdluzen.hotelreservation.domain.repositories.HotelRepository;
import pl.maciejdluzen.hotelreservation.domain.repositories.RoleRepository;
import pl.maciejdluzen.hotelreservation.domain.repositories.RoomRepository;
import pl.maciejdluzen.hotelreservation.domain.repositories.RoomTypeRepository;

@Component
public class StarterClass implements ApplicationRunner {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final RoleRepository roleRepository;
    private final HotelRepository hotelRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomRepository roomRepository;

    public StarterClass(RoleRepository roleRepository, HotelRepository hotelRepository, RoomTypeRepository roomTypeRepository, RoomRepository roomRepository) {
        this.roleRepository = roleRepository;
        this.hotelRepository = hotelRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.roomRepository = roomRepository;
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

        Hotel hotel2 = new Hotel();
        hotel2.setName("Plaza Inn Poznan - Centrum");
        hotel2.setStreet("Grunwaldzka");
        hotel2.setNumber("8");
        hotel2.setCity("Poznań");
        hotel2.setPostCode("33-000");
        hotel2.setPhoneNumber("(+48) 50 433-87-39");
        hotel2.setEmailAddress("poznancentrum@plazainn.pl");
        hotelRepository.save(hotel2);

        RoomType roomType1 = new RoomType();
        roomType1.setName("LUX");
        roomType1.setNoPersons(2);
        roomType1.setRateNet(140.00);
        roomType1.setTax(0.23);
        roomType1.setRateGross(roomType1.getRateNet()*(1+roomType1.getTax()));
        roomTypeRepository.save(roomType1);

        RoomType roomType2 = new RoomType();
        roomType2.setName("Double");
        roomType2.setNoPersons(2);
        roomType2.setRateNet(90.00);
        roomType2.setTax(0.23);
        roomType2.setRateGross(roomType2.getRateNet()*(1+roomType2.getTax()));
        roomTypeRepository.save(roomType2);

        Room room1 = new Room();
        room1.setRoomNumber(100);
        room1.setFloorNumber(1);
        room1.setRoomType(roomType1);
        room1.setHotel(hotel1);
        roomRepository.save(room1);

        Room room2 = new Room();
        room2.setRoomNumber(101);
        room2.setFloorNumber(1);
        room2.setRoomType(roomType2);
        room2.setHotel(hotel1);
        roomRepository.save(room2);

    }
}

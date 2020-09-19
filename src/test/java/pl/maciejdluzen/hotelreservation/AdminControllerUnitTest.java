package pl.maciejdluzen.hotelreservation;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import pl.maciejdluzen.hotelreservation.domain.entities.Hotel;
import pl.maciejdluzen.hotelreservation.domain.repositories.HotelRepository;
import pl.maciejdluzen.hotelreservation.dtos.GetHotelDtoJson;
import pl.maciejdluzen.hotelreservation.services.HotelService;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


@RunWith(SpringRunner.class)
//@DataJpaTest(showSql = true)
@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:testscripts/rooms-testdata.sql")
public class AdminControllerUnitTest {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void givenHotelId_whenGetHotelJson_thenReturnOneHotel() {

        GetHotelDtoJson hotel = hotelService.findHotelById(1L);
        MatcherAssert.assertThat(hotel, is(notNullValue()));

    }




}

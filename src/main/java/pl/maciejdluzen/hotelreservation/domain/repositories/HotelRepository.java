package pl.maciejdluzen.hotelreservation.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.maciejdluzen.hotelreservation.domain.entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Transactional
    @Query("UPDATE Hotel h SET h.name = :name, h.street = :street, h.number = :number, " +
            "h.city = :city, h.postCode = :postCode, h.phoneNumber = :phoneNumber, h.emailAddress = :emailAddress WHERE h.id = :id")
    @Modifying
    void updateHotelById(@Param("id") Long id, @Param("name") String name,
                         @Param("street") String street, @Param("number") String number,
                         @Param("city") String city, @Param("postCode") String postCode,
                         @Param("phoneNumber") String phoneNumber, @Param("emailAddress") String emailAddress);

}

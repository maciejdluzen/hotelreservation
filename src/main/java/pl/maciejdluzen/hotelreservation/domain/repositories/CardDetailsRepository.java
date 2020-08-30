package pl.maciejdluzen.hotelreservation.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maciejdluzen.hotelreservation.domain.entities.CardDetails;

@Repository
public interface CardDetailsRepository extends JpaRepository<CardDetails, Long> {

    CardDetails findCardDetailsByCardNumber(String cardNumber);



}

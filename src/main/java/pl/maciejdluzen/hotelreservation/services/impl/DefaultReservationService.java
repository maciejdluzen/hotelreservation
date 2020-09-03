package pl.maciejdluzen.hotelreservation.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.*;
import pl.maciejdluzen.hotelreservation.domain.repositories.*;
import pl.maciejdluzen.hotelreservation.dtos.CardDetailsDto;
import pl.maciejdluzen.hotelreservation.dtos.GetReservationsDto;
import pl.maciejdluzen.hotelreservation.dtos.ReservationDetailsDto;
import pl.maciejdluzen.hotelreservation.dtos.ReservationDto;
import pl.maciejdluzen.hotelreservation.services.ReservationService;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultReservationService implements ReservationService {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final ModelMapper mapper;
    private final CardDetailsRepository cardDetailsRepository;
    private final ReservationRepository reservationRepository;
    private final HotelRepository hotelRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final GuestRepository guestRepository;

    public DefaultReservationService(ModelMapper mapper, CardDetailsRepository cardDetailsRepository, ReservationRepository reservationRepository, HotelRepository hotelRepository, RoomTypeRepository roomTypeRepository, GuestRepository guestRepository) {
        this.mapper = mapper;
        this.cardDetailsRepository = cardDetailsRepository;
        this.reservationRepository = reservationRepository;
        this.hotelRepository = hotelRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public Reservation createReservation(ReservationDto reservationDto, CardDetailsDto cardDetailsDto) {
        // move these two lines to anotehr class

        Reservation reservation = new Reservation();
        reservation.setReservationNumber(reservationNumberBuilder(reservationDto)); // Create string builder
        reservation.setCheckInDate(reservationDto.getCheckInDate());
        reservation.setCheckOutDate(reservationDto.getCheckOutDate());
        reservation.setSecondGuestName(reservationDto.getSecondGuestName());
        reservation.setThirdGuestName(reservationDto.getThirdGuestName());
        reservation.setFourthGuestName(reservationDto.getFourthGuestName());
        reservation.setTotalNetCost(calculateTotalCosts(reservationDto).get(0));
        reservation.setTax(calculateTotalCosts(reservationDto).get(1));
        reservation.setTotalGrossCost(calculateTotalCosts(reservationDto).get(2));
        reservation.setMessage(reservationDto.getMessage());
        reservation.setRoomTypeName(reservationDto.getRoomTypeName());

        // Relationships
        reservation.setGuest(guestRepository.findByEmailAddressIgnoreCase(reservationDto.getUsername()));
        reservation.setCardDetails(cardDetailsRepository.findCardDetailsByCardNumber(cardDetailsDto.getCardNumber())); // find card details by number
        reservation.setHotel(hotelRepository.findHotelByName(reservationDto.getHotelName()));

        return reservationRepository.save(reservation);
    }


    @Override
    public String reservationNumberBuilder(ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.findReservationWithGreatestId();
        Hotel hotel = hotelRepository.findHotelByName(reservationDto.getHotelName());

        Long id = reservation.getId() + 1L;
        String city = hotel.getCity();
        String street = hotel.getStreet();
        String guestName = reservationDto.getGuestName().toUpperCase();
        String[] guestNameSplit = guestName.split(" ");
        char firstNameBegin = guestNameSplit[0].charAt(0);
        char lastNameBegin = guestNameSplit[1].charAt(0);

        String idString = Long.toString(id);

        StringBuilder sb = new StringBuilder();
        sb.append(city.toUpperCase().substring(0, 2))
                .append("/")
                .append(street.toUpperCase().substring(0, 2))
                .append("/")
                .append(firstNameBegin)
                .append(lastNameBegin)
                .append("/")
                .append(idString);

        return sb.toString();
    }

    @Override
    public List<Double> calculateTotalCosts(ReservationDto reservationDto) {
        LocalDate checkIn = reservationDto.getCheckInDate();
        LocalDate checkOut = reservationDto.getCheckOutDate();
        Period period = Period.between(checkIn, checkOut);
        int nightsNumber = period.getDays();
        RoomType roomType = roomTypeRepository.findRoomTypeByName(reservationDto.getRoomTypeName());
        double rateNet = roomType.getRateNet();
        double rateGross = roomType.getRateGross();
        double vat = roomType.getTax();

        double totalCostNet = nightsNumber * rateNet;
        double totalCostGross = nightsNumber * rateGross;

        double totalNetRounded = Math.round(totalCostNet * 100.0)/100.0;
        double totalGrossRounded = Math.round(totalCostGross * 100.0)/100.0;

        List<Double> costs = new ArrayList<>(3);
        costs.add(totalNetRounded);
        costs.add(vat);
        costs.add(totalGrossRounded);
        return costs;
    }

    @Override
    public List<GetReservationsDto> getAllReservationsByUsername(String username) {
        Guest guest = guestRepository.findByUsername(username);
        List<Reservation> reservations = reservationRepository.findAllReservationsByGuest(guest);
        List<GetReservationsDto> reservationsDto = new ArrayList<>();

        for(Reservation reservation : reservations) {
            GetReservationsDto reservationDto = mapper.map(reservation, GetReservationsDto.class);
            reservationsDto.add(reservationDto);
        }
        return reservationsDto;
    }

    @Override
    public ReservationDetailsDto getReservationDetails(Long id) {
        Reservation reservation = reservationRepository.getOne(id);
        ReservationDetailsDto reservationDetails = mapper.map(reservation, ReservationDetailsDto.class);

        return reservationDetails;
    }
}

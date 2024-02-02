package com.udemx.carRentalService.repository;

import com.udemx.carRentalService.dao.ReservationRepository;
import com.udemx.carRentalService.entity.Reservation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReservationRepositoryTests {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void ReservationRepository_SaveAll(){
        Reservation reservation = new Reservation(1, "2024-01-01", "2024-01-02", "name", "email", "address", "0123456789", 100);

        Reservation savedReservation = reservationRepository.save(reservation);

        Assertions.assertThat(savedReservation).isNotNull();
        Assertions.assertThat(savedReservation.getId()).isGreaterThan(0);
    }

    @Test
    public void ReservationRepository_GetAll() {
        Reservation reservation1 = new Reservation(1, "2024-01-01", "2024-01-02", "name", "email", "address", "0123456789", 100);
        Reservation reservation2 = new Reservation(2, "2024-01-01", "2024-01-02", "name", "email", "address", "0123456789", 100);

        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);

        List<Reservation> reservationList = reservationRepository.findAll();

        Assertions.assertThat(reservationList).isNotNull();
        Assertions.assertThat(reservationList.size()).isEqualTo(2);
    }

    @Test
    public void ReservationRepository_findById(){
        Reservation reservation = new Reservation(1, "2024-01-01", "2024-01-02", "name", "email", "address", "0123456789", 100);

        reservationRepository.save(reservation);

        Reservation savedReservation = reservationRepository.findById(reservation.getId()).get();

        Assertions.assertThat(savedReservation).isNotNull();
    }

    @Test
    public void ReservationRepository_Update(){
        Reservation reservation = new Reservation(1, "2024-01-01", "2024-01-02", "name", "email", "address", "0123456789", 100);

        reservationRepository.save(reservation);

        Reservation savedReservation = reservationRepository.findById(reservation.getId()).get();
        savedReservation.setCarId(2);

        reservationRepository.save(savedReservation);

        Assertions.assertThat(savedReservation).isNotNull();
        Assertions.assertThat(savedReservation.getCarId()).isEqualTo(2);
    }

    @Test
    public void ReservationRepository_Delete(){
        Reservation reservation = new Reservation(1, "2024-01-01", "2024-01-02", "name", "email", "address", "0123456789", 100);

        reservationRepository.save(reservation);

        reservationRepository.deleteById(reservation.getId());
        Optional<Reservation> reservationReturn = reservationRepository.findById(reservation.getId());

        Assertions.assertThat(reservationReturn).isEmpty();
    }
}

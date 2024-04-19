package org.example.adherent;

import org.example.models.Adherent;
import org.example.models.Reservation;
import org.example.services.ReservationService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class AdherentReservationTest {
    @Test
    public void testReservationAddedToAdherentList() {
        Adherent adherent = new Adherent("123", "John", "Doe", "01/01/1990", "Monsieur");

        String reservationId = "R123";
        LocalDate dateDepot = LocalDate.parse("2024-04-01");
        LocalDate dateLimite = LocalDate.parse("2024-04-01");
        String etat = "En cours";
        String referenceAdherent = adherent.getCode_adherent();
        Reservation reservation = new Reservation(reservationId, dateDepot, dateLimite, etat, referenceAdherent);

        ReservationService reservationService = new ReservationService();
        boolean reservationSuccess = reservationService.makeReservation(adherent, reservation);

        assertTrue(reservationSuccess);
        assertTrue(adherent.getReservations().contains(reservation));
    }

    @Test
    public void testReservationDetailsCorrect() {
        Adherent adherent = new Adherent("123", "John", "Doe", "01/01/1990", "Monsieur");

        String reservationId = "R123";
        LocalDate dateDepot = LocalDate.parse("2024-04-01");
        LocalDate dateLimite = LocalDate.parse("2024-04-01");
        String etat = "En cours";
        String referenceAdherent = adherent.getCode_adherent();
        Reservation reservation = new Reservation(reservationId, dateDepot, dateLimite, etat, referenceAdherent);

        ReservationService reservationService = new ReservationService();
        boolean reservationSuccess = reservationService.makeReservation(adherent, reservation);

        assertTrue(reservationSuccess);
        Reservation addedReservation = adherent.getReservations().get(0);
        assertEquals(reservationId, addedReservation.getId());
        assertEquals(dateDepot, addedReservation.getDateDepot());
        assertEquals(dateLimite, addedReservation.getDateLimite());
        assertEquals(etat, addedReservation.getEtat());
        assertEquals(referenceAdherent, addedReservation.getReferenceAdherent());
    }

    @Test
    public void testReservationDeadline() {
        Adherent adherent = new Adherent("123", "John", "Doe", "01/04/1990", "Monsieur");

        String reservationId = "R123";
        LocalDate dateDepot = LocalDate.parse("2024-04-01");
        LocalDate dateLimite = LocalDate.parse("2024-08-01");
        String etat = "En cours";
        String referenceAdherent = adherent.getCode_adherent();
        Reservation reservation = new Reservation(reservationId, dateDepot, dateLimite, etat, referenceAdherent);

        reservation.setDepositDate(dateDepot);

        LocalDate expectedDeadline = dateDepot.plusMonths(4);

        assertEquals(expectedDeadline, reservation.getDateLimite(), "La date limite de la réservation doit être de 4 mois à partir de la date de dépôt.");
    }

    @Test
    public void testInvalidReservationDeadline() {
        Adherent adherent = new Adherent("123", "John", "Doe", "01/04/1990", "Monsieur");

        String reservationId = "R123";
        LocalDate dateDepot = LocalDate.parse("2024-09-01");
        LocalDate dateLimite = LocalDate.parse("2024-08-01");
        String etat = "En cours";
        String referenceAdherent = adherent.getCode_adherent();
        Reservation reservation = new Reservation(reservationId, dateDepot, dateLimite, etat, referenceAdherent);

        assertThrows(IllegalArgumentException.class, () -> {
            reservation.setDepositDate(dateDepot);
        });
    }

    @Test
    public void testEndReservation() {
        Adherent adherent = new Adherent("123", "John", "Doe", "01/04/1990", "Monsieur");

        String reservationId = "R123";
        LocalDate dateDepot = LocalDate.parse("2024-09-01");
        LocalDate dateLimite = LocalDate.parse("2024-12-01");
        String etat = "En cours";
        String referenceAdherent = adherent.getCode_adherent();
        Reservation reservation = new Reservation(reservationId, dateDepot, dateLimite, etat, referenceAdherent);

        reservation.endReservation();

        assertEquals("Terminée", reservation.getEtat());
    }

    @Test
    public void testMaxThreeOpenReservationsPerAdherent(){
        Adherent adherent = new Adherent("123", "John", "Doe", "01/04/1990", "Monsieur");

        String reservationId1 = "R121";
        LocalDate dateDepot1 = LocalDate.parse("2024-09-01");
        LocalDate dateLimite1 = LocalDate.parse("2024-12-01");
        String etat1 = "En cours";
        String referenceAdherent1 = adherent.getCode_adherent();
        Reservation reservation1 = new Reservation(reservationId1, dateDepot1, dateLimite1, etat1, referenceAdherent1);

        String reservationId2 = "R122";
        LocalDate dateDepot2 = LocalDate.parse("2024-09-01");
        LocalDate dateLimite2 = LocalDate.parse("2024-12-01");
        String etat2 = "En cours";
        String referenceAdherent2 = adherent.getCode_adherent();
        Reservation reservation2 = new Reservation(reservationId2, dateDepot2, dateLimite2, etat2, referenceAdherent2);

        String reservationId3 = "R123";
        LocalDate dateDepot3 = LocalDate.parse("2024-09-01");
        LocalDate dateLimite3 = LocalDate.parse("2024-12-01");
        String etat3 = "En cours";
        String referenceAdherent3 = adherent.getCode_adherent();
        Reservation reservation3 = new Reservation(reservationId3, dateDepot3, dateLimite3, etat3, referenceAdherent3);

        String reservationId4 = "R124";
        LocalDate dateDepot4 = LocalDate.parse("2024-09-01");
        LocalDate dateLimite4 = LocalDate.parse("2024-12-01");
        String etat4 = "En cours";
        String referenceAdherent4 = adherent.getCode_adherent();
        Reservation reservation4 = new Reservation(reservationId4, dateDepot4, dateLimite4, etat4, referenceAdherent4);

        ReservationService reservationService = new ReservationService();
        reservationService.makeReservation(adherent, reservation1);
        reservationService.makeReservation(adherent, reservation2);
        reservationService.makeReservation(adherent, reservation3);

        assertThrows(IllegalArgumentException.class, () -> {
            reservationService.makeReservation(adherent, reservation4);
        });
    }

    @Test
    public void testGetOpenReservations(){
        Adherent adherent = new Adherent("123", "John", "Doe", "01/04/1990", "Monsieur");

        String reservationId1 = "R121";
        LocalDate dateDepot1 = LocalDate.parse("2024-09-01");
        LocalDate dateLimite1 = LocalDate.parse("2024-12-01");
        String etat1 = "En cours";
        String referenceAdherent1 = adherent.getCode_adherent();
        Reservation reservation1 = new Reservation(reservationId1, dateDepot1, dateLimite1, etat1, referenceAdherent1);

        String reservationId2 = "R122";
        LocalDate dateDepot2 = LocalDate.parse("2024-09-01");
        LocalDate dateLimite2 = LocalDate.parse("2024-12-01");
        String etat2 = "En cours";
        String referenceAdherent2 = adherent.getCode_adherent();
        Reservation reservation2 = new Reservation(reservationId2, dateDepot2, dateLimite2, etat2, referenceAdherent2);

        String reservationId3 = "R123";
        LocalDate dateDepot3 = LocalDate.parse("2024-09-01");
        LocalDate dateLimite3 = LocalDate.parse("2024-12-01");
        String etat3 = "Terminée";
        String referenceAdherent3 = adherent.getCode_adherent();
        Reservation reservation3 = new Reservation(reservationId3, dateDepot3, dateLimite3, etat3, referenceAdherent3);

        ReservationService reservationService = new ReservationService();
        reservationService.makeReservation(adherent, reservation1);
        reservationService.makeReservation(adherent, reservation2);
        reservationService.makeReservation(adherent, reservation3);

        assertEquals(3, adherent.getReservations().size());
    }
}

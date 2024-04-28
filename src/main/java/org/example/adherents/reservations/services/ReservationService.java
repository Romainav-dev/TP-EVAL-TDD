package org.example.adherents.reservations.services;

import org.example.adherents.mails.MailService;
import org.example.adherents.models.Adherent;
import org.example.adherents.reservations.models.Reservation;
import org.example.adherents.reservations.repositories.ReservationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MailService mailService;

    public ReservationService(ReservationRepository reservationRepository, MailService mailService) {
        this.reservationRepository = reservationRepository;
        this.mailService = mailService;
    }

    public boolean makeReservation(Adherent adherent, Reservation reservation) {
        if (adherent.getReservations().size() >= 3) {
            throw new IllegalArgumentException("L'adhérent a déjà atteint le nombre maximum de réservations ouvertes.");
        }
        adherent.getReservations().add(reservation);

        return true;
    }

    public List<Reservation> getOpenReservations(Adherent adherent) {
        List<Reservation> allReservations = reservationRepository.findByAdherentId(adherent.getCode_adherent());

        List<Reservation> openReservations = allReservations.stream()
                .filter(reservation -> "En cours".equals(reservation.getEtat()))
                .collect(Collectors.toList());

        return openReservations;
    }

    public List<Reservation> getReservationHistory(String adherentId) {
        List<Reservation> allReservations = reservationRepository.findByAdherentId(adherentId);

        return allReservations;
    }

    public void sendReminderEmail(Adherent adherent) {
        List<Reservation> allReservations = reservationRepository.findByAdherentId(adherent.getCode_adherent());

        List<Reservation> overdueReservations = filterOverdueReservations(allReservations);

        for (Reservation reservation : overdueReservations) {
            mailService.sendReminderEmail(adherent, reservation);
        }
    }

    private List<Reservation> filterOverdueReservations(List<Reservation> allReservations) {
        List<Reservation> filteredReservations = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (Reservation reservation : allReservations) {
            if (reservation.getDateLimite().isBefore(currentDate)) {
                filteredReservations.add(reservation);
            }
        }

        return filteredReservations;
    }
}


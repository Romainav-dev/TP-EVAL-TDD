package org.example.services;

import org.example.models.Adherent;
import org.example.models.Reservation;
import org.example.repositories.ReservationRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
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
}


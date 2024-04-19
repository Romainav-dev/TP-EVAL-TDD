package org.example.services;

import org.example.models.Adherent;
import org.example.models.Reservation;

public class ReservationService {

    public boolean makeReservation(Adherent adherent, Reservation reservation) {
        if (adherent.getReservations().size() >= 3) {
            throw new IllegalArgumentException("L'adhérent a déjà atteint le nombre maximum de réservations ouvertes.");
        }
        adherent.getReservations().add(reservation);

        return true;
    }
}


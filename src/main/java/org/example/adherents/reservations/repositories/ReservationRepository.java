package org.example.adherents.reservations.repositories;

import org.example.adherents.reservations.models.Reservation;

import java.util.List;

public interface ReservationRepository {
    List<Reservation> findByAdherentId(String adherentId);

    void save(Reservation reservation);
}

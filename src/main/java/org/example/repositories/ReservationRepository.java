package org.example.repositories;

import org.example.models.Reservation;

import java.util.List;

public interface ReservationRepository {
    List<Reservation> findByAdherentId(String adherentId);

    void save(Reservation reservation);
}

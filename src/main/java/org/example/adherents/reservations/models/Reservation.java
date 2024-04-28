package org.example.adherents.reservations.models;

import java.time.LocalDate;

public class Reservation {

    private String Id;
    private LocalDate DateDepot;
    private LocalDate DateLimite;
    private String Etat;
    private String ReferenceAdherent;

    public Reservation(String Id, LocalDate DateDepot, LocalDate DateLimite, String Etat, String ReferenceAdherent) {
        this.Id = Id;
        this.DateDepot = DateDepot;
        this.DateLimite = DateLimite;
        this.Etat = Etat;
        this.ReferenceAdherent = ReferenceAdherent;
    }

    public String getId(){
        return Id;
    }

    public LocalDate getDateDepot() {
        return DateDepot;
    }

    public LocalDate getDateLimite() {
        return DateLimite;
    }

    public String getEtat() {
        return Etat;
    }

    public String getReferenceAdherent() {
        return ReferenceAdherent;
    }

    public void setDepositDate(LocalDate depositDate) {
        if (this.DateLimite != null && depositDate.isAfter(this.DateLimite)) {
            throw new IllegalArgumentException("La date de dépôt ne peut pas être après la date limite.");
        }
        this.DateDepot = depositDate;

        if (depositDate != null) {
            this.DateLimite = depositDate.plusMonths(4);
        } else {
            this.DateLimite = null;
        }
    }

    public void endReservation() {
        this.Etat = "Terminée";
    }

}

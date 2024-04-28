package org.example.adherents.models;

import org.example.adherents.validator.AdherentValidator;
import org.example.interfaces.Validatable;
import org.example.adherents.reservations.models.Reservation;

import java.util.ArrayList;
import java.util.List;

public class Adherent implements Validatable {

    private String code_adherent;
    private String nom;
    private String prenom;
    private String email;
    private String date_naissance;
    private String civilite;
    private List<Reservation> reservations;

    public Adherent(String code_adherent, String nom, String prenom, String email, String date_naissance, String civilite) {
        this.code_adherent = code_adherent;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.date_naissance = date_naissance;
        this.civilite = civilite;
        this.reservations = new ArrayList<>();
    }

    public String getCode_adherent() {
        return code_adherent;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public String getCivilite() {
        return civilite;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    @Override
    public void validate() {
        AdherentValidator.validateAdherent(this);
    }
}

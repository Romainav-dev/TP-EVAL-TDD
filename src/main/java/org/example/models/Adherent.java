package org.example.models;

import org.example.interfaces.Validatable;

import java.util.ArrayList;
import java.util.List;

public class Adherent implements Validatable {

    private String code_adherent;
    private String nom;
    private String prenom;
    private String date_naissance;
    private String civilite;
    private List<Reservation> reservations;

    public Adherent(String code_adherent, String nom, String prenom, String date_naissance, String civilite) {
        this.code_adherent = code_adherent;
        this.nom = nom;
        this.prenom = prenom;
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
        if (code_adherent == null || code_adherent.isEmpty()) {
            throw new IllegalArgumentException("Code adhérent must be positive");
        }
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Nom must not be empty");
        }
        if (prenom == null || prenom.isEmpty()) {
            throw new IllegalArgumentException("Prenom must not be empty");
        }
        if (date_naissance == null || date_naissance.isEmpty()) {
            throw new IllegalArgumentException("Date naissance must not be empty");
        }
        if (civilite == null || civilite.isEmpty()) {
            throw new IllegalArgumentException("Civilité must not be empty");
        }
    }
}

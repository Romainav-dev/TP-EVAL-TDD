package org.example.adherents.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.example.adherents.models.Adherent;

public class AdherentValidator {
    private static final String[] VALID_CIVILITE= {"Monsieur", "Madame"};

    public static boolean validateCivilite(String civilite) {
        for (String validateCivilite : VALID_CIVILITE) {
            if (validateCivilite.equalsIgnoreCase(civilite)) {
                return true;
            }
        }
        return false;
    }

    public static void validateAdherent(Adherent adherent) {
        if (adherent.getCode_adherent() == null || adherent.getCode_adherent().isEmpty()) {
            throw new IllegalArgumentException("Code adhérent must be positive");
        }
        if (adherent.getNom() == null || adherent.getNom().isEmpty() || !NomValide(adherent.getNom())) {
            throw new IllegalArgumentException("Nom must not be empty or longer than 15 characters");
        }
        if (adherent.getPrenom() == null || adherent.getPrenom().isEmpty() || !PrenomValide(adherent.getPrenom())) {
            throw new IllegalArgumentException("Prenom must not be empty or longer than 15 characters");
        }
        if(adherent.getEmail() == null || adherent.getEmail().isEmpty() || !EmailValide(adherent.getEmail())) {
            throw new IllegalArgumentException("Email must not be empty or invalid format");
        }
        if (adherent.getDate_naissance() == null || adherent.getDate_naissance().isEmpty() || !DateNaissanceValide(adherent.getDate_naissance())) {
            throw new IllegalArgumentException("Date naissance must not be empty or invalid format");
        }
        if (adherent.getCivilite() == null || adherent.getCivilite().isEmpty() || !validateCivilite(adherent.getCivilite())) {
            throw new IllegalArgumentException("Civilité must not be empty or invalid format");
        }
    }

    public static boolean NomValide(String nom) {
        return nom.length() <= 15;
    }

    public static boolean PrenomValide(String prenom) {
        return prenom.length() <= 15;
    }

    public static boolean CodeAdherentValide(String codeAdherent) {
        return codeAdherent.matches("[A-Z]{2}\\d{6}");
    }

    public static boolean DateNaissanceValide(String dateNaissance) {
        try {
            LocalDate date = LocalDate.parse(dateNaissance, DateTimeFormatter.ISO_DATE);
            LocalDate dateActuelle = LocalDate.now();
            return date.isBefore(dateActuelle);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean EmailValide(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

}

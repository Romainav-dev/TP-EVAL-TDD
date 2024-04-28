package org.example.adherent;

import org.example.adherents.models.Adherent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdherentTest {
    @Test
    public void testAddAdherentWithoutAdherentCode() {
        Adherent adherent = new Adherent("", "Doe", "John", "john.doe@example.com","1990-01-01", "Monsieur");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testAddAdherentWithoutLastName() {
        Adherent adherent = new Adherent("AD123", "", "John", "john.doe@example.com","1990-01-01", "Monsieur");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testAddAdherentWithoutFirstName() {
        Adherent adherent = new Adherent("AD123", "Doe", "", "john.doe@example.com","1990-01-01", "Monsieur");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testAddAdherentWithoutDateOfBirth() {
        Adherent adherent = new Adherent("AD123", "Doe", "John", "john.doe@example.com","", "Monsieur");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testAddAdherentWithoutCivility() {
        Adherent adherent = new Adherent("AD123", "Doe", "John", "john.doe@example.com","1990-01-01", "");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testAddAdherentWithInvalidCivility() {
        Adherent adherent = new Adherent("AD123", "Doe", "John", "john.doe@example.com","1990-01-01", "Femme");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testAddAdherentWithoutEmail() {
        Adherent adherent = new Adherent("AD123", "Doe", "John", "","1990-01-01", "Monsieur");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testAddValidAdherent() {
        Adherent adherent = new Adherent("AD123", "Doe", "John", "john.doe@example.com","1990-01-01", "Monsieur");
        assertDoesNotThrow(adherent::validate);
    }

    @Test
    public void testNomLongueurMaximaleAutoriseeDepasse() {
        Adherent adherent = new Adherent("AD123", "Talourdelacarterie", "John", "john.doe@example.com","1990-01-01", "Monsieur");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testNomLongueurValide() {
        Adherent adherent = new Adherent("AD123", "Talourdelacarte", "John", "john.doe@example.com","1990-01-01", "Monsieur");
        assertDoesNotThrow(adherent::validate);
    }

    @Test
    public void testPrenomLongueurMaximaleAutoriseeDepasse() {
        Adherent adherent = new Adherent("AD123", "Talourdelacarte", "Jean Pierre Robert", "john.doe@example.com","1990-01-01", "Monsieur");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testPrenomLongueurValide() {
        Adherent adherent = new Adherent("AD123", "Talourdelacarte", "Jean Pierre", "john.doe@example.com","1990-01-01", "Monsieur");
        assertDoesNotThrow(adherent::validate);
    }

    @Test
    public void testCodeAdherentFormatInvalide() {
        Adherent adherent = new Adherent("AD1234567", "Talourdelacarte", "Jean Pierre", "john.doe@example.com","1990-01-01", "Monsieur");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testCodeAdherentFormatValide() {
        Adherent adherent = new Adherent("ABC123", "Talourdelacarte", "Jean Pierre", "john.doe@example.com","1990-01-01", "Monsieur");
        assertDoesNotThrow(adherent::validate);
    }

    @Test
    public void testDateNaissanceFormatInvalide() {
        Adherent adherent = new Adherent("ABC123", "Talourdelacarte", "Jean Pierre", "john.doe@example.com","01/01/1990", "Monsieur");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testDateNaissanceFormatValide() {
        Adherent adherent = new Adherent("ABC123", "Talourdelacarte", "Jean Pierre", "john.doe@example.com","1990-01-01", "Monsieur");
        assertDoesNotThrow(adherent::validate);
    }

    @Test
    public void testCivilitéInvalide() {
        Adherent adherent = new Adherent("ABC123", "Talourdelacarte", "Jean Pierre", "john.doe@example.com","1990-01-01", "Docteur");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testCivilitéValide() {
        Adherent adherent = new Adherent("ABC123", "Talourdelacarte", "Jean Pierre", "john.doe@example.com","1990-01-01", "Monsieur");
        assertDoesNotThrow(adherent::validate);
    }

    @Test
    public void testEmailFormatInvalide() {
        Adherent adherent = new Adherent("ABC123", "Talourdelacarte", "Jean Pierre", "johndoe@example","1990-01-01", "Monsieur");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testEmailFormatValide() {
        Adherent adherent = new Adherent("ABC123", "Talourdelacarte", "Jean Pierre", "john.doe@example.com","1990-01-01", "Monsieur");
        assertDoesNotThrow(adherent::validate);
    }
}

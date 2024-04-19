package org.example.adherent;

import org.example.models.Adherent;
import org.example.models.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdherentTest {
    @Test
    public void testAddAdherentWithoutAdherentCode() {
        Adherent adherent = new Adherent("", "Doe", "John", "1990-01-01", "Monsieur");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testAddAdherentWithoutLastName() {
        Adherent adherent = new Adherent("AD123", "", "John", "1990-01-01", "Monsieur");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testAddAdherentWithoutFirstName() {
        Adherent adherent = new Adherent("AD123", "Doe", "", "1990-01-01", "Monsieur");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testAddAdherentWithoutDateOfBirth() {
        Adherent adherent = new Adherent("AD123", "Doe", "John", "", "Monsieur");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testAddAdherentWithoutCivility() {
        Adherent adherent = new Adherent("AD123", "Doe", "John", "1990-01-01", "");
        assertThrows(IllegalArgumentException.class, adherent::validate);
    }

    @Test
    public void testAddValidAdherent() {
        Adherent adherent = new Adherent("AD123", "Doe", "John", "1990-01-01", "Monsieur");
        assertDoesNotThrow(adherent::validate);
    }
}

package org.example.adherents.mails;

import org.example.adherents.models.Adherent;
import org.example.adherents.reservations.models.Reservation;

public class MailService {
    public void sendReminderEmail(Adherent adherent, Reservation reservation) {
        String recipientEmail = adherent.getEmail(); // Récupérer l'adresse e-mail de l'adhérent
        String subject = "Rappel de réservation";
        String message = "Cher " + adherent.getPrenom() + ",\n\n"
                + "Ceci est un rappel pour la réservation avec le numéro " + reservation.getId() + "."
                + "Veuillez prendre note que cette réservation est en retard.\n\n"
                + "Cordialement,\n"
                + "Votre service de réservation";

        // Simuler l'envoi de l'e-mail de rappel
        sendEmail(recipientEmail, subject, message);
    }

    public void sendEmail(String recipient, String subject, String message) {
        // Implémenter la logique d'envoi d'e-mail réel ici
        System.out.println("Envoi d'un e-mail à : " + recipient);
        System.out.println("Sujet : " + subject);
        System.out.println("Corps du message : " + message);
        System.out.println("L'e-mail de rappel a été envoyé avec succès !");
    }
}


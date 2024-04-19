package org.example.books;

import org.example.exceptions.InvalidIsbnCharactersException;
import org.example.exceptions.InvalidIsbnLengthException;

public class IsbnValidator {

    private int total = 0;

    private static final int SHORT_ISBN_LENGTH = 10;
    private static final int SHORT_ISBN_DIVIDER = 11;

    private static final int LONG_ISBN_LENGTH = 13;
    private static final int LONG_ISBN_DIVIDER = 10;


    public boolean validateISBN(String isbn) throws InvalidIsbnLengthException {
        if (isbn.length() == SHORT_ISBN_LENGTH) {
            for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
                if (!Character.isDigit(isbn.charAt(i))) {
                    if (i == 9 && isbn.charAt(9) == 'X') {
                        total += 10;
                        break;
                    } else {
                        throw new InvalidIsbnCharactersException();
                    }
                }
                total += Character.getNumericValue(isbn.charAt(i)) * (SHORT_ISBN_LENGTH - i);
            }

            if (total % SHORT_ISBN_DIVIDER == 0)
                return true;
            else
                return false;

        } else if (isbn.length() == LONG_ISBN_LENGTH) {
            for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
                if (!Character.isDigit(isbn.charAt(i))) {
                    throw new InvalidIsbnCharactersException();
                }
                var Poids = Character.getNumericValue(isbn.charAt(i) - '0');
                if (i % 2 == 0) {
                    total += Poids;
                } else {
                    total += Poids * 3;
                }
                total += Character.getNumericValue(isbn.charAt(i)) * (LONG_ISBN_LENGTH - i);
            }

            if (total % LONG_ISBN_DIVIDER == 0)
                return true;
            else
                return false;
        }
        throw new InvalidIsbnLengthException();
    }
}

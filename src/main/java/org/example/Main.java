package org.example;

import com.github.javafaker.Faker;
import org.example.esential.Catalog;
import org.example.esential.Libro;
import org.example.esential.Rivista;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Catalog> catalogo = new ArrayList<>();
        //creazione di libri/riviste pre-caricate nel catalogo
        for (int i = 0; i < 10; i++) {
            creazioneCataloghiCas(catalogo);
        }
        visualizzaCatalogo(catalogo);

        Scanner scanner = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("Scegli un'opzione:");
            System.out.println("1. Aggiunta di un elemento");
            System.out.println("2. Rimozione di un elemento dato un codice ISBN");
            System.out.println("3. Ricerca per ISBN");
            System.out.println("4. Ricerca per anno pubblicazione");
            System.out.println("5. Ricerca per autore");
            System.out.println("6. Salvataggio su disco dell'archivio");
            System.out.println("7. Caricamento dal disco dell'archivio");
            System.out.println("0. Esci");

            scelta = Integer.parseInt(scanner.nextLine());

            switch (scelta) {
                case 1:
                    // Aggiunta di un elemento
                    aggiungiElementoCatalog(catalogo);
                    System.out.println("La nuovo lista del catalogo-----------------");
                    visualizzaCatalogo(catalogo);
                    break;
                case 2:
                    // Rimozione di un elemento dato un codice ISBN
                    System.out.println("inserisci ISBN da eliminare:");
                    int isbn = Integer.parseInt(scanner.nextLine());
                    rimuoviIsbn(catalogo, isbn);
                    break;
                case 3:
                    // Ricerca per ISBN
                    break;
                case 4:
                    // Ricerca per anno pubblicazione
                    break;
                case 5:
                    // Ricerca per autore
                    break;
                case 6:
                    // Salvataggio su disco dell'archivio
                    break;
                case 7:
                    // Caricamento dal disco dell'archivio
                    break;
                case 0:
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        } while (scelta != 0);

        scanner.close();
    }


    // creazione del catalogo di libri/riviste iniziale come se fosse un mag.
    public static void creazioneCataloghiCas(ArrayList<Catalog> catalogo) {
        Faker faker = new Faker(Locale.ITALY);
        int tipo = faker.number().numberBetween(1, 3);
        if (tipo == 1) {
            Libro libro = new Libro(faker.code().isbn10(), faker.book().title(), faker.number().numberBetween(1700, 2023), faker.number().numberBetween(1900, 2023), faker.book().author(), faker.book().genre());
            catalogo.add(libro);
        } else if (tipo == 2) {
            Periodicita periodo;
            int periodicita = faker.number().numberBetween(1, 4);
            switch (periodicita) {
                case 1:
                    periodo = Periodicita.SEMESTRALE;
                    break;
                case 2:
                    periodo = Periodicita.MENSILE;
                    break;
                case 3:
                    periodo = Periodicita.SETTIMANALE;
                    break;
                default:
                    return;
            }

            Rivista rivista = new Rivista(faker.code().isbn10(), faker.book().title(), faker.number().numberBetween(1700, 2023), faker.number().numberBetween(1900, 2023), periodo);
            catalogo.add(rivista);
        }
    }

    //stampa del mag diviso per tipologia
    public static void visualizzaCatalogo(ArrayList<Catalog> catalogo) {
        System.out.println("Libri:");
        catalogo.stream()
                .filter(elemento -> elemento instanceof Libro)
                .map(elemento -> (Libro) elemento)
                .forEach(libro -> {
                    System.out.println("Codice ISBN: " + libro.getCodiceISBN());
                    System.out.println("Titolo: " + libro.getTitolo());
                    System.out.println("Anno pubblicazione: " + libro.getAnnoPubblicazione());
                    System.out.println("Numero pagine: " + libro.getNumeroPagine());
                    System.out.println("Autore: " + libro.getAutore());
                    System.out.println("Genere: " + libro.getGenere());
                    System.out.println();
                });
        System.out.println("------------------------------------------------------------------");
        System.out.println("Riviste:");
        catalogo.stream()
                .filter(elemento -> elemento instanceof Rivista)
                .map(elemento -> (Rivista) elemento)
                .forEach(rivista -> {
                    System.out.println("Codice ISBN: " + rivista.getCodiceISBN());
                    System.out.println("Titolo: " + rivista.getTitolo());
                    System.out.println("Anno pubblicazione: " + rivista.getAnnoPubblicazione());
                    System.out.println("Numero pagine: " + rivista.getNumeroPagine());
                    System.out.println("Periodicità: " + rivista.getPeriodicita());
                    System.out.println();
                });
    }

    public static void aggiungiElementoCatalog(ArrayList<Catalog> catalogo) {
        Faker faker = new Faker(Locale.ITALY);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vuoi aggiungere un libro o una rivista? (1 = libro, 2 = rivista)");
        int tipo = Integer.parseInt(scanner.nextLine());
        System.out.println("Inserisci il titolo:");
        String titolo = scanner.nextLine();
        System.out.println("Inserisci l'anno di pubblicazione:");
        int anno = Integer.parseInt(scanner.nextLine());
        System.out.println("Inserisci il numero di pagine:");
        int pagine = Integer.parseInt(scanner.nextLine());
        if (tipo == 1) {
            System.out.println("Inserisci l'autore:");
            String autore = scanner.nextLine();

            System.out.println("Inserisci il genere:");
            String genere = scanner.nextLine();

            Libro libro = new Libro(faker.code().isbn10(), titolo, anno, pagine, autore, genere);
            catalogo.add(libro);
        } else if (tipo == 2) {
            System.out.println("Inserisci la periodicità (1 = settimanale, 2 = mensile, 3 = semestrale):");
            int periodicita = Integer.parseInt(scanner.nextLine());
            Periodicita periodo;
            switch (periodicita) {
                case 1:
                    periodo = Periodicita.SEMESTRALE;
                    break;
                case 2:
                    periodo = Periodicita.MENSILE;
                    break;
                case 3:
                    periodo = Periodicita.SETTIMANALE;
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
                    return;
            }


            Rivista rivista = new Rivista(faker.code().isbn10(), titolo, anno, pagine, periodo);
            catalogo.add(rivista);
        } else {
            System.out.println("Scelta non valida. Riprova.");
        }
    }


    //eliminazione di elementi tramite codice isbn
    public static void rimuoviIsbn(ArrayList<Catalog> catalogo, int isbn) {
        if (catalogo.stream().anyMatch(catalog -> catalog.getCodiceISBN().equals(isbn))) {
            catalogo.removeIf(catalog -> catalog.getCodiceISBN().equals(isbn));
            System.out.println("hai rimosso con successo");
        } else {
            System.out.println("non abbiamo trovato un elemento con isbn:" + isbn + " da rimuovere");
        }

    }
}





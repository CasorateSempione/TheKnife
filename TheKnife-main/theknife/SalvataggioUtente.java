package theknife;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe <code>SalvataggioUtente</code> gestisce la persistenza dei dati
 * relativi agli utenti dell'applicazione TheKnife.
 * <p>
 * I dati vengono salvati in un file di testo strutturato, dove ogni riga
 * rappresenta un utente e contiene i campi separati da punto e virgola.
 * </p>
 *
 * <p>
 * La classe supporta il caricamento, il salvataggio, l'aggiunta e la rimozione
 * degli utenti, distinguendo automaticamente tra clienti e ristoratori in base
 * al ruolo salvato nel file.
 * </p>
 *
 * @version 1.0
 */
public class SalvataggioUtente {

    /** Nome del file di salvataggio degli utenti. */
    private String file = "utenti.txt";

    /**
     * Carica tutti gli utenti dal file di testo.
     *
     * @return una lista contenente tutti gli utenti caricati
     */
    private List<Utente> load() {
        List<Utente> lista = new ArrayList<>();

        try {
            if (!Files.exists(Paths.get(file))) return lista;

            for (String line : Files.readAllLines(Paths.get(file))) {

                if (line.isBlank()) continue;

                String[] t = line.split(";");

                if (t.length < 7) {
                    System.out.println("Riga malformata: " + line);
                    continue;
                }

                String ruolo = t[6].toLowerCase();
                Utente u;

                if (ruolo.equals("cliente")) {
                    u = new Cliente(
                            t[0], // nome
                            t[1], // cognome
                            t[2], // mail
                            t[5], // username
                            t[4], // password
                            t[3], // domicilio
                            t[6]  // ruolo
                    );

                } else if (ruolo.equals("ristoratore")) {
                    u = new Ristoratore(
                            t[0], // nome
                            t[1], // cognome
                            t[2], // mail
                            t[5], // username
                            t[4], // password
                            t[3], // domicilio
                            t[6]  // ruolo
                    );

                } else {
                    System.out.println("Ruolo sconosciuto: " + ruolo);
                    continue;
                }

                lista.add(u);
            }

        } catch (Exception e) {
            System.out.println("Errore caricando: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Salva l'intera lista degli utenti nel file.
     *
     * @param lista la lista degli utenti da salvare
     */
    private void save(List<Utente> lista) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file))) {

            for (Utente u : lista) {
                writer.write(String.join(";",
                        u.getNome(),
                        u.getcognome(),
                        u.getmail(),
                        u.getdomicilio(),
                        u.getpassword(),
                        u.getusername(),
                        u.getruolo()
                ));
                writer.newLine();
            }

        } catch (Exception e) {
            System.out.println("Errore salvando: " + e.getMessage());
        }
    }

    /**
     * Aggiunge un nuovo utente al file.
     *
     * @param u l'utente da aggiungere
     */
    public void aggiungiUtente(Utente u) {
        List<Utente> lista = load();
        lista.add(u);
        save(lista);
    }

    /**
     * Rimuove un utente dal file confrontando il nome utente.
     *
     * @param u l'utente da rimuovere
     */
    public void rimuoviUtente(Utente u) {
        List<Utente> lista = load();
        lista.removeIf(existing -> existing.getusername().equals(u.getusername()));
        save(lista);
    }

    /**
     * Restituisce tutti gli utenti salvati.
     *
     * @return lista degli utenti
     */
    public List<Utente> getTutti() {
        return load();
    }
}

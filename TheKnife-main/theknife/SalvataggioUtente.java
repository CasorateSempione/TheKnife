package theknife;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Classe che si occupa di salvare e caricare gli utenti da un file.
 * <p>Gestisce la lettura, la scrittura e la conversione delle righe del file
 * nei rispettivi oggetti Cliente o Ristoratore.
 */
public class SalvataggioUtente {

    /**
     * Percorso del file dove vengono salvati gli utenti.
     */
   private String file = "data/utenti.txt";

    /**
     * Carica tutti gli utenti presenti nel file.
     * <p>Ogni riga viene letta, divisa in campi e trasformata
     * nell’oggetto corretto in base al ruolo.
     *
     * @return lista di utenti caricati dal file
     */
    protected List<Utente> load() {
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
                    u = new Cliente(t[0], t[1], t[2], t[3], t[4], t[5], t[6]);
                } else if (ruolo.equals("ristoratore")) {
                    u = new Ristoratore(t[0], t[1], t[2], t[3], t[4], t[5], t[6]);
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
     * Salva su file la lista completa degli utenti.
     * <p>Ogni utente viene scritto come una riga con i campi separati da “;”.
     *
     * @param lista lista di utenti da salvare
     */
    private void save(List<Utente> lista) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file))) {
            for (Utente u : lista) {
                writer.write(String.join(";",
                        u.getNome(),
                        u.getcognome(),
                        u.getdomicilio(),
                        u.getmail(),
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
     * @param u utente da aggiungere
     */
    public void aggiungiUtente(Utente u) {
        List<Utente> lista = load();
        lista.add(u);
        save(lista);
    }

    /**
     * Rimuove un utente dal file confrontando lo username.
     *
     * @param u utente da rimuovere
     */
    public void rimuoviUtente(Utente u) {
        List<Utente> lista = load();
        lista.removeIf(existing -> existing.getusername().equals(u.getusername()));
        save(lista);
    }

    /**
     * Restituisce tutti gli utenti salvati nel file.
     *
     * @return lista completa degli utenti
     */
    public List<Utente> getTutti() {
        return load();
    }
}



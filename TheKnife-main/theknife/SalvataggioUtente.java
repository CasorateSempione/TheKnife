package theknife;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che si occupa di salvare e caricare gli utenti da file.
 */
public class SalvataggioUtente {

    /**
     * Percorso del file degli utenti.
     */
    private final Path file;

    /**
     * Costruttore.
     * Cerca il file utenti nella struttura del progetto.
     */
    public SalvataggioUtente() {

        Path percorso1 = Paths.get("data", "utenti.txt");
        Path percorso2 = Paths.get("TheKnife-main", "data", "utenti.txt");

        if (Files.exists(percorso1)) {
            file = percorso1;
        } else if (Files.exists(percorso2)) {
            file = percorso2;
        } else {
            /*
             * Se il file non esiste ancora, utilizziamo il percorso
             * della struttura del progetto.
             */
            file = percorso2;
        }
    }

    /**
     * Carica tutti gli utenti presenti nel file.
     *
     * @return lista degli utenti caricati
     */
    protected List<Utente> load() {

        List<Utente> lista = new ArrayList<>();

        try {

            if (!Files.exists(file)) {
                return lista;
            }

            List<String> righe = Files.readAllLines(
                    file,
                    StandardCharsets.UTF_8
            );

            for (String line : righe) {

                if (line == null || line.isBlank()) {
                    continue;
                }

                String[] t = line.split(";");

                if (t.length < 7) {
                    System.out.println(
                            "Riga malformata nel file utenti: " + line
                    );
                    continue;
                }

                String ruolo = t[6].trim();

                Utente u;

                if (ruolo.equalsIgnoreCase("Cliente")) {

                    u = new Cliente(
                            t[0],
                            t[1],
                            t[2],
                            t[3],
                            t[4],
                            t[5],
                            t[6]
                    );

                } else if (ruolo.equalsIgnoreCase("Ristoratore")) {

                    u = new Ristoratore(
                            t[0],
                            t[1],
                            t[2],
                            t[3],
                            t[4],
                            t[5],
                            t[6]
                    );

                } else {

                    System.out.println(
                            "Ruolo sconosciuto: " + ruolo
                    );

                    continue;
                }

                lista.add(u);
            }

        } catch (Exception e) {

            System.out.println(
                    "Errore durante il caricamento degli utenti: "
                            + e.getMessage()
            );
        }

        return lista;
    }

    /**
     * Salva la lista completa degli utenti nel file.
     *
     * @param lista lista degli utenti da salvare
     */
    private void save(List<Utente> lista) {

        try {

            /*
             * Crea la cartella data se non esiste.
             */
            if (file.getParent() != null) {
                Files.createDirectories(file.getParent());
            }

            try (BufferedWriter writer = Files.newBufferedWriter(
                    file,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE
            )) {

                for (Utente u : lista) {

                    writer.write(
                            String.join(
                                    ";",
                                    u.getNome(),
                                    u.getcognome(),
                                    u.getdomicilio(),
                                    u.getmail(),
                                    u.getpassword(),
                                    u.getusername(),
                                    u.getruolo()
                            )
                    );

                    writer.newLine();
                }
            }

            System.out.println(
                    "Utente salvato nel file: "
                            + file.toAbsolutePath()
            );

        } catch (Exception e) {

            System.out.println(
                    "ERRORE durante il salvataggio degli utenti:"
            );

            e.printStackTrace();
        }
    }

    /**
     * Aggiunge un nuovo utente al file.
     *
     * @param u utente da salvare
     */
    public void aggiungiUtente(Utente u) {

        if (u == null) {
            System.out.println(
                    "Errore: impossibile salvare un utente nullo."
            );
            return;
        }

        List<Utente> lista = load();

        lista.add(u);

        save(lista);
    }

    /**
     * Rimuove un utente dal file tramite username.
     *
     * @param u utente da rimuovere
     */
    public void rimuoviUtente(Utente u) {

        if (u == null) {
            return;
        }

        List<Utente> lista = load();

        lista.removeIf(
                existing ->
                        existing.getusername()
                                .equals(u.getusername())
        );

        save(lista);
    }

    /**
     * Restituisce tutti gli utenti presenti nel file.
     *
     * @return lista degli utenti
     */
    public List<Utente> getTutti() {
        return load();
    }
}
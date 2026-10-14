package theknife;

import java.io.BufferedWriter;
import java.io.IOException;
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
     * Cerca il file utenti.txt nella struttura del progetto.
     */
    public SalvataggioUtente() {

        Path cartellaCorrente = Paths.get("")
                .toAbsolutePath()
                .normalize();

        Path file1 = cartellaCorrente.resolve(
                Paths.get(
                        "TheKnife",
                        "TheKnife-main",
                        "data",
                        "utenti.txt"
                )
        );

        Path file2 = cartellaCorrente.resolve(
                Paths.get(
                        "TheKnife-main",
                        "data",
                        "utenti.txt"
                )
        );

        Path file3 = cartellaCorrente.resolve(
                Paths.get(
                        "data",
                        "utenti.txt"
                )
        );

        if (Files.isRegularFile(file1)) {
            file = file1;
        } else if (Files.isRegularFile(file2)) {
            file = file2;
        } else if (Files.isRegularFile(file3)) {
            file = file3;
        } else {
            throw new IllegalStateException(
                    "File utenti.txt non trovato.\n"
                            + "Percorsi controllati:\n"
                            + file1 + "\n"
                            + file2 + "\n"
                            + file3
            );
        }

        System.out.println(
                "File utenti utilizzato: "
                        + file.toAbsolutePath()
        );
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

                String[] t = line.split(";", -1);

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

        } catch (IOException e) {

            System.err.println(
                    "Errore durante il caricamento degli utenti da: "
                            + file.toAbsolutePath()
            );

            e.printStackTrace();
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

            Path cartella = file.getParent();

            if (cartella != null) {
                Files.createDirectories(cartella);
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
                    "Salvataggio completato. Numero di utenti: "
                            + lista.size()
            );

            System.out.println(
                    "File aggiornato: "
                            + file.toAbsolutePath()
            );

        } catch (IOException e) {

            System.err.println(
                    "Errore durante il salvataggio degli utenti in: "
                            + file.toAbsolutePath()
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
            System.err.println(
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
                existing -> existing.getusername().equals(u.getusername())
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
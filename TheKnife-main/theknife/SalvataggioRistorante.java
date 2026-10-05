package theknife;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Classe che gestisce il salvataggio e il caricamento dei ristoranti da file.
 * <p>Ogni ristorante viene salvato con le sue informazioni principali
 * e con tutte le recensioni collegate.
 */
public class SalvataggioRistorante {

    /**
     * Percorso del file dove vengono salvati i ristoranti.
     */
    private String file = "TheKnife-main/data/Ristorante.txt";

    /**
     * Carica tutti i ristoranti presenti nel file.
     * <p>Il file è strutturato a sezioni: una sezione per il ristorante
     * e una per ogni recensione collegata.
     *
     * @return lista di ristoranti caricati dal file
     */
    private List<Ristorante> load() {
        List<Ristorante> lista = new ArrayList<>();
        Ristorante current = null;

        try {
            if (!Files.exists(Paths.get(file))) return lista;

            for (String line : Files.readAllLines(Paths.get(file))) {

                if (line.isBlank()) continue;

                // indica l'inizio di un nuovo ristorante
                if (line.equals("[RISTORANTE]")) {
                    current = null;
                    continue;
                }

                // indica l'inizio di una recensione
                if (line.equals("[RECENSIONE]")) {
                    continue;
                }

                // se current è null, stiamo leggendo i dati del ristorante
                if (current == null) {
                    String[] t = line.split(";");
                    String usernameRistoratore = (t.length > 11) ? t[11] : "";

                    current = new Ristorante(
                            t[0], t[1], t[2], t[3], t[4],
                            Double.parseDouble(t[5]),
                            Double.parseDouble(t[6]),
                            Double.parseDouble(t[7]),
                            t[8].equals("si"),
                            t[9].equals("si"),
                            t[10],
                            usernameRistoratore
                    );

                    lista.add(current);

                } else {
                    // altrimenti stiamo leggendo una recensione
                    String[] t = line.split(";");

                    Recensioni rec = new Recensioni(
                            t[0], t[1], t[2], t[3],
                            Integer.parseInt(t[4]),
                            t[5],
                            t.length > 6 ? t[6] : "",
                            t.length > 7 ? t[7] : ""
                    );

                    current.addRecensione(rec);
                }
            }

        } catch (Exception e) {
            System.out.println("Errore caricando: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Salva su file tutti i ristoranti e le loro recensioni.
     * <p>Ogni ristorante viene scritto con un blocco dedicato,
     * seguito dalle sue recensioni.
     *
     * @param lista lista dei ristoranti da salvare
     */
    private void save(List<Ristorante> lista) {

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(file))) {

            for (Ristorante r : lista) {

                bw.write("[RISTORANTE]\n");
                bw.write(String.join(";",
                        r.getId(),
                        r.getnome(),
                        r.getNazione(),
                        r.getCitta(),
                        r.getIndirizzo(),
                        "" + r.getLatitudine(),
                        "" + r.getLongitudine(),
                        "" + r.getFasciaPrezzo(),
                        r.isDelivery() ? "si" : "no",
                        r.isPrenotazioneOnline() ? "si" : "no",
                        r.getTipoCucina(),
                        r.getusernameRistoratore()
                ) + "\n");

                for (Recensioni rec : r.getRecensioni()) {
                    bw.write("[RECENSIONE]\n");
                    bw.write(String.join(";",
                            rec.getRiristoratore(),
                            rec.getId(),
                            rec.getRistoranteid(),
                            rec.getAutore(),
                            "" + rec.getNumeroStelle(),
                            rec.getCommento(),
                            rec.getrispostaAutore(),
                            rec.getRispostaTesto()
                    ) + "\n");
                }
            }

        } catch (Exception e) {
            System.out.println("Errore salvataggio: " + e.getMessage());
        }
    }

    /**
     * Aggiunge un nuovo ristorante al file.
     *
     * @param r ristorante da aggiungere
     */
    public void aggiungiRistorante(Ristorante r) {
        List<Ristorante> lista = load();
        lista.add(r);
        save(lista);
    }

    /**
     * Aggiorna un ristorante già presente nel file.
     * <p>La ricerca avviene tramite l'id del ristorante.
     *
     * @param aggiornato ristorante aggiornato
     */
    public void aggiornaRistorante(Ristorante aggiornato) {
        List<Ristorante> lista = load();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals(aggiornato.getId())) {
                lista.set(i, aggiornato);
                break;
            }
        }
        save(lista);
    }

    /**
     * Aggiunge una recensione a un ristorante specifico.
     *
     * @param idRist id del ristorante
     * @param rec recensione da aggiungere
     */
    public void aggiungiRecensione(String idRist, Recensioni rec) {
        List<Ristorante> lista = load();
        for (Ristorante r : lista) {
            if (r.getId().equals(idRist)) {
                r.addRecensione(rec);
                break;
            }
        }
        save(lista);
    }

    /**
     * Rimuove un ristorante dal file tramite il suo id.
     *
     * @param id id del ristorante da rimuovere
     */
    public void rimuoviRistorante(String id) {
        List<Ristorante> lista = load();
        lista.removeIf(r -> r.getId().equals(id));
        save(lista);
    }

    /**
     * Restituisce tutti i ristoranti salvati nel file.
     *
     * @return lista completa dei ristoranti
     */
    public List<Ristorante> getTutti() {
        return load();
    }
}
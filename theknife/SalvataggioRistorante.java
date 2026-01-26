package theknife;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe <code>SalvataggioRistorante</code> gestisce la persistenza dei dati
 * relativi ai ristoranti e alle loro recensioni.
 * <p>
 * I dati vengono salvati in un file di testo strutturato tramite marcatori
 * come <strong>[RISTORANTE]</strong> e <strong>[RECENSIONE]</strong>.
 * </p>
 *
 * <p>
 * La classe fornisce metodi per:
 * </p>
 * <ul>
 *     <li>caricare tutti i ristoranti dal file</li>
 *     <li>salvare l'intera lista dei ristoranti</li>
 *     <li>aggiungere un nuovo ristorante</li>
 *     <li>aggiungere una recensione a un ristorante</li>
 *     <li>rimuovere un ristorante</li>
 * </ul>
 *
 * @author CADDU
 * @version 1.0
 */
public class SalvataggioRistorante {

    /** Nome del file di salvataggio. */
    private String file = "ristoranti.txt";

    /**
     * Carica tutti i ristoranti e le relative recensioni dal file.
     *
     * @return una lista di oggetti <code>Ristorante</code>
     */
    private List<Ristorante> load() {
        List<Ristorante> lista = new ArrayList<>();
        Ristorante current = null;

        try {
            if (!Files.exists(Paths.get(file))) return lista;

            for (String line : Files.readAllLines(Paths.get(file))) {

                if (line.isBlank()) continue;

                if (line.equals("[RISTORANTE]")) {
                    current = null;
                    continue;
                }

                if (line.equals("[RECENSIONE]")) {
                    continue;
                }

                // Caricamento ristorante
                if (current == null) {
                    String[] t = line.split(";");

                    current = new Ristorante(
                            t[0], t[1], t[2], t[3], t[4],
                            Double.parseDouble(t[5]),
                            Double.parseDouble(t[6]),
                            Double.parseDouble(t[7]),
                            t[8].equals("si"),
                            t[9].equals("si"),
                            t[10]
                    );

                    lista.add(current);

                } else {
                    // Caricamento recensione
                    String[] t = line.split(";");

                    Recensioni rec = new Recensioni(
                            t[0],      // id recensione
                            t[1],      // id ristorante
                            t[2],      // autore
                            Integer.parseInt(t[3]), // stelle
                            t[4],      // commento
                            t.length > 5 ? t[5] : null, // risposta autore
                            t.length > 6 ? t[6] : null  // risposta testo
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
     * Salva l'intera lista dei ristoranti nel file.
     *
     * @param lista lista dei ristoranti da salvare
     */
    private void save(List<Ristorante> lista) {

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(file))) {

            for (Ristorante r : lista) {

                bw.write("[RISTORANTE]\n");
                bw.write(String.join(";",
                        r.getId(),
                        r.getNome(),
                        r.getNazione(),
                        r.getCitta(),
                        r.getIndirizzo(),
                        "" + r.getLatitudine(),
                        "" + r.getLongitudine(),
                        "" + r.getFasciaPrezzo(),
                        r.isDelivery() ? "si" : "no",
                        r.isPrenotazioneOnline() ? "si" : "no",
                        r.getTipoCucina()
                ) + "\n");

                for (Recensioni rec : r.getRecensioni()) {
                    bw.write("[RECENSIONE]\n");
                    bw.write(String.join(";",
                            rec.getId(),
                            rec.getRistoranteid(),
                            rec.getAutore(),
                            "" + rec.getNumeroStelle(),
                            rec.getCommento(),
                            rec.getRisposta() != null ? rec.getRisposta().getAutore() : "",
                            rec.getRisposta() != null ? rec.getRisposta().getTesto() : ""
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
     * @param r il ristorante da aggiungere
     */
    public void aggiungiRistorante(Ristorante r) {
        List<Ristorante> lista = load();
        lista.add(r);
        save(lista);
    }

    /**
     * Aggiunge una recensione a un ristorante esistente.
     *
     * @param idRist id del ristorante
     * @param rec    recensione da aggiungere
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
     * Rimuove un ristorante dal file.
     *
     * @param id identificativo del ristorante da rimuovere
     */
    public void rimuoviRistorante(String id) {
        List<Ristorante> lista = load();
        lista.removeIf(r -> r.getId().equals(id));
        save(lista);
    }

    /**
     * Restituisce tutti i ristoranti salvati.
     *
     * @return lista di ristoranti
     */
    public List<Ristorante> getTutti() {
        return load();
    }
}
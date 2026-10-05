package theknife;

import java.io.*;
import java.util.*;

/**
 * Gestisce il salvataggio delle recensioni nel file
 * data/Recensioni.txt.
 *
 * Le recensioni sono separate dai ristoranti e vengono
 * collegate tramite l'id del ristorante.
 */
public class SalvataggioRecensioni {

    private String file =
            "C:\\Users\\Utente\\Desktop\\Esposito_760476\\TheKnife\\TheKnife-main\\data\\Recensioni.txt";


    /**
     * Carica tutte le recensioni dal file.
     *
     * Formato:
     *
     * idRecensione;
     * idRistorante;
     * autore;
     * numeroStelle;
     * commento;
     * rispostaAutore;
     * rispostaTesto;
     * ristoratore
     */
    private List<Recensioni> load() {

        List<Recensioni> lista = new ArrayList<>();

        File fileRecensioni = new File(file);

        // Se il file non esiste lo creiamo
        if (!fileRecensioni.exists()) {

            try {
                fileRecensioni.createNewFile();
            } catch (IOException e) {
                System.out.println(
                        "Errore creazione Recensioni.txt: "
                                + e.getMessage()
                );
            }

            return lista;
        }

        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader(fileRecensioni))) {

            String riga;

            while ((riga = br.readLine()) != null) {

                if (riga.isBlank()) {
                    continue;
                }

                String[] campi = riga.split(";", -1);

                if (campi.length < 8) {
                    continue;
                }

                try {

                    String idRecensione = campi[0];
                    String idRistorante = campi[1];
                    String autore = campi[2];

                    int numeroStelle =
                            Integer.parseInt(campi[3]);

                    String commento = campi[4];
                    String rispostaAutore = campi[5];
                    String rispostaTesto = campi[6];
                    String ristoratore = campi[7];

                    Recensioni recensione =
                            new Recensioni(
                                    ristoratore,
                                    idRecensione,
                                    idRistorante,
                                    autore,
                                    numeroStelle,
                                    commento,
                                    rispostaAutore,
                                    rispostaTesto
                            );

                    lista.add(recensione);

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Numero stelle non valido: "
                                    + riga
                    );
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Errore caricamento recensioni: "
                            + e.getMessage()
            );
        }

        return lista;
    }


    /**
     * Salva tutte le recensioni nel file.
     */
    private void save(List<Recensioni> lista) {

        try (BufferedWriter bw =
                     new BufferedWriter(
                             new FileWriter(file))) {

            for (Recensioni rec : lista) {

                bw.write(
                        sicuro(rec.getId()) + ";" +
                        sicuro(rec.getRistoranteid()) + ";" +
                        sicuro(rec.getAutore()) + ";" +
                        rec.getNumeroStelle() + ";" +
                        sicuro(rec.getCommento()) + ";" +
                        sicuro(rec.getrispostaAutore()) + ";" +
                        sicuro(rec.getRispostaTesto()) + ";" +
                        sicuro(rec.getRiristoratore())
                );

                bw.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Errore salvataggio recensioni: "
                            + e.getMessage()
            );
        }
    }


    /**
     * Restituisce tutte le recensioni.
     */
    public List<Recensioni> getTutte() {

        return load();
    }


    /**
     * Restituisce solamente le recensioni
     * appartenenti ad un determinato ristorante.
     */
    public List<Recensioni> getPerRistorante(
            String idRistorante) {

        List<Recensioni> tutte = load();

        List<Recensioni> risultato =
                new ArrayList<>();

        for (Recensioni rec : tutte) {

            if (rec.getRistoranteid()
                    .equals(idRistorante)) {

                risultato.add(rec);
            }
        }

        return risultato;
    }


    /**
     * Aggiunge una nuova recensione.
     */
    public void aggiungiRecensione(
            Recensioni recensione) {

        List<Recensioni> lista = load();

        lista.add(recensione);

        save(lista);
    }


    /**
     * Aggiorna una recensione esistente.
     */
    public void aggiornaRecensione(
            Recensioni recensioneAggiornata) {

        List<Recensioni> lista = load();

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i)
                    .getId()
                    .equals(recensioneAggiornata.getId())) {

                lista.set(i, recensioneAggiornata);
                break;
            }
        }

        save(lista);
    }


    /**
     * Elimina una recensione tramite il suo ID.
     */
    public void rimuoviRecensione(
            String idRecensione) {

        List<Recensioni> lista = load();

        lista.removeIf(
                rec -> rec.getId()
                        .equals(idRecensione)
        );

        save(lista);
    }


    /**
     * Elimina tutte le recensioni appartenenti
     * ad un determinato ristorante.
     */
    public void rimuoviPerRistorante(
            String idRistorante) {

        List<Recensioni> lista = load();

        lista.removeIf(
                rec -> rec.getRistoranteid()
                        .equals(idRistorante)
        );

        save(lista);
    }


    /**
     * Evita che ';' o caratteri di nuova riga
     * rompano la struttura del file.
     */
    private String sicuro(String testo) {

        if (testo == null) {
            return "";
        }

        return testo
                .replace(";", ",")
                .replace("\n", " ")
                .replace("\r", " ");
    }
}


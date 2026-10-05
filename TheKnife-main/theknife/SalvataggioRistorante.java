package theknife;

import java.io.*;
import java.util.*;

/**
 * Gestisce il caricamento e il salvataggio dei ristoranti
 * utilizzando il file CSV Ristorante.csv.
 *
 * Le recensioni NON vengono più salvate qui:
 * verranno gestite successivamente da SalvataggioRecensioni.
 */
public class SalvataggioRistorante {

    private String file =
            "C:\\Users\\Utente\\Desktop\\Esposito_760476\\TheKnife\\TheKnife-main\\data\\Ristorante.csv";

    /**
     * Carica tutti i ristoranti presenti nel CSV.
     */
    private List<Ristorante> load() {

        List<Ristorante> lista = new ArrayList<>();

        File csvFile = new File(file);

        if (!csvFile.exists()) {
            System.out.println("File Ristorante.csv non trovato.");
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String intestazione = br.readLine();

            if (intestazione == null) {
                return lista;
            }

            String riga;

            while ((riga = br.readLine()) != null) {

                if (riga.isBlank()) {
                    continue;
                }

                List<String> campi = parseCSVLine(riga);

                /*
                 * Struttura attuale:
                 *
                 * 0  Id
                 * 1  Name
                 * 2  Address
                 * 3  Location
                 * 4  Price
                 * 5  Cuisine
                 * 6  Longitude
                 * 7  Latitude
                 * 8  PhoneNumber
                 * 9  Url
                 * 10 WebsiteUrl
                 * 11 Award
                 * 12 GreenStar
                 * 13 FacilitiesAndServices
                 * 14 Description
                 */

                if (campi.size() < 8) {
                    continue;
                }

                try {

                    String id = campi.get(0);
                    String nome = campi.get(1);
                    String indirizzo = campi.get(2);
                    String location = campi.get(3);
                    String prezzoString = campi.get(4);
                    String tipoCucina = campi.get(5);

                    double longitudine =
                            Double.parseDouble(campi.get(6));

                    double latitudine =
                            Double.parseDouble(campi.get(7));

                    /*
                     * Location:
                     *
                     * "Vienna, Austria"
                     *
                     * diventa:
                     * citta = Vienna
                     * nazione = Austria
                     */
                    String[] partiLocation = location.split(",");

                    String citta = "";

                    if (partiLocation.length > 0) {
                        citta = partiLocation[0].trim();
                    }

                    String nazione = "";

                    if (partiLocation.length > 1) {
                        nazione = partiLocation[1].trim();
                    }

                    /*
                     * €€€€ -> 4
                     * $$$$ -> 4
                     * €€€ -> 3
                     */
                    double fasciaPrezzo =
                            prezzoString.length();

                    /*
                     * Questi dati non sono ancora presenti
                     * nella struttura attuale del CSV.
                     */
                    boolean delivery = false;
                    boolean prenotazioneOnline = false;
                    String usernameRistoratore = "";

                    Ristorante ristorante = new Ristorante(
                            id,
                            nome,
                            nazione,
                            citta,
                            indirizzo,
                            latitudine,
                            longitudine,
                            fasciaPrezzo,
                            delivery,
                            prenotazioneOnline,
                            tipoCucina,
                            usernameRistoratore
                    );

                    lista.add(ristorante);

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Riga CSV non valida, ignorata: " + riga
                    );
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Errore caricando Ristorante.csv: "
                            + e.getMessage()
            );
        }

        return lista;
    }

    /**
     * Salva la lista dei ristoranti nel CSV.
     *
     * Le colonne aggiuntive provenienti dal dataset originale
     * vengono mantenute.
     */
    private void save(List<Ristorante> lista) {

        /*
         * Prima leggiamo il CSV attuale in modo da non perdere
         * le informazioni extra presenti nelle colonne 8-14.
         */
        Map<String, List<String>> datiOriginali =
                leggiRigheOriginali();

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(file))) {

            /*
             * Manteniamo tutte le colonne originali.
             */
            bw.write(
                    "Id,Name,Address,Location,Price,Cuisine,"
                            + "Longitude,Latitude,PhoneNumber,Url,"
                            + "WebsiteUrl,Award,GreenStar,"
                            + "FacilitiesAndServices,Description"
            );

            bw.newLine();

            for (Ristorante r : lista) {

                List<String> campi =
                        datiOriginali.get(r.getId());

                /*
                 * Se il ristorante era già presente nel CSV,
                 * recuperiamo tutte le sue informazioni originali.
                 *
                 * Se è nuovo, creiamo le 15 colonne.
                 */
                if (campi == null) {

                    campi = new ArrayList<>();

                    for (int i = 0; i < 15; i++) {
                        campi.add("");
                    }
                }

                /*
                 * Aggiorniamo solamente i dati gestiti
                 * dalla classe Ristorante.
                 */

                campi.set(0, r.getId());
                campi.set(1, r.getnome());
                campi.set(2, r.getIndirizzo());

                String location =
                        r.getCitta();

                if (!r.getNazione().isBlank()) {
                    location += ", " + r.getNazione();
                }

                campi.set(3, location);

                campi.set(
                        4,
                        creaPrezzo(r.getFasciaPrezzo())
                );

                campi.set(5, r.getTipoCucina());

                campi.set(
                        6,
                        String.valueOf(r.getLongitudine())
                );

                campi.set(
                        7,
                        String.valueOf(r.getLatitudine())
                );

                /*
                 * Le colonne:
                 *
                 * 8  PhoneNumber
                 * 9  Url
                 * 10 WebsiteUrl
                 * 11 Award
                 * 12 GreenStar
                 * 13 FacilitiesAndServices
                 * 14 Description
                 *
                 * vengono lasciate invariate.
                 */

                bw.write(convertiInCSV(campi));
                bw.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Errore salvando Ristorante.csv: "
                            + e.getMessage()
            );
        }
    }

    /**
     * Legge le righe originali del CSV e le associa all'ID.
     * Serve per preservare le colonne che Ristorante.java
     * attualmente non gestisce.
     */
    private Map<String, List<String>> leggiRigheOriginali() {

        Map<String, List<String>> mappa =
                new HashMap<>();

        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {

            String intestazione = br.readLine();

            if (intestazione == null) {
                return mappa;
            }

            String riga;

            while ((riga = br.readLine()) != null) {

                if (riga.isBlank()) {
                    continue;
                }

                List<String> campi =
                        parseCSVLine(riga);

                if (campi.size() >= 8) {

                    String id = campi.get(0);

                    /*
                     * Ci assicuriamo che ci siano 15 colonne.
                     */
                    while (campi.size() < 15) {
                        campi.add("");
                    }

                    mappa.put(id, campi);
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Errore lettura dati originali: "
                            + e.getMessage()
            );
        }

        return mappa;
    }

    /**
     * Aggiunge un nuovo ristorante.
     */
    public void aggiungiRistorante(Ristorante r) {

        List<Ristorante> lista = load();

        lista.add(r);

        save(lista);
    }

    /**
     * Aggiorna un ristorante tramite il suo ID.
     */
    public void aggiornaRistorante(Ristorante aggiornato) {

        List<Ristorante> lista = load();

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i)
                    .getId()
                    .equals(aggiornato.getId())) {

                lista.set(i, aggiornato);
                break;
            }
        }

        save(lista);
    }

    /**
     * Metodo mantenuto temporaneamente per compatibilità
     * con il resto del progetto.
     *
     * Le recensioni verranno spostate successivamente
     * in Recensioni.txt.
     */
    public void aggiungiRecensione(
            String idRist,
            Recensioni rec) {

        List<Ristorante> lista = load();

        for (Ristorante r : lista) {

            if (r.getId().equals(idRist)) {

                r.addRecensione(rec);
                break;
            }
        }

        /*
         * Per ora aggiorniamo il ristorante.
         *
         * La persistenza definitiva delle recensioni
         * verrà gestita da SalvataggioRecensioni.
         */
        save(lista);
    }

    /**
     * Rimuove un ristorante tramite ID.
     */
    public void rimuoviRistorante(String id) {

        List<Ristorante> lista = load();

        lista.removeIf(
                r -> r.getId().equals(id)
        );

        save(lista);
    }

    /**
     * Restituisce tutti i ristoranti.
     */
    public List<Ristorante> getTutti() {

        return load();
    }

    /**
     * Converte una stringa prezzo numerica
     * nel formato utilizzato dal CSV.
     *
     * Esempio:
     * 4 -> €€€€
     * 3 -> €€€
     */
    private String creaPrezzo(double prezzo) {

        int valore = (int) Math.round(prezzo);

        if (valore < 1) {
            return "";
        }

        StringBuilder risultato =
                new StringBuilder();

        for (int i = 0; i < valore; i++) {
            risultato.append("€");
        }

        return risultato.toString();
    }

    /**
     * Parser CSV che gestisce le virgole
     * all'interno dei campi racchiusi tra virgolette.
     */
    private List<String> parseCSVLine(String riga) {

        List<String> campi =
                new ArrayList<>();

        StringBuilder campo =
                new StringBuilder();

        boolean dentroVirgolette = false;

        for (int i = 0; i < riga.length(); i++) {

            char carattere = riga.charAt(i);

            if (carattere == '"') {

                /*
                 * Gestione delle virgolette doppie.
                 */
                if (dentroVirgolette
                        && i + 1 < riga.length()
                        && riga.charAt(i + 1) == '"') {

                    campo.append('"');
                    i++;

                } else {

                    dentroVirgolette =
                            !dentroVirgolette;
                }

            } else if (
                    carattere == ','
                            && !dentroVirgolette) {

                campi.add(
                        campo.toString().trim()
                );

                campo.setLength(0);

            } else {

                campo.append(carattere);
            }
        }

        campi.add(
                campo.toString().trim()
        );

        return campi;
    }

    /**
     * Converte una lista di campi in una riga CSV.
     */
    private String convertiInCSV(
            List<String> campi) {

        StringBuilder riga =
                new StringBuilder();

        for (int i = 0; i < campi.size(); i++) {

            if (i > 0) {
                riga.append(",");
            }

            String campo = campi.get(i);

            if (campo == null) {
                campo = "";
            }

            /*
             * I campi vengono racchiusi tra virgolette
             * se contengono virgole, virgolette o newline.
             */
            if (campo.contains(",")
                    || campo.contains("\"")
                    || campo.contains("\n")
                    || campo.contains("\r")) {

                campo =
                        campo.replace("\"", "\"\"");

                riga.append("\"")
                        .append(campo)
                        .append("\"");

            } else {

                riga.append(campo);
            }
        }

        return riga.toString();
    }
}


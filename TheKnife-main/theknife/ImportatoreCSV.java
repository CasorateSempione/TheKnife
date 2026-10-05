
package theknife;

import java.io.*;
import java.util.*;

/**
 * Importa i ristoranti dal CSV e assegna un ID univoco
 * che viene salvato direttamente nel CSV.
 */
public class ImportatoreCSV {

    private String file = "C:\\Users\\Utente\\Desktop\\Esposito_760476\\TheKnife\\TheKnife-main\\data\\Ristorante.csv";

    /**
     * Importa tutti i ristoranti dal CSV.
     *
     * Se il CSV non contiene ancora la colonna Id,
     * viene creata automaticamente e viene assegnato
     * un UUID ad ogni ristorante.
     */
    public List<Ristorante> importa() {

        List<Ristorante> ristoranti = new ArrayList<>();

        try {

            File csvFile = new File(file);

            if (!csvFile.exists()) {
                System.out.println("File CSV non trovato: " + file);
                return ristoranti;
            }

            List<String> righe = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

                String riga;

                while ((riga = br.readLine()) != null) {
                    righe.add(riga);
                }
            }

            if (righe.isEmpty()) {
                System.out.println("Il CSV è vuoto.");
                return ristoranti;
            }

            // Leggiamo l'intestazione
            String intestazione = righe.get(0);

            List<String> colonne = parseCSVLine(intestazione);

            boolean haId = !colonne.isEmpty()
                    && colonne.get(0).equalsIgnoreCase("Id");

            /*
             * Se il CSV non ha ancora gli ID,
             * li generiamo una sola volta e riscriviamo il file.
             */
            if (!haId) {

                System.out.println("Il CSV non contiene gli ID.");
                System.out.println("Genero gli ID dei ristoranti...");

                List<String> nuoveRighe = new ArrayList<>();

                nuoveRighe.add(
                        "Id," + intestazione
                );

                for (int i = 1; i < righe.size(); i++) {

                    String riga = righe.get(i);

                    if (riga.isBlank()) {
                        continue;
                    }

                    String id = UUID.randomUUID().toString();

                    nuoveRighe.add(id + "," + riga);
                }

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {

                    for (String riga : nuoveRighe) {
                        bw.write(riga);
                        bw.newLine();
                    }
                }

                System.out.println("ID generati e salvati nel CSV.");

                // Aggiorniamo le righe utilizzate per l'importazione
                righe = nuoveRighe;
            }

            // Importazione vera e propria
            for (int i = 1; i < righe.size(); i++) {

                String riga = righe.get(i);

                if (riga.isBlank()) {
                    continue;
                }

                List<String> campi = parseCSVLine(riga);

                if (campi.size() < 8) {
                    continue;
                }

                /*
                 * Ora la prima colonna è l'ID.
                 *
                 * 0  = Id
                 * 1  = Name
                 * 2  = Address
                 * 3  = Location
                 * 4  = Price
                 * 5  = Cuisine
                 * 6  = Longitude
                 * 7  = Latitude
                 */

                String id = campi.get(0);
                String nome = campi.get(1);
                String indirizzo = campi.get(2);
                String location = campi.get(3);
                String fasciaPrezzoString = campi.get(4);
                String tipoCucina = campi.get(5);

                double longitudine = Double.parseDouble(campi.get(6));
                double latitudine = Double.parseDouble(campi.get(7));

                // Separiamo città e nazione
                String[] partiLocation = location.split(",");

                String citta = partiLocation[0].trim();

                String nazione = "";

                if (partiLocation.length > 1) {
                    nazione = partiLocation[1].trim();
                }

                /*
                 * €€€€ -> 4
                 * $$$$ -> 4
                 * €€€  -> 3
                 */
                double fasciaPrezzo = fasciaPrezzoString.length();

                /*
                 * Questi dati non sono presenti nel CSV
                 * oppure non sono gestiti attualmente dalla
                 * classe Ristorante.
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

                ristoranti.add(ristorante);
            }

        } catch (IOException e) {

            System.out.println(
                    "Errore lettura/scrittura CSV: " + e.getMessage()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Errore formato numerico nel CSV: " + e.getMessage()
            );
        }

        return ristoranti;
    }

    /**
     * Parser CSV che gestisce anche i campi racchiusi tra virgolette.
     */
    private List<String> parseCSVLine(String riga) {

        List<String> campi = new ArrayList<>();

        StringBuilder campo = new StringBuilder();

        boolean dentroVirgolette = false;

        for (int i = 0; i < riga.length(); i++) {

            char carattere = riga.charAt(i);

            if (carattere == '"') {

                dentroVirgolette = !dentroVirgolette;

            } else if (carattere == ',' && !dentroVirgolette) {

                campi.add(campo.toString().trim());

                campo.setLength(0);

            } else {

                campo.append(carattere);
            }
        }

        campi.add(campo.toString().trim());

        return campi;
    }

    /**
     * Test dell'importatore.
     */
    public static void main(String[] args) {

        ImportatoreCSV importatore = new ImportatoreCSV();

        List<Ristorante> ristoranti = importatore.importa();

        System.out.println(
                "Ristoranti caricati: " + ristoranti.size()
        );

        System.out.println();

        for (int i = 0; i < Math.min(5, ristoranti.size()); i++) {

            Ristorante r = ristoranti.get(i);

            System.out.println(
                    r.getId() + " | " +
                    r.getnome() + " | " +
                    r.getCitta() + " | " +
                    r.getNazione()
            );
        }
    }
}


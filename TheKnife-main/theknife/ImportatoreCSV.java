package theknife;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ImportatoreCSV {

   private String file = "data/Ristorante.csv";

    public List<Ristorante> importa() {

        List<Ristorante> ristoranti = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            // Salta la prima riga, cioè l'intestazione
            br.readLine();

            String riga;

            while ((riga = br.readLine()) != null) {

                List<String> campi = parseCSVLine(riga);

                // Il nostro CSV deve avere almeno 7 colonne utili
                if (campi.size() < 7) {
                    continue;
                }

                String id = UUID.randomUUID().toString();

                String nome = campi.get(0);
                String indirizzo = campi.get(1);
                String location = campi.get(2);
                String fasciaPrezzoString = campi.get(3);
                String tipoCucina = campi.get(4);
                double longitudine = Double.parseDouble(campi.get(5));
                double latitudine = Double.parseDouble(campi.get(6));

                // Location è del tipo "Vienna, Austria"
                String[] partiLocation = location.split(",");

                String citta = partiLocation[0].trim();
                String nazione = "";

                if (partiLocation.length > 1) {
                    nazione = partiLocation[1].trim();
                }

                // Trasformiamo €€€€ in 4
                double fasciaPrezzo = fasciaPrezzoString.length();

                // Per ora questi dati non sono presenti nel CSV
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
            System.out.println("Errore lettura CSV: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Errore formato numerico nel CSV: " + e.getMessage());
        }

        return ristoranti;
    }


    // Legge una riga CSV rispettando le virgole contenute tra virgolette
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

        // Aggiunge l'ultimo campo
        campi.add(campo.toString().trim());

        return campi;
    }

    public static void main(String[] args) {

    ImportatoreCSV importatore = new ImportatoreCSV();

    List<Ristorante> ristoranti = importatore.importa();

    System.out.println("Ristoranti caricati: " + ristoranti.size());

    for (int i = 0; i < Math.min(5, ristoranti.size()); i++) {

        Ristorante r = ristoranti.get(i);

        System.out.println(
                r.getnome() + " - " +
                r.getCitta() + " - " +
                r.getNazione()
        );
    }
}
}
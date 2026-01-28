package theknife;

import java.util.*;

/**
 * Classe di supporto che gestisce le operazioni sui ristoranti.
 * <p>Permette di cercarli, mostrarli tutti e filtrare in base a vari criteri.
 */
public class GestoreRistoranti {

    /**
     * Oggetto che si occupa di salvare e recuperare i ristoranti.
     */
    private static SalvataggioRistorante dao = new SalvataggioRistorante();

    /**
     * Mostra a schermo tutti i ristoranti salvati.
     * <p>Se non ce ne sono, avvisa l’utente.
     */
    public static void mostraTutti() {
        List<Ristorante> ristoranti = dao.getTutti();

        if (ristoranti.isEmpty()) {
            System.out.println("Nessun ristorante disponibile");
            return;
        }

        for (Ristorante r : ristoranti) {
            System.out.println(r);
        }
    }

    /**
     * Restituisce la lista completa dei ristoranti salvati.
     *
     * @return lista di ristoranti
     */
    public static List<Ristorante> getRistoranti() {
        return dao.getTutti();
    }

    /**
     * Cerca tutti i ristoranti che si trovano in una certa città.
     *
     * @param citta città da cercare
     * @return lista di ristoranti trovati nella città indicata
     */
    public static List<Ristorante> cercaPerCitta(String citta) {
        List<Ristorante> risultato = new ArrayList<>();

        for (Ristorante r : dao.getTutti()) {
            if (r.getCitta().equalsIgnoreCase(citta)) {
                risultato.add(r);
            }
        }
        return risultato;
    }

    /**
     * Cerca i ristoranti in base al tipo di cucina.
     *
     * @param tipo tipo di cucina (es. italiana, sushi, messicana)
     * @return lista dei ristoranti che offrono quel tipo di cucina
     */
    public static List<Ristorante> cercaPerTipoCucina(String tipo) {
        List<Ristorante> risultato = new ArrayList<>();

        for (Ristorante r : dao.getTutti()) {
            if (r.getTipoCucina().equalsIgnoreCase(tipo)) {
                risultato.add(r);
            }
        }
        return risultato;
    }

    /**
     * Cerca i ristoranti il cui nome contiene il testo inserito.
     * <p>La ricerca non è case-sensitive.
     *
     * @param testo parte del nome da cercare
     * @return lista dei ristoranti che corrispondono alla ricerca
     */
    public static List<Ristorante> cercaPerNome(String testo) {
        List<Ristorante> risultato = new ArrayList<>();
        String query = testo.toLowerCase();

        for (Ristorante r : dao.getTutti()) {
            if (r.getnome().toLowerCase().contains(query)) {
                risultato.add(r);
            }
        }
        return risultato;
    }

    /**
     * Cerca i ristoranti che hanno una fascia di prezzo
     * minore o uguale al valore indicato.
     *
     * @param max prezzo massimo accettato
     * @return lista dei ristoranti che rientrano nella fascia
     */
    public static List<Ristorante> cercaperFasciaPrezzo(double max) {
        List<Ristorante> risultato = new ArrayList<>();

        for (Ristorante r : dao.getTutti()) {
            if (r.getFasciaPrezzo() <= max) {
                risultato.add(r);
            }
        }
        return risultato;
    }
}
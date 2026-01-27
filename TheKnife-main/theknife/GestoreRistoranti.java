package theknife;

import java.util.*;
public class GestoreRistoranti {

    private static SalvataggioRistorante dao = new SalvataggioRistorante();

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

    public static List<Ristorante> getRistoranti() {
        return dao.getTutti();
    }

    public static List<Ristorante> cercaPerCitta(String citta) {
        List<Ristorante> risultato = new ArrayList<>();

        for (Ristorante r : dao.getTutti()) {
            if (r.getCitta().equalsIgnoreCase(citta)) {
                risultato.add(r);
            }
        }
        return risultato;
    }

    public static List<Ristorante> cercaPerTipoCucina(String tipo) {
        List<Ristorante> risultato = new ArrayList<>();

        for (Ristorante r : dao.getTutti()) {
            if (r.getTipoCucina().equalsIgnoreCase(tipo)) {
                risultato.add(r);
            }
        }
        return risultato;
    }
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

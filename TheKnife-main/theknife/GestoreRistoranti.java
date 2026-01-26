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
}
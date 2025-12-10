package theknife;

import java.util.ArrayList;
import java.util.List;

public class Ristoratore extends Utente {


    private final List<Ristorante> ristorantiGestiti = new ArrayList<>();

    public Ristoratore(String nome, String cognome, String mail, String username,
                       String password, String domicilio, String ruolo) {
        super(nome, cognome, mail, username, password, domicilio, ruolo);
    }

    public List<Ristorante> getRistorantiGestiti() {
        return ristorantiGestiti;
    }

    public boolean aggiungiRistorante(Ristorante r) {
        if (r == null) {
            System.out.println("Errore: il ristorante non può essere nullo.");
            return false;
        }

        for (Ristorante esistente : ristorantiGestiti) {
            if (esistente.getnome().equalsIgnoreCase(r.getnome()) &&
                esistente.getCitta().equalsIgnoreCase(r.getCitta())) {

                System.out.println("Il ristorante '" + r.getnome() +
                                   "' a " + r.getCitta() + " è già presente.");
                return false;
            }
        }

        ristorantiGestiti.add(r);
        System.out.println("Ristorante '" + r.getnome() + "' aggiunto con successo!");
        return true;
    }

    public boolean rimuoviRistorante(Ristorante r) {
        if (r == null) {
            System.out.println("Errore: il ristorante non può essere nullo.");
            return false;

        }

        if (!ristorantiGestiti.contains(r)) {
            System.out.println("Il ristorante '" + r.getnome() +
                               "' non è presente nella lista.");
            return false;
        }

        ristorantiGestiti.remove(r);
        System.out.println("Ristorante '" + r.getnome() + "' rimosso con successo!");
        return true;
    }

    public String riepilogoRecensioni() {
        StringBuilder sb = new StringBuilder();

        for (Ristorante r : ristorantiGestiti) {
            double media = r.calcolaValutazioneMedia();
            int count = r.getRecensioni().size();

            sb.append(String.format(
                    "Ristorante: %s | Recensioni: %d | Media stelle: %.2f%n",
                    r.getnome(), count, media
            ));
        }

        return sb.toString();
    }

    public String visualizzaRecensioniDettaglio(Ristorante r) {
        if (r == null) return "Errore: ristorante nullo.";

        StringBuilder sb = new StringBuilder("Recensioni per: " + r.getnome() + "\n");
        int idx = 1;

        for (Recensioni rec : r.getRecensioni()) {

            String risposta = (rec.getrispostaAutore() == null ||
                               rec.getRispostaTesto() == null ||
                               rec.getRispostaTesto().isBlank())
                    ? "(nessuna risposta)"
                    : rec.getrispostaAutore() + ": " + rec.getRispostaTesto();

            sb.append(String.format(
                    "%d) Stelle: %d | Testo: %s | Risposta: %s%n",
                    idx++, rec.getNumeroStelle(), rec.getCommento(), risposta
            ));
        }

        return sb.toString();
    }

    public boolean rispondiARecensione(Ristorante r, Recensioni rec, String testoRisposta) {
        if (r == null || rec == null || testoRisposta == null) return false;

        if (!r.getRecensioni().contains(rec)) return false;

        if (rec.getRispostaTesto() != null && !rec.getRispostaTesto().isBlank())
            return false;

        // Aggiunge la risposta alla recensione
        rec.rispostaAutore= getNome();
        rec.rispostaTesto = testoRisposta.trim();

        return true;
    }
}

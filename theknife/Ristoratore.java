package theknife;

import java.util.ArrayList;
import java.util.List;


public class Ristoratore extends Utente  {
    private final List<Ristorante> ristorantigestiti = new ArrayList<>();
    
    public Ristoratore(String nome, String cognome, String mail, String username, String password, String domicilio, String ruolo) {
        super(nome, cognome, mail, username, password,domicilio,ruolo );
    }

public boolean aggiungiRistorante(Ristorante r) {
    if (r == null) {
        System.out.println("Errore: il ristorante non può essere nullo.");
        return false;
    }
    
    for (Ristorante esistente : ristorantigestiti) {
        if (esistente.getNome().equalsIgnoreCase(r.getNome()) &&
            esistente.getCitta().equalsIgnoreCase(r.getCitta())) {
            System.out.println("Il ristorante '" + r.getNome() + "' a " + r.getCitta() + " è già presente.");
            return false;
        }
    }
    ristorantigestiti.add(r);
    System.out.println("Ristorante '" + r.getNome() + "' aggiunto con successo!");
    return true;
}
public boolean rimuoviRistorante(Ristorante r) {
    if (r == null) {
        System.out.println("Errore: il ristorante non può essere nullo.");
        return false;
    }
    if (!ristorantigestiti.contains(r)) {
        System.out.println("Il ristorante '" + r.getNome() + "' non è presente nella lista.");
        return false;
    }
    ristorantigestiti.remove(r);
    System.out.println("Ristorante '" + r.getNome() + "' rimosso con successo!");
    return true;
}

public String riepilogoRecensioni() {
    StringBuilder sb = new StringBuilder();
    for (Ristorante r : ristorantigestiti) {
        double media = r.calcolaValutazioneMedia();
        int count = r.getRecensioni().size();
        sb.append(String.format(
            "Ristorante: %s | Recensioni: %d | Media stelle: %.2f%n",
            r.getNome(), count, media
        ));
    }
    return sb.toString();
}

public String visualizzaRecensioniDettaglio(Ristorante r) {
    if (r == null) return "Errore: ristorante nullo.";
    StringBuilder sb = new StringBuilder("Recensioni per: " + r.getnome() + "\n");
    int idx = 1;
    for (Recensioni rec : r.getRecensioni()) {
        String risposta = (rec.getRispostaRistoratore() == null || rec.getRispostaRistoratore().isBlank())
                ? "(nessuna risposta)"
                : rec.getRispostaRistoratore();
        sb.append(String.format(
            "%d) Stelle: %d | Testo: %s | Risposta: %s%n",
            idx++, rec.getNumeroStelle(), rec.getCommento(), risposta
        ));
    }
    return sb.toString();

}

public boolean rispondiARecensione(Ristorante r, Recensioni recensione, String testoRisposta) {
   if (r == null || recensione == null || testoRisposta == null) return false;

   if (!r.getRecensioni().contains(recensione)) return false;

   if (recensione.getRispostaRistoratore() != null && !recensione.getRispostaRistoratore().isBlank()) return false;
   
   recensione.rispondi(testoRisposta.trim()); // trim toglie spazi inutili 
   return true;

}

   
}



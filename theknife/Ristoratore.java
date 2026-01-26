package theknife;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe <code>Ristoratore</code> rappresenta un utente registrato che gestisce
 * uno o più ristoranti all'interno dell'applicazione TheKnife.
 * <p>
 * Un ristoratore può aggiungere o rimuovere ristoranti dal proprio elenco,
 * visualizzare le recensioni ricevute e rispondere ai clienti.
 * </p>
 *
 * <p>
 * La classe estende <code>Utente</code>, ereditando tutte le informazioni
 * anagrafiche e di autenticazione.
 * </p>
 *
 * @author CADDU
 * @version 1.0
 */
public class Ristoratore extends Utente {

    /** Lista dei ristoranti gestiti dal ristoratore. */
    private final List<Ristorante> ristorantigestiti = new ArrayList<>();

    /**
     * Costruisce un nuovo oggetto <code>Ristoratore</code> inizializzando
     * i campi ereditati dalla classe <code>Utente</code>.
     *
     * @param nome      nome del ristoratore
     * @param cognome   cognome del ristoratore
     * @param mail      indirizzo email
     * @param username  nome utente scelto
     * @param password  password dell'account
     * @param domicilio domicilio del ristoratore
     * @param ruolo     ruolo dell'utente (tipicamente "ristoratore")
     */
    public Ristoratore(String nome, String cognome, String mail, String username,
                       String password, String domicilio, String ruolo) {
        super(nome, cognome, mail, username, password, domicilio, ruolo);
    }

    /**
     * Aggiunge un ristorante alla lista dei ristoranti gestiti.
     * <p>
     * L'aggiunta fallisce se:
     * </p>
     * <ul>
     *     <li>il ristorante è nullo</li>
     *     <li>un ristorante con lo stesso nome e città è già presente</li>
     * </ul>
     *
     * @param r il ristorante da aggiungere
     * @return <code>true</code> se l'aggiunta è avvenuta con successo,
     *         <code>false</code> altrimenti
     */
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

    /**
     * Rimuove un ristorante dalla lista dei ristoranti gestiti.
     *
     * @param r il ristorante da rimuovere
     * @return <code>true</code> se la rimozione è avvenuta con successo,
     *         <code>false</code> se il ristorante è nullo o non presente nella lista
     */
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

    /**
     * Restituisce un riepilogo sintetico delle recensioni dei ristoranti gestiti.
     * <p>
     * Per ogni ristorante vengono riportati:
     * </p>
     * <ul>
     *     <li>numero totale di recensioni</li>
     *     <li>media delle stelle</li>
     * </ul>
     *
     * @return una stringa contenente il riepilogo delle recensioni
     */
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

    /**
     * Restituisce una descrizione dettagliata delle recensioni di un ristorante.
     *
     * @param r il ristorante di cui visualizzare le recensioni
     * @return una stringa contenente tutte le recensioni e le eventuali risposte
     */
    public String visualizzaRecensioniDettaglio(Ristorante r) {
        if (r == null) return "Errore: ristorante nullo.";

        StringBuilder sb = new StringBuilder("Recensioni per: " + r.getNome() + "\n");
        int idx = 1;

        for (Recensioni rec : r.getRecensioni()) {
            String risposta = (rec.getRisposta() == null)
                    ? "(nessuna risposta)"
                    : rec.getRisposta().getTesto();

            sb.append(String.format(
                "%d) Stelle: %d | Testo: %s | Risposta: %s%n",
                idx++, rec.getNumeroStelle(), rec.getCommento(), risposta
            ));
        }

        return sb.toString();
    }

    /**
     * Permette al ristoratore di rispondere a una recensione.
     * <p>
     * La risposta viene accettata solo se:
     * </p>
     * <ul>
     *     <li>il ristorante e la recensione non sono nulli</li>
     *     <li>la recensione appartiene al ristorante</li>
     *     <li>la recensione non ha già una risposta</li>
     * </ul>
     *
     * @param r il ristorante a cui appartiene la recensione
     * @param recensione la recensione a cui rispondere
     * @param testoRisposta il testo della risposta
     * @return <code>true</code> se la risposta è stata registrata,
     *         <code>false</code> altrimenti
     */
    public boolean rispondiARecensione(Ristorante r, Recensioni recensione, String testoRisposta) {
        if (r == null || recensione == null || testoRisposta == null) return false;

        if (!r.getRecensioni().contains(recensione)) return false;

        if (recensione.getRisposta() != null) return false;

        recensione.setRisposta(new Risposta("Ristoratore", testoRisposta.trim()));
        return true;
    }
}


package theknife;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Rappresenta un ristoratore nell'app.
 * <p>Un ristoratore può gestire uno o più ristoranti,
 * vedere le recensioni e rispondere ai clienti.
 */
public class Ristoratore extends Utente {

    /**
     * Lista dei ristoranti gestiti da questo ristoratore.
     */
    private final List<Ristorante> ristorantiGestiti = new ArrayList<>();

    /**
     * Crea un nuovo ristoratore con i suoi dati principali.
     *
     * @param nome nome del ristoratore
     * @param cognome cognome del ristoratore
     * @param mail email del ristoratore
     * @param username username scelto
     * @param password password (già eventualmente cifrata)
     * @param domicilio domicilio del ristoratore
     * @param ruolo ruolo dell’utente (es. "Ristoratore")
     */
    public Ristoratore(String nome, String cognome, String domicilio, String mail,
                       String password, String username, String ruolo) {
        super(nome,cognome,domicilio,mail,password,username,ruolo);
    }

    /**
     * Restituisce la lista dei ristoranti gestiti.
     *
     * @return lista di ristoranti gestiti
     */
    public List<Ristorante> getRistorantiGestiti() {
        return ristorantiGestiti;
    }

    /**
     * Aggiunge un ristorante alla lista di quelli gestiti,
     * controllando che non sia già presente nella stessa città con lo stesso nome.
     *
     * @param r ristorante da aggiungere
     * @return true se il ristorante è stato aggiunto, false altrimenti
     */
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

    /**
     * Rimuove un ristorante dalla lista di quelli gestiti.
     *
     * @param r ristorante da rimuovere
     * @return true se il ristorante è stato rimosso, false altrimenti
     */
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

    /**
     * Restituisce un riepilogo delle recensioni per tutti i ristoranti gestiti.
     * <p>Per ogni ristorante mostra quante recensioni ha e la media delle stelle.
     *
     * @return stringa con il riepilogo delle recensioni
     */
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

    /**
     * Restituisce il dettaglio delle recensioni di un singolo ristorante,
     * con eventuale risposta del ristoratore.
     *
     * @param r ristorante di cui mostrare le recensioni
     * @return stringa con l’elenco delle recensioni e le risposte
     */
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

    /**
     * Permette al ristoratore di rispondere a una recensione,
     * solo se non è già presente una risposta.
     *
     * @param r ristorante a cui appartiene la recensione
     * @param rec recensione a cui rispondere
     * @param testoRisposta testo della risposta
     * @return true se la risposta è stata aggiunta, false altrimenti
     */
    public boolean rispondiARecensione(Ristorante r, Recensioni rec, String testoRisposta) {
        if (r == null || rec == null || testoRisposta == null) return false;

        if (!r.getRecensioni().contains(rec)) return false;

        if (rec.getRispostaTesto() != null && !rec.getRispostaTesto().isBlank())
            return false;

        rec.rispostaAutore = getNome();
        rec.rispostaTesto = testoRisposta.trim();

        return true;
    }

    /**
     * Gestisce la registrazione di un nuovo ristoratore chiedendo i dati da input.
     * <p>La password viene cifrata prima di creare l’oggetto.
     *
     * @return un nuovo oggetto Ristoratore con i dati inseriti
     */
    public static Ristoratore registrazioneRistoratore() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Inserire nome");
        String nome = sc.nextLine();

        System.out.println("Inserire cognome");
        String cognome = sc.nextLine();

        System.out.println("Inserire domicilio ");
        String domicilio = sc.nextLine();

        System.out.println("Inserire username");
        String username = sc.nextLine();

        System.out.println("Inserire mail");
        String mail = sc.nextLine();

        String ruolo = "Ristoratore";
        String hash = null;

        System.out.println("Inserire Password");
        String Password = sc.nextLine();
        try {
            hash = PasswordCriptata.hash(Password);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Errore");
        }

        return new Ristoratore(nome, cognome, domicilio, mail, hash, username, ruolo);
    }
}

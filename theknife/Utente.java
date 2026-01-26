package theknife;

import java.util.List;

/**
 * La classe astratta <code>Utente</code> rappresenta un generico utente
 * dell'applicazione TheKnife. Contiene le informazioni anagrafiche e
 * di autenticazione comuni a tutte le tipologie di utenti
 * (clienti e ristoratori).
 *
 * <p>
 * Le sottoclassi specializzano il comportamento aggiungendo funzionalità
 * specifiche per il ruolo ricoperto.
 * </p>
 */
public abstract class Utente {

    /** Lista degli utenti (non utilizzata direttamente in questa classe). */
    private List<Utente> utenti;

    /** Nome dell'utente. */
    private String nome;

    /** Cognome dell'utente. */
    private String cognome;

    /** Domicilio dell'utente. */
    private String domicilio;

    /** Username scelto dall'utente. */
    private String username;

    /** Indirizzo email dell'utente. */
    private String mail;

    /** Password dell'utente. */
    private String password;

    /** Ruolo dell'utente (cliente o ristoratore). */
    private String ruolo;

    /**
     * Costruisce un nuovo oggetto <code>Utente</code> inizializzando
     * tutte le informazioni principali.
     *
     * @param ruolo     ruolo dell'utente
     * @param nome      nome dell'utente
     * @param cognome   cognome dell'utente
     * @param mail      indirizzo email
     * @param password  password dell'utente
     * @param domicilio domicilio dell'utente
     * @param username  username scelto
     */
    public Utente(String ruolo, String nome, String cognome, String mail,
                  String password, String domicilio, String username) {

        this.nome = nome;
        this.cognome = cognome;
        this.domicilio = domicilio;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.ruolo = ruolo;
    }

    /** @return il nome dell'utente */
    public String getNome() {
        return nome;
    }

    /** @return il cognome dell'utente */
    public String getcognome() {
        return cognome;
    }

    /** @return il domicilio dell'utente */
    public String getdomicilio() {
        return domicilio;
    }

    /** @return lo username dell'utente */
    public String getusername() {
        return username;
    }

    /** @return l'indirizzo email dell'utente */
    public String getmail() {
        return mail;
    }

    /** @return la password dell'utente */
    public String getpassword() {
        return password;
    }

    /** @return il ruolo dell'utente */
    public String getruolo() {
        return ruolo;
    }
}
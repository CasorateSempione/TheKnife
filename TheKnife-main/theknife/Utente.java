package theknife;

import java.util.List;

/**
 * Classe astratta che rappresenta un utente dell’app.
 * <p>Contiene tutte le informazioni base che servono sia ai clienti
 * che ai ristoratori (nome, mail, username, ruolo ecc.).
 */
public abstract class Utente {

    /**
     * Lista di utenti (può essere usata per gestioni interne).
     */
    private List<Utente> utenti;

    /**
     * Nome dell’utente.
     */
    private String nome;

    /**
     * Cognome dell’utente.
     */
    private String cognome;

    /**
     * Domicilio dell’utente.
     */
    private String domicilio;

    /**
     * Username scelto dall’utente.
     */
    private String username;

    /**
     * Email dell’utente.
     */
    private String mail;

    /**
     * Password dell’utente (di solito già cifrata).
     */
    private String password;

    /**
     * Ruolo dell’utente (es. Cliente, Ristoratore).
     */
    private String ruolo;

    /**
     * Costruttore base per creare un utente con tutti i suoi dati.
     *
     * @param nome nome dell’utente
     * @param cognome cognome dell’utente
     * @param domicilio domicilio dell’utente
     * @param mail email dell’utente
     * @param password password dell’utente
     * @param username username scelto
     * @param ruolo ruolo dell’utente
     */
    public Utente(String nome, String cognome, String domicilio, String mail,
                  String password, String username, String ruolo) {

        this.nome = nome;
        this.cognome = cognome;
        this.domicilio = domicilio;
        this.mail = mail;
        this.password = password;
        this.username = username;
        this.ruolo = ruolo;
    }

    /** @return il nome dell’utente */
    public String getNome() {
        return nome;
    }

    /** @return il cognome dell’utente */
    public String getcognome() {
        return cognome;
    }

    /** @return il domicilio dell’utente */
    public String getdomicilio() {
        return domicilio;
    }

    /** @return la mail dell’utente */
    public String getmail() {
        return mail;
    }

    /** @return la password dell’utente */
    public String getpassword() {
        return password;
    }

    /** @return lo username dell’utente */
    public String getusername() {
        return username;
    }

    /** @return il ruolo dell’utente */
    public String getruolo() {
        return ruolo;
    }

    /**
     * Restituisce una rappresentazione dell’utente in formato riga di file.
     * <p>Utile per salvare i dati su file in modo semplice.
     *
     * @return stringa con tutti i campi separati da “;”
     */
    public String toFileString() {
        return getClass().getSimpleName() + ";" +
                nome + ";" +
                cognome + ";" +
                domicilio + ";" +
                mail + ";" +
                password + ";" +
                username + ";" +
                ruolo;
    }
}
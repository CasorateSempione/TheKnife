package theknife;

/**
 * La classe <code>Cliente</code> rappresenta un utente dell'applicazione
 * TheKnife che utilizza il sistema per cercare ristoranti e lasciare
 * recensioni.
 *
 * <p>
 * Estende la classe astratta <code>Utente</code>, ereditando tutte le
 * informazioni anagrafiche e di autenticazione.
 * </p>
 *
 * <p>
 * Il cliente non possiede funzionalità aggiuntive rispetto a quelle
 * fornite dalla classe base, ma viene distinto tramite il ruolo.
 * </p>
 *
 * @author CADDU
 * @version 1.0
 */
public class Cliente extends Utente {

    /**
     * Costruisce un nuovo oggetto <code>Cliente</code> inizializzando
     * tutte le informazioni dell'utente.
     *
     * @param nome      nome del cliente
     * @param cognome   cognome del cliente
     * @param mail      indirizzo email
     * @param username  username scelto
     * @param password  password dell'account
     * @param domicilio domicilio del cliente
     * @param ruolo     ruolo dell'utente (tipicamente "cliente")
     */
    public Cliente(String nome, String cognome, String mail,
                   String username, String password, String domicilio, String ruolo) {

        super(ruolo, nome, cognome, mail, password, domicilio, username);
    }
}
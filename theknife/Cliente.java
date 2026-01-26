package theknife;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe <code>Cliente</code> rappresenta un utente registrato che utilizza
 * l'applicazione come fruitore dei servizi offerti dal sistema TheKnife.
 * <p>
 * Un oggetto di questa classe eredita tutte le informazioni anagrafiche e di
 * autenticazione dalla classe <code>Utente</code> e aggiunge la gestione di una
 * lista di ristoranti preferiti, che permette al cliente di salvare e
 * consultare rapidamente i locali di proprio interesse.
 * </p>
 *
 * <p>
 * Questa classe fa parte del dominio applicativo e non contiene logica di
 * persistenza o gestione dei file, in accordo con il principio di separazione
 * delle responsabilità.
 * </p>
 *
 * @author CADDU
 * @version 1.0
 */
public class Cliente extends Utente {

    /**
     * Lista dei ristoranti preferiti associati al cliente.
     * <p>
     * La lista è implementata tramite <code>ArrayList</code> e inizialmente è vuota.
     * Può essere modificata tramite i metodi getter e setter.
     * </p>
     */
    private List<Ristorante> ristorantiPreferiti = new ArrayList<>();

    /**
     * Costruisce un nuovo oggetto <code>Cliente</code> inizializzando tutti i campi
     * ereditati dalla classe <code>Utente</code>.
     *
     * @param nome      il nome del cliente
     * @param cognome   il cognome del cliente
     * @param domicilio il domicilio del cliente
     * @param mail      l'indirizzo email del cliente
     * @param password  la password del cliente
     * @param username  il nome utente scelto dal cliente
     * @param ruolo     il ruolo dell'utente (tipicamente "cliente")
     */
    public Cliente(String nome, String cognome, String domicilio, String mail,
                   String password, String username, String ruolo) {
        super(nome, cognome, domicilio, mail, password, username, ruolo);
    }

    /**
     * Restituisce la lista dei ristoranti preferiti del cliente.
     *
     * @return una lista contenente i ristoranti preferiti
     */
    public List<Ristorante> ristorantePreferiti() {
        return ristorantiPreferiti;
    }

    /**
     * Imposta la lista dei ristoranti preferiti del cliente.
     * <p>
     * Questo metodo permette di sostituire completamente la lista esistente con
     * una nuova lista fornita come parametro.
     * </p>
     *
     * @param ristorantiPreferiti la nuova lista di ristoranti preferiti
     */
    public void setRistorantiPreferiti(List<Ristorante> ristorantiPreferiti) {
        this.ristorantiPreferiti = ristorantiPreferiti;
    }
}

package theknife;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Rappresenta un cliente dell’app.
 * <p>Un cliente può salvare i suoi ristoranti preferiti
 * e registrarsi inserendo i propri dati.
 */
public class Cliente extends Utente {

    /**
     * Lista dei ristoranti che il cliente ha aggiunto ai preferiti.
     */
    private List<Ristorante> ristorantiPreferiti = new ArrayList<>();

    /**
     * Crea un nuovo cliente con i suoi dati principali.
     *
     * @param nome nome del cliente
     * @param cognome cognome del cliente
     * @param domicilio domicilio del cliente
     * @param mail email del cliente
     * @param Password password (già cifrata)
     * @param username username scelto
     * @param ruolo ruolo dell’utente (es. "Cliente")
     */
    public Cliente(String nome, String cognome, String domicilio, String mail,
                   String Password, String username, String ruolo) {
        super(nome, cognome, domicilio, mail, Password, username, ruolo);
    }

    /**
     * Restituisce la lista dei ristoranti preferiti del cliente.
     *
     * @return lista dei ristoranti preferiti
     */
    public List<Ristorante> ristorantePreferiti() {
        return ristorantiPreferiti;
    }

    /**
     * Imposta una nuova lista di ristoranti preferiti.
     *
     * @param ristorantiPreferiti lista aggiornata dei preferiti
     */
    public void setRistorantiPreferiti(List<Ristorante> ristorantiPreferiti) {
        this.ristorantiPreferiti = ristorantiPreferiti;
    }

    /**
     * Gestisce la registrazione di un nuovo cliente chiedendo i dati da input.
     * <p>La password viene cifrata prima di creare l’oggetto Cliente.
     *
     * @return un nuovo Cliente con i dati inseriti
     */
    public static Cliente registrazionecliente() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Inserire nome");
        String nome = sc.nextLine();

        System.out.println("Inserire cognome");
        String cognome = sc.nextLine();

        System.out.println("Inserire domicilio");
        String domicilio = sc.nextLine();

        System.out.println("Inserire username");
        String username = sc.nextLine();

        System.out.println("Inserire mail");
        String mail = sc.nextLine();

        String ruolo = "Cliente";
        String hash1 = null;

        System.out.println("Inserire Password");
        String Password = sc.nextLine();

        try {
            hash1 = PasswordCriptata.hash(Password);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Errore");
        }

        return new Cliente(nome, cognome, domicilio, mail, hash1, username, ruolo);
    }
}

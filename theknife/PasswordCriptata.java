package theknife;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * La classe <code>PasswordCriptata</code> fornisce un metodo di utilità per
 * generare l'hash di una password utilizzando l'algoritmo crittografico
 * <strong>SHA-256</strong>.
 * <p>
 * La classe è dichiarata <code>final</code> poiché non è destinata ad essere
 * estesa. Tutte le funzionalità sono offerte tramite metodi statici, in quanto
 * non è necessario creare istanze della classe.
 * </p>
 *
 * <p>
 * L'hash risultante viene restituito in formato Base64, così da ottenere una
 * rappresentazione compatta e facilmente memorizzabile nei file di salvataggio
 * dell'applicazione.
 * </p>
 *
 * @author CADDU
 * @version 1.0
 */
public final class PasswordCriptata {

    /**
     * Genera l'hash della password fornita utilizzando l'algoritmo
     * <strong>SHA-256</strong>.
     * <p>
     * L'array di byte risultante dall'operazione di hashing viene convertito in
     * una stringa codificata in Base64, così da ottenere una rappresentazione
     * leggibile e facilmente memorizzabile.
     * </p>
     *
     * @param psw la password in chiaro da convertire in hash
     * @return una stringa contenente l'hash della password in formato Base64
     *
     * @throws NoSuchAlgorithmException se l'algoritmo SHA-256 non è supportato
     *                                  dall'ambiente di esecuzione
     */
    public static String hash(String psw) throws NoSuchAlgorithmException {

        // Creazione dell'istanza di MessageDigest per SHA-256
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Calcolo dell'hash della password
        byte[] hash = md.digest(psw.getBytes());

        // Conversione dell'hash in Base64 per una rappresentazione leggibile
        return Base64.getEncoder().encodeToString(hash);
    }
}
package theknife;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Classe di utilità per cifrare una password.
 * <p>Usa l’algoritmo SHA‑256 e restituisce l’hash in formato Base64,
 * così è più facile da salvare e leggere.
 */
public final class PasswordCriptata {

    /**
     * Genera l’hash della password usando SHA‑256.
     *
     * @param psw la password in chiaro
     * @return l’hash della password in formato Base64
     * @throws NoSuchAlgorithmException se l’algoritmo SHA‑256 non è disponibile
     */
    public static String hash(String psw) throws NoSuchAlgorithmException {

        // crea l'hash SHA‑256 della password
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(psw.getBytes());

        // converte l'hash in Base64 per renderlo leggibile e salvabile
        return Base64.getEncoder().encodeToString(hash);
    }
}
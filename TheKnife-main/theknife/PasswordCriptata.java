package theknife;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
     */
    public static String hash(String psw) throws NoSuchAlgorithmException {
    try {
        // crea l'hash SHA‑256 della password
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(psw.getBytes());


        StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException("Errore hash password", e);
        }
    }
}
    
package theknife;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;    

public final class PasswordCriptata {
    public static String hash(String psw)throws NoSuchAlgorithmException{
    //usando SHA-256 per creare l'hash della password
        MessageDigest md=MessageDigest.getInstance("SHA-256");
        byte[] hash=md.digest(psw.getBytes());
    //restitutire l'hash in formato Base64 per una rappresentazione leggibile

        return Base64.getEncoder().encodeToString(hash);                         
    }
}
package theknife;
import java.util.ArrayList;
import java.util.List;





public class Cliente extends Utente{
   private  List<Ristorante> ristorantiPreferiti = new ArrayList<>(); 
    public Cliente(String nome, String cognome, String domicilio, String mail,
                     String password, String username, String ruolo) {
         super( nome, cognome, domicilio, mail, password, username, ruolo);
    }       
public List ristorantePreferiti(){
    return ristorantiPreferiti;

}

}


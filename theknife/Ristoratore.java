package theknife;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ristoratore extends Utente  {
    private final List<Ristorante> ristorantigestiti = new ArrayList<>();
    
    public Ristoratore(String nome, String cognome, String mail, String username, String password, String domicilio, String ruolo) {
    super(nome, cognome, mail, username, password,domicilio,ruolo );

    public void aggiungiRistorante(Ristorante r) {
    Objects.requireNonNull(r, "Ristorante non può essere null");
    ristorantiGestiti.add(r);
}

   
}

}

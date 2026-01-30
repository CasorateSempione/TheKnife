package theknife;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TheKnife {
    private static Scanner sc=new Scanner(System.in);
    private static List<Utente> utenti=new ArrayList<>();
    private static SalvataggioUtente fileutenti = new SalvataggioUtente();
    public static void main(String[] args) {
        utenti = fileutenti.getTutti();
        String scelta = "";
        boolean running = true;

        while (running) {
            System.out.println("\nBenvenuto in The Knife!");
            System.out.println("1) Login");
            System.out.println("2) Registrazione");
            System.out.println("3) Entra come Guest");
            System.out.println("0) Esci");
            System.out.print("Scelta: ");

            scelta = sc.nextLine();
            switch (scelta) {
                case "1":
                    login();
                    break;

                case "2":
                Scanner sc = new Scanner (System.in);
                SalvataggioUtente salvataggio=new SalvataggioUtente();
                System.out.println("1) Registrazione Ristoratore");
                System.out.println("2)Registrazione Cliente");
                int a=sc.nextInt(); 
                if(a==1) {
            Ristoratore S1=Ristoratore.registrazioneRistoratore();
      salvataggio.aggiungiUtente(S1);  } 
      else if (a==2) {
       Cliente S2= Cliente.registrazionecliente();
                  salvataggio.aggiungiUtente(S2);
                } else {
                    System.out.println("Errore: Valore non valido");
                }
                    break;

                case "3":
                   usaComeGuest();
                    break;

                case "0":
                    System.out.println("\nChiusura in corso...");
                    running = false;
                    break;

                default:
                    System.out.println("\n Scelta non valida. Riprova."); 
  
        }
    }
}
     
      private static void SalvaRistorante() {

      }

      
                    private static void login() {
                    SalvataggioUtente su = new SalvataggioUtente();
                    utenti = su.getTutti();
                    System.out.print("Username: ");
                    String username = sc.nextLine();
                    System.out.print("Password: ");
                    String password = sc.nextLine();
                    String passwordHash;
    try {
        passwordHash = PasswordCriptata.hash(password);
    } catch (NoSuchAlgorithmException e) {
        System.out.println("Errore criptazione password");
        return;
    }
                    Utente trovato = null;
                    for (Utente u : utenti) {
                         System.out.println("Username: " + u.getusername() + ", Password: " + u.getpassword());
                        if (u.getusername().equals(username) && u.getpassword().equals(passwordHash)) {
                            trovato = u;
                            break;
                        }
                    }
                     if (trovato == null) {
                         System.out.println("Credenziali errate");
                             return;
                        }
                            
                        System.out.println("\nBenvenuto " + trovato.getNome() + "!");
                            
                            if (trovato instanceof Cliente) {
    menuCliente((Cliente) trovato);
} else if (trovato instanceof Ristoratore) {
    menuRistoratore((Ristoratore) trovato);
}
    }

private static void menuCliente(Cliente cliente) {
    String scelta;

    do {
        System.out.println("BENVENUTO NEL MENU' CLIENTE");
        System.out.println("1) Visualizza ristoranti");
        System.out.println("2) Aggiungi ristorante ai preferiti");
        System.out.println("3) Rimuovi ristorante dai preferiti");
        System.out.println("4) Visualizza preferiti");
        System.out.println("5) Aggiungi recensione");
        System.out.println("6) Modifica recensione");
        System.out.println("7) Elimina recensione");
        System.out.println("0) Logout");
        System.out.print("Scelta: ");

        scelta = sc.nextLine();

        switch (scelta) {
            case "1":
                visualizzaDettagliRistoranti(); 
                break;

            case "2":
                aggiungiPreferito(cliente);
                break;

            case "3":
                rimuoviPreferito(cliente);
                break;

            case "4":
                visualizzaPreferiti(cliente);
                break;

            case "5":
                aggiungiRecensione(cliente);
                break;

            case "6":
                modificaRecensione(cliente);
                break;

            case "7":
                eliminaRecensione(cliente);
                break;

            case "0":
                System.out.println("Logout effettuato.");
                break;

            default:
                System.out.println("Scelta non valida.");
        }

    } while (!scelta.equals("0"));
}


private static void aggiungiPreferito(Cliente cliente) {
    SalvataggioRistorante dao = new SalvataggioRistorante();

    Ristorante rist = selezionaRistoranteDaNome(dao.getTutti());
    if (rist == null) return;

    for (Ristorante p : cliente.ristorantePreferiti()) {
        if (p.getId().equals(rist.getId())) {
            System.out.println("Questo ristorante è già nei preferiti.");
            return;
        }
    }

    cliente.ristorantePreferiti().add(rist);
    System.out.println("Aggiunto ai preferiti: " + rist.getnome());
}

private static void rimuoviPreferito(Cliente cliente) {
    List<Ristorante> pref = cliente.ristorantePreferiti();
    if (pref.isEmpty()) {
        System.out.println("Non hai ristoranti salvati come preferiti.");
        return;
    }

    System.out.println("I TUOI PREFERITI ");
    for (int i = 0; i < pref.size(); i++) {
        System.out.println((i + 1) + ") " + pref.get(i).getnome() + " (" + pref.get(i).getCitta() + ")");
    }
    System.out.print("Seleziona numero da rimuovere (0 annulla): ");

    int scelta = leggiIntero();
    if (scelta <= 0 || scelta > pref.size()) {
        System.out.println("Operazione annullata.");
        return;
    }

    Ristorante rimosso = pref.remove(scelta - 1);
    System.out.println("Rimosso dai preferiti: " + rimosso.getnome());
}

private static void visualizzaPreferiti(Cliente cliente) {
    List<Ristorante> pref = cliente.ristorantePreferiti();
    if (pref.isEmpty()) {
        System.out.println("Non hai preferiti.");
        return;
    }

    System.out.println("I TUOI PREFERITI");
    for (Ristorante r : pref) {
        System.out.println(r);
        System.out.println("Valutazione media: " + r.calcolaValutazioneMedia());
    }
}

private static void aggiungiRecensione(Cliente cliente) {
    SalvataggioRistorante dao = new SalvataggioRistorante();
    List<Ristorante> tutti = dao.getTutti();

    Ristorante rist = selezionaRistoranteDaNome(tutti);
    if (rist == null) return;

    
    for (Recensioni rec : rist.getRecensioni()) {
        if (rec.getAutore().equalsIgnoreCase(cliente.getusername())) {
            System.out.println("Hai già recensito questo ristorante. Usa 'Modifica recensione'.");
            return;
        }
    }

    System.out.print("Numero stelle (1-5): ");
    int stelle = leggiIntero();
    if (stelle < 1 || stelle > 5) {
        System.out.println("Valore stelle non valido.");
        return;
    }

    System.out.print("Testo recensione: ");
    String testo = sc.nextLine();

    String idRec = java.util.UUID.randomUUID().toString();

    Recensioni nuova = new Recensioni(
            rist.getusernameRistoratore(), // ristostaristoratore
            idRec,
            rist.getId(),
            cliente.getusername(),
            stelle,
            testo,
            "", // rispostaAutore
            ""  // rispostaTesto
    );

    rist.addRecensione(nuova);
    dao.aggiornaRistorante(rist);

    System.out.println("Recensione aggiunta con successo!");
}

private static void modificaRecensione(Cliente cliente) {
    SalvataggioRistorante dao = new SalvataggioRistorante();
    List<Ristorante> tutti = dao.getTutti();

    Ristorante rist = selezionaRistoranteDaNome(tutti);
    if (rist == null) return;

    Recensioni mia = null;
    for (Recensioni rec : rist.getRecensioni()) {
        if (rec.getAutore().equalsIgnoreCase(cliente.getusername())) {
            mia = rec;
            break;
        }
    }

    if (mia == null) {
        System.out.println("Non hai ancora inserito una recensione per questo ristorante.");
        return;
    }

    System.out.println("LA TUA RECENSIONE ATTUALE ");
    System.out.println("Stelle: " + mia.getNumeroStelle());
    System.out.println("Testo: " + mia.getCommento());

    System.out.print("Nuove stelle (1-5): ");
    int nuoveStelle = leggiIntero();
    if (nuoveStelle < 1 || nuoveStelle > 5) {
        System.out.println("Valore stelle non valido.");
        return;
    }

    System.out.print("Nuovo testo: ");
    String nuovoTesto = sc.nextLine();

    mia.numeroStelle(nuoveStelle);
    mia.commento(nuovoTesto);

    dao.aggiornaRistorante(rist);
    System.out.println("Recensione modificata con successo!");
}

private static void eliminaRecensione(Cliente cliente) {
    SalvataggioRistorante dao = new SalvataggioRistorante();
    List<Ristorante> tutti = dao.getTutti();

    Ristorante rist = selezionaRistoranteDaNome(tutti);
    if (rist == null) return;

    Recensioni mia = null;
    for (Recensioni rec : rist.getRecensioni()) {
        if (rec.getAutore().equalsIgnoreCase(cliente.getusername())) {
            mia = rec;
            break;
        }
    }

    if (mia == null) {
        System.out.println("Non hai recensioni da eliminare per questo ristorante.");
        return;
    }

    rist.removeRecensione(mia);
    dao.aggiornaRistorante(rist);

    System.out.println("Recensione eliminata con successo!");
}


private static Ristorante selezionaRistoranteDaNome(List<Ristorante> lista) {
    if (lista == null || lista.isEmpty()) {
        System.out.println("Nessun ristorante disponibile.");
        return null;
    }

    System.out.print("Inserisci nome (anche parziale) del ristorante: ");
    String query = sc.nextLine().toLowerCase();

    List<Ristorante> trovati = new ArrayList<>();
    for (Ristorante r : lista) {
        if (r.getnome().toLowerCase().contains(query)) {
            trovati.add(r);
        }
    }

    if (trovati.isEmpty()) {
        System.out.println("Nessun ristorante trovato.");
        return null;
    }

    if (trovati.size() == 1) {
        return trovati.get(0);
    }

    System.out.println("\n--- RISULTATI ---");
    for (int i = 0; i < trovati.size(); i++) {
        Ristorante r = trovati.get(i);
        System.out.println((i + 1) + ") " + r.getnome() + " - " + r.getCitta() + " (" + r.getTipoCucina() + ")");
    }

    System.out.print("Seleziona numero (0 annulla): ");
    int scelta = leggiIntero();
    if (scelta <= 0 || scelta > trovati.size()) {
        System.out.println("Operazione annullata.");
        return null;
    }

    return trovati.get(scelta - 1);
}

private static int leggiIntero() {
    while (true) {
        String s = sc.nextLine().trim();
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.print("Inserisci un numero valido: ");
        }
    }
}

    private  static void menuRistoratore(Ristoratore r) {

    String scelta;

    do {
        System.out.println(" BENVENUTO NEL MENU' RISTORATORE");
        System.out.println("1) Aggiungi ristorante");
        System.out.println("2) Visualizza i miei ristoranti");
        System.out.println("3) Visualizza recensioni");
        System.out.println("4) Rispondi a una recensione");
        System.out.println("5. Statistiche ristoranti");
        System.out.println("0. Logout");

        scelta = sc.nextLine();

        switch (scelta) {
            case "1" : aggiungiRistorante(r);

            case "2" : visualizzaMieiRistoranti(r);

            case "3" : visualizzaRecensioni(r);
            case "4" : rispondiRecensione(r);
            case "5":  statisticheRistoranti(r); 
        }

    } while (!scelta.equals("0")); 
}
    
    
                    
    private static void usaComeGuest() {
        System.out.println("Stai visualizzando in modalità ospite. ");
        System.out.println("1) VisualizzaRistoranti");
        System.out.println("2) Registrazione");
        System.out.println("3) CercaRistoranti");
        System.out.print("Scelta: ");

          String scelta1 = sc.nextLine();
            switch (scelta1) {
             
                case "1":  
                visualizzaDettagliRistoranti();
                break;
                case "2": 
                Scanner sc = new Scanner (System.in);
                SalvataggioUtente salvataggio=new SalvataggioUtente();
                System.out.println("1) Registrazione Ristoratore");
                System.out.println("2)Registrazione Cliente");
                int a=sc.nextInt(); 
                if(a==1) {
            Ristoratore S1=Ristoratore.registrazioneRistoratore();
      salvataggio.aggiungiUtente(S1);
                } else if (a==2) {
       Cliente S2= Cliente.registrazionecliente();
                  salvataggio.aggiungiUtente(S2);
                } else {
                    System.out.println("Errore: Valore non valido");
                }
                    break;
                    case "3":
                    menuRicercaGuest(); 

                default:
                    System.out.println("\n Scelta non valida. Riprova."); 
                }

    }      


    public static void visualizzaDettagliRistoranti() {
    SalvataggioRistorante dao = new SalvataggioRistorante();
    List<Ristorante> lista = dao.getTutti();

    if (lista.isEmpty()) {
        System.out.println("Nessun ristorante disponibile");
        return;
    }

    for (Ristorante r : lista) {
        r.stampaDettagli();
    }
}
   public static void visualizzaDettagliRistoranti(List<Ristorante> lista) {
    SalvataggioRistorante dao = new SalvataggioRistorante();
    lista = dao.getTutti();

    if (lista.isEmpty()) {
        System.out.println("Nessun ristorante disponibile");
        return;
    }

    for (Ristorante r : lista) {
        r.stampaDettagli();
    }
}






private static void menuRicercaGuest() {
    System.out.println("Ora ti trovi nel menù di ricerca Ristoranti");
    System.out.println("1) Ricerca per nome");
    System.out.println("2) Ricerca per città");
    System.out.println("3) Ricerca per tipo di cucina");
    System.out.println("4) Ricerca per fascia di prezzo");
    System.out.print("Scelta: ");

    String scelta = sc.nextLine();

    switch (scelta) {

        case "1":
            System.out.print("Nome ristorante: ");
            String nome = sc.nextLine();
            List<Ristorante> risultati= GestoreRistoranti.cercaPerNome(nome);
            visualizzaDettagliRistoranti(risultati);
            break;

        case "2":
            System.out.print("Città: ");
            String citta = sc.nextLine();
            visualizzaDettagliRistoranti(GestoreRistoranti.cercaPerCitta(citta));
            break;

        case "3":
            System.out.print("Tipo cucina: ");
            String tipo = sc.nextLine();
            visualizzaDettagliRistoranti(GestoreRistoranti.cercaPerTipoCucina(tipo));
            break;

        case "4":
            System.out.print("Prezzo massimo: ");
            try {
            double prezzo = Double.parseDouble(sc.nextLine());
            visualizzaDettagliRistoranti(GestoreRistoranti.cercaperFasciaPrezzo(prezzo));
            } catch (NumberFormatException e ) {
                System.out.println("Inserisci un numero valido");
            }
            break;

        default:
            System.out.println("Scelta non valida");
    }
}

private static void aggiungiRistorante(Ristoratore r) {
    Ristorante nuovo = Ristorante.creaDaInput(r.getusername()); 

    SalvataggioRistorante dao = new SalvataggioRistorante();
    dao.aggiungiRistorante(nuovo);

    System.out.println("Ristorante aggiunto correttamente!");
}
private static void visualizzaMieiRistoranti(Ristoratore r) {
    List<Ristorante> tutti = GestoreRistoranti.getRistoranti(); // tutti i ristoranti
    visualizzaDettagliRistoranti(tutti); // stampa dettagli
}






private static void rispondiRecensione(Ristoratore r) {

    SalvataggioRistorante dao = new SalvataggioRistorante();
    List<Ristorante> tutti = dao.getTutti();

    // filtro solo i ristoranti del ristoratore loggato
    List<Ristorante> miei = new ArrayList<>();
    for (Ristorante x : tutti) {
        if (x.getusernameRistoratore().equals(r.getusername())) {
            miei.add(x);
        }
    }

    if (miei.isEmpty()) {
        System.out.println("Non hai ancora ristoranti registrati...");
        return;
    }

    System.out.println("Scegli il ristorante:");
    for (int i = 0; i < miei.size(); i++) {
        System.out.println((i + 1) + ") " + miei.get(i).getnome());
    }
    System.out.print("Scelta: ");
    int idxR = Integer.parseInt(sc.nextLine()) - 1;

    if (idxR < 0 || idxR >= miei.size()) {
        System.out.println("Scelta non valida.");
        return;
    }

    Ristorante scelto = miei.get(idxR);

    if (scelto.getRecensioni().isEmpty()) {
        System.out.println("Questo ristorante non ha recensioni...");
        return;
    }

    System.out.println("Ora scegli la recensione:");
    for (int j = 0; j < scelto.getRecensioni().size(); j++) {
        Recensioni rec = scelto.getRecensioni().get(j);
        System.out.println((j + 1) + ") " + rec.getAutore()
                + " - " + rec.getNumeroStelle() + "★");
        System.out.println("   " + rec.getCommento());

        if (rec.getrispostaAutore().equalsIgnoreCase("si")) {
            System.out.println("   Risposta: " + rec.getRispostaTesto());
        } else {
            System.out.println("Non è presente alcuna risposta");
        }
    }

    System.out.print("Scelta: ");
    int idxRec = Integer.parseInt(sc.nextLine()) - 1;

    if (idxRec < 0 || idxRec >= scelto.getRecensioni().size()) {
        System.out.println("Scelta non valida.");
        return;
    }

    Recensioni recScelta = scelto.getRecensioni().get(idxRec);

    if (recScelta.haRisposta()) {
    System.out.println("Hai già risposto a questa recensione...");
    return;
}

System.out.print("Scrivi la risposta: ");
String testo = sc.nextLine().trim();
if (testo.isEmpty()) {
    System.out.println("Risposta non valida, operazione annullata.");
    return;
}

recScelta.rispondi(r.getusername(), testo);
dao.aggiornaRistorante(scelto);
System.out.println("Risposta salvata correttamente...");

}

private static void visualizzaRecensioni(Ristoratore r) {
    SalvataggioRistorante dao = new SalvataggioRistorante();
    List<Ristorante> tutti = dao.getTutti();

    for (Ristorante ris : tutti) {
        if (ris.getusernameRistoratore().equals(r.getusername())) {

            System.out.println("Ristorante: " + ris.getnome());

            if (ris.getRecensioni().isEmpty()) {
                System.out.println("  Nessuna recensione.");
            }

            for (Recensioni rec : ris.getRecensioni()) {
                System.out.println("- " + rec.getAutore()
                        + " (" + rec.getNumeroStelle() + "★): "
                        + rec.getCommento());

                if (rec.haRisposta()) {
                    System.out.println("  Risposta: " + rec.getRispostaTesto());
                } else {
                    System.out.println("  Risposta: (nessuna)");
                }
            }
        }
    }
}


private static void statisticheRistoranti(Ristoratore r) {
    SalvataggioRistorante dao = new SalvataggioRistorante();
    List<Ristorante> tutti = dao.getTutti();

    boolean almenoUno = false;

    System.out.println("Ora potrai vedere le statistiche dei tuoi ristoranti");

    for (Ristorante ris : tutti) {
        if (ris.getusernameRistoratore().equals(r.getusername())) {
            almenoUno = true;

            System.out.println("Ristorante: " + ris.getnome());
            System.out.println("Numero recensioni: " + ris.getNumeroRecensioni());
            System.out.printf("Valutazione media: % "+  ris.calcolaValutazioneMedia());
        }
    }

    if (!almenoUno) {
        System.out.println("Non hai ancora ristoranti registrati.");
    }
}





}

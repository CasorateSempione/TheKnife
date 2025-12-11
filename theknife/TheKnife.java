package theknife;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TheKnife {

    private static Scanner sc = new Scanner(System.in);
    private static List<Utente> utenti = new ArrayList<>();
    private static SalvataggioUtente fileutenti = new SalvataggioUtente();

    public static void main(String[] args) {
        utenti = fileutenti.getTutti();
        System.out.println("Utenti caricati: " + utenti.size());
        for (Utente u : utenti) {
            System.out.println(u.getusername() + " - " + u.getruolo());
        }
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
                System.out.println("1) Registrazione Ristorante");
                System.out.println("2)Registrazione Utente");
                int a=sc.nextInt(); 
                if(a==1) {
                    Ristorante nuovoRistorante=registrazioneRistorante();
                } else if (a==2) {
                    Utente nuovoUtente=registrazioneUtente(); 
                    Utente.load(nuovoUtente);
                } else {
                    System.out.println("Errore: Valore non valido");
                }
                    break;
<<<<<<< HEAD
                 */
=======

>>>>>>> a03a898b366d87b13bc492b73de50faff39847cc
                case "3":
                    // usaComeGuest();
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

    private static void login() {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        Utente trovato = null;
        for (Utente u : utenti) {
            // Stampa debug per vedere che il for viene eseguito
            System.out.println("Controllo utente: " + u.getusername());
            if (u.getusername().equals(username) && u.getpassword().equals(password)) {
                trovato = u;
                break;
            }
        }

        if (trovato == null) {
            System.out.println("Credenziali errate");
            return;
        }

        System.out.println("Benvenuto " + trovato.getNome() + "!");
    }

    private static void registrazioneUtente(String nome, String cognome, String mail, String password, String domicilio, String username) {

    }
}
<<<<<<< HEAD
//private static void usaComeGuest() {}      
=======
     
      

      
                    private static void login() {
                    System.out.print("Username: ");
                    String username = sc.nextLine();
                    System.out.print("Password: ");
                    String password = sc.nextLine();
                    Utente trovato = null;
                    for (Utente u : utenti) {
                        if (u.getusername().equals(username) && u.getmail().equals(password)) {
                            trovato = u;
                            break;
                        }
                    }
                    }
                    private static void registrazioneUtente () { 
                    Scanner sc= new Scanner(System.in);
                   
                    System.out.println("Inserire nome"); 
                    String nome= sc.nextLine();

                    System.out.println("Inserire cognome"); 
                    String cognome=sc.nextLine();

                    System.out.println("Inserire domicilio ");
                    String domicilio=sc.nextLine();
                    
                    System.out.println("Inserire username");
                    String username=sc.nextLine();

                    System.out.println("Inserire mail");
                    String mail=sc.nextLine();
                    
                    System.out.println("Inserire Password");
                    String Password=sc.nextLine();
                    String ruolo= Utente;
                     
            
                    return new Utente(ruolo, nome, cognome, mail, Password, domicilio, username);
                    }

                






}                    }
                    //private static void usaComeGuest() {}      


>>>>>>> a03a898b366d87b13bc492b73de50faff39847cc


package theknife;

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
      salvataggio.aggiungiUtente(S1);
                } else if (a==2) {
       Cliente S2= Cliente.registrazionecliente();
                  salvataggio.aggiungiUtente(S2);
                } else {
                    System.out.println("Errore: Valore non valido");
                }
                    break;

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
                         System.out.println("Username: " + u.getusername() + ", Password: " + u.getpassword());
                        if (u.getusername().equals(username) && u.getpassword().equals(password)) {
                            trovato = u;
                            break;
                        }
                    }
                     if (trovato == null) {
                         System.out.println("Credenziali errate");
                             return;
                        }else
                            {
                                System.out.println("\nBenvenuto " + trovato.getNome() + "!");
                            }
    }


                






                    
   /*  private static void usaComeGuest() {
        System.out.println("Stai visualizzando in modalità ospite. ");
        System.out.println("1) VisualizzaRistoranti");
        System.out.println("2) Registrazione");
        System.out.println("3) CercaRistoranti");
        System.out.print("Scelta: ");

            scelta1 = sc.nextLine();
            switch (scelta1) {
             
                case("1") { 

                }

    }      
}*/
}

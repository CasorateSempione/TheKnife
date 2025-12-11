
package theknife;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TheKnife {
    private static Scanner sc = new Scanner(System.in);
     private static List<Utente> utenti = new ArrayList<>();

    public static void main(String[] args) {
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
                    registrazione();
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
                    private static void registrazione() 
                    {
                        
                    }
                    private static void usaComeGuest() {}      

}


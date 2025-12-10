
package theknife;

import java.util.Scanner;

public class TheKnife {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
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
                        
                
                    }
                    private static void registrazione() {}
                    private static void usaComeGuest() {}      

}


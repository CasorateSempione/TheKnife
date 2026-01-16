package theknife;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;





public class Cliente extends Utente{
   private  List<Ristorante> ristorantiPreferiti = new ArrayList<>(); 
    public Cliente(String nome, String cognome, String domicilio, String mail,
                     String Password, String username, String ruolo) {
         super( nome, cognome, domicilio, mail, Password, username, ruolo);
    }       
public  List<Ristorante> ristorantePreferiti(){
    return ristorantiPreferiti;

}
public void setRistorantiPreferiti(List<Ristorante> ristorantiPreferiti){
    this.ristorantiPreferiti=ristorantiPreferiti;   

} 

 public  static Cliente registrazionecliente() {
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

                    String ruolo="Cliente";
                    
                    System.out.println("Inserire Password");
                    String Password=sc.nextLine();
                    try { 
                    String hash = PasswordCriptata.hash(Password);
                    } catch(NoSuchAlgorithmException e) {System.out.println("Errore");
                }
            
                    return new Cliente(nome, cognome, domicilio, mail, Password, username, ruolo); 
                    
                    
}

}


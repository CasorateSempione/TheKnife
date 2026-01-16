package theknife;

import java.util.List;


public abstract class Utente {
  private  List<Utente> utenti;
private String nome;
private String cognome;
private String domicilio;
private String username;
private String mail;
private String password;
private String ruolo;
public Utente(String nome, String cognome, String domicilio, String mail, String password, String username, String ruolo) {
    
    this.nome=nome;
    this.cognome=cognome;
    this.domicilio=domicilio;
    this.mail=mail;
    this.password=password;
    this.username=username;
    this.ruolo=ruolo;
}

public String getNome(){
    return nome;
}
public String getcognome(){
    return cognome;

}
public String getdomicilio(){
    return domicilio;
}  
public String getmail(){
    return mail;
}
public String getpassword(){
    return password;
}

public String getusername(){
    return username;
} 

public String getruolo(){
    return ruolo;
}

}
package theknife;


public abstract  class  Utente {
private String nome;
private String cognome;
private String domicilio;
private String username;
private String mail;
private String password;
private String ruolo;
public Utente( String nome, String cognome, String mail, String password, String domicilio, String username, String ruolo){
    this.nome=nome;
    this.cognome=cognome;
    this.domicilio=domicilio;
    this.username=username;
    this.mail=mail;
    this.password=password;
    this.ruolo=ruolo;
}

public String getnome(){
    return nome;
}
public String getcognome(){
    return cognome;

}
public String getdomicilio(){
    return domicilio;
}  

public String getusername(){
    return username;
} 
public String getmail(){
    return mail;
}
}
package theknife;

public class Risposta {
    private String autore;
    private String testo;
    
    public Risposta(String autore, String testo) {
        this.autore = autore;
        this.testo = testo;
    }
    
    public String getAutore() {
        return autore;
    }
    
    public String getTesto() {
        return testo;
    }
   
}
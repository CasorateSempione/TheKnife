package theknife;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Ristorante extends Utente {
    
    private String nazione;
    private String citta;
    private String indirizzo;
    private Double latitudine;
    private Double longitudine;
    private double fasciaPrezzo;
    private boolean delivery;
    private boolean prenotazioneOnline;
    private String tipoCucina;
    private List<Recensione> recensioni= new ArrayList<>();

    public Ristorante(String nome, String cognome, String domicilio, String mail,
                    String password, String username, String ruolo,
                       String nazione, String citta, String indirizzo,
                       double latitudine, double longitudine, double fasciaPrezzo,
                       boolean delivery, boolean prenotazioneOnline, String tipoCucina) {
        
        super( nome, cognome, password, domicilio, username, mail, ruolo);
        
        this.nazione = nazione;
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.fasciaPrezzo = fasciaPrezzo;
        this.delivery = delivery;
        this.prenotazioneOnline = prenotazioneOnline;
        this.tipoCucina = tipoCucina;
    }

    public String getNazione() { return nazione; }
    public String getCitta() { return citta; }
    public String getIndirizzo() { return indirizzo; }
    public Double getLatitudine() { return latitudine; }
    public Double getLongitudine() { return longitudine; }
    public double getFasciaPrezzo() { return fasciaPrezzo; }
    public boolean isDelivery() { return delivery; }
    public boolean isPrenotazioneOnline() { return prenotazioneOnline; }
    public String getTipoCucina() { return tipoCucina; }
}   public List<Recensione> getRecensioni() {return recensioni; }

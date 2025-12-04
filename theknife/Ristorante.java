package theknife;

import java.util.ArrayList;
import java.util.List;

public class Ristorante{
    
    private String nazione;
    private String citta;
    private String indirizzo;
    private Double latitudine;
    private Double longitudine;
    private double fasciaPrezzo;
    private boolean delivery;
    private boolean prenotazioneOnline;
    private String tipoCucina;
    private List<Recensioni> recensioni= new ArrayList<>();

    public Ristorante(String nome,String nazione, String citta, String indirizzo,
                       double latitudine, double longitudine, double fasciaPrezzo,
                       boolean delivery, boolean prenotazioneOnline, String tipoCucina) {
        
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
     public List<Recensioni> getRecensioni() {return recensioni; }
        public void AddRecensione(Recensioni r){
            this.recensioni.add(r);
        }   
        public void removeRecensione(Recensioni r){
            this.recensioni.remove(r);
        }
        public double calcolaValutazioneMedia(){
            if (recensioni.isEmpty()) {
                return 0.0;
            }
            int sommaStelle = 0;
            for (Recensioni recensione : recensioni) {
                sommaStelle += recensione.getNumeroStelle();
            }
            return (double) sommaStelle / recensioni.size();
        }
} 
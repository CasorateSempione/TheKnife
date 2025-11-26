package TheKnife;

public class Ristoratore extends Utente {
    
    private String nazione;
    private String citta;
    private String indirizzo;
    private Double latitudine;
    private Double longitudine;
    private double fasciaPrezzo;
    private boolean delivery;
    private boolean prenotazioneOnline;
    private String tipoCucina;

    public Ristoratore(int id, String nome, String mail, String password,
                       String nazione, String citta, String indirizzo,
                       double latitudine, double longitudine, double fasciaPrezzo,
                       boolean delivery, boolean prenotazioneOnline, String tipoCucina) {
        
        super(id, nome, mail, password);
        
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
}

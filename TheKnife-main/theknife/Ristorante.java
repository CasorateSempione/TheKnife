package theknife;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe <code>Ristorante</code> modella un ristorante presente
 * all'interno dell'applicazione TheKnife.
 * <p>
 * Ogni ristorante è caratterizzato da informazioni anagrafiche
 * (nome, indirizzo, città, nazione), coordinate geografiche,
 * fascia di prezzo, servizi disponibili (delivery e prenotazione online)
 * e una lista di recensioni lasciate dagli utenti.
 * </p>
 *
 * <p>
 * La classe fornisce inoltre metodi per aggiungere e rimuovere recensioni
 * e per calcolare la valutazione media del ristorante.
 * </p>
 *
 * @version 1.0
 */
public class Ristorante {

    /** Identificativo univoco del ristorante. */
    private String Id;

    /** Nome del ristorante. */
    private String nome;

    /** Nazione in cui si trova il ristorante. */
    private String nazione;

    /** Città in cui si trova il ristorante. */
    private String citta;

    /** Indirizzo completo del ristorante. */
    private String indirizzo;

    /** Latitudine della posizione geografica del ristorante. */
    private Double latitudine;

    /** Longitudine della posizione geografica del ristorante. */
    private Double longitudine;

    /** Fascia di prezzo indicativa del ristorante. */
    private double fasciaPrezzo;

    /** Indica se il ristorante offre il servizio di delivery. */
    private boolean delivery;

    /** Indica se il ristorante permette la prenotazione online. */
    private boolean prenotazioneOnline;

    /** Tipologia di cucina offerta dal ristorante. */
    private String tipoCucina;

    /** Lista delle recensioni associate al ristorante. */
    private List<Recensioni> recensioni = new ArrayList<>();

    /**
     * Costruisce un nuovo oggetto <code>Ristorante</code> inizializzando
     * tutte le informazioni principali.
     */
    public Ristorante(String Id, String nome, String nazione, String citta, String indirizzo,
                      double latitudine, double longitudine, double fasciaPrezzo,
                      boolean delivery, boolean prenotazioneOnline, String tipoCucina) {

        this.Id = Id;
        this.nome = nome;
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
    public String getNome() { return nome; }
    public String getId() { return Id; }
    public String getCitta() { return citta; }
    public String getIndirizzo() { return indirizzo; }
    public Double getLatitudine() { return latitudine; }
    public Double getLongitudine() { return longitudine; }
    public double getFasciaPrezzo() { return fasciaPrezzo; }
    public boolean isDelivery() { return delivery; }
    public boolean isPrenotazioneOnline() { return prenotazioneOnline; }
    public String getTipoCucina() { return tipoCucina; }
    public List<Recensioni> getRecensioni() { return recensioni; }

    /** Aggiunge una recensione alla lista. */
    public void addRecensione(Recensioni r) {
        this.recensioni.add(r);
    }

    /** Rimuove una recensione dalla lista. */
    public void removeRecensione(Recensioni r) {
        this.recensioni.remove(r);
    }

    /**
     * Calcola la valutazione media del ristorante.
     *
     * @return media delle stelle oppure 0.0 se non ci sono recensioni
     */
    public double calcolaValutazioneMedia() {
        if (recensioni.isEmpty()) {
            return 0.0;
        }

        int sommaStelle = 0;
        for (Recensioni recensione : recensioni) {
            sommaStelle += recensione.getNumeroStelle();
        }

        return (double) sommaStelle / recensioni.size();
    }

    /**
     * Stampa i dettagli principali del ristorante.
     */
    public void stampaDettagli() {
        System.out.println("Nome: " + getNome());
        System.out.println("Luogo: " + getNazione() + ", " + getCitta());
        System.out.println("Fascia di prezzo: " + getFasciaPrezzo());
        System.out.println("Delivery: " + (isDelivery() ? "Sì" : "No"));
        System.out.println("Prenotazione online: " + (isPrenotazioneOnline() ? "Sì" : "No"));
        System.out.println("Tipo cucina: " + getTipoCucina());
        System.out.println("--------------------------------");
    }
}
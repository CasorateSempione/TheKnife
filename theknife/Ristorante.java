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
 * @author CADDU
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
     *
     * @param Id                  identificativo del ristorante
     * @param nome                nome del ristorante
     * @param nazione             nazione in cui si trova
     * @param citta               città in cui si trova
     * @param indirizzo           indirizzo completo
     * @param latitudine          coordinata geografica (latitudine)
     * @param longitudine         coordinata geografica (longitudine)
     * @param fasciaPrezzo        fascia di prezzo indicativa
     * @param delivery            disponibilità del servizio delivery
     * @param prenotazioneOnline  disponibilità della prenotazione online
     * @param tipoCucina          tipologia di cucina offerta
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

    /** @return la nazione del ristorante */
    public String getNazione() { return nazione; }

    /** @return il nome del ristorante */
    public String getNome() { return nome; }

    /** @return l'identificativo del ristorante */
    public String getId() { return Id; }

    /** @return la città del ristorante */
    public String getCitta() { return citta; }

    /** @return l'indirizzo del ristorante */
    public String getIndirizzo() { return indirizzo; }

    /** @return la latitudine della posizione */
    public Double getLatitudine() { return latitudine; }

    /** @return la longitudine della posizione */
    public Double getLongitudine() { return longitudine; }

    /** @return la fascia di prezzo */
    public double getFasciaPrezzo() { return fasciaPrezzo; }

    /** @return true se il ristorante offre delivery */
    public boolean isDelivery() { return delivery; }

    /** @return true se il ristorante permette la prenotazione online */
    public boolean isPrenotazioneOnline() { return prenotazioneOnline; }

    /** @return la tipologia di cucina */
    public String getTipoCucina() { return tipoCucina; }

    /** @return la lista delle recensioni */
    public List<Recensioni> getRecensioni() { return recensioni; }

    /**
     * Aggiunge una recensione alla lista.
     *
     * @param r la recensione da aggiungere
     */
    public void addRecensione(Recensioni r) {
        this.recensioni.add(r);
    }

    /**
     * Rimuove una recensione dalla lista.
     *
     * @param r la recensione da rimuovere
     */
    public void removeRecensione(Recensioni r) {
        this.recensioni.remove(r);
    }

    /**
     * Calcola la valutazione media del ristorante sulla base delle recensioni.
     *
     * @return la media delle stelle, oppure 0.0 se non ci sono recensioni
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
}
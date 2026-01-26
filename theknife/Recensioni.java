package theknife;

/**
 * La classe <code>Recensioni</code> rappresenta una recensione associata a un
 * ristorante. Ogni recensione contiene un identificativo, l'autore, il numero
 * di stelle assegnate, un commento testuale e un'eventuale risposta del
 * ristoratore.
 * <p>
 * La risposta del ristoratore è modellata tramite un oggetto della classe
 * <code>Risposta</code>, che contiene autore e testo della risposta.
 * </p>
 *
 * @author CADDU
 * @version 1.0
 */
public class Recensioni {

    /** Identificativo univoco della recensione. */
    private String id;

    /** Identificativo del ristorante a cui la recensione appartiene. */
    private String ristoranteid;

    /** Nome dell'autore della recensione. */
    private String autore;

    /** Numero di stelle assegnate dal cliente (1–5). */
    private int numeroStelle;

    /** Commento testuale associato alla recensione. */
    private String commento;

    /** Eventuale risposta del ristoratore alla recensione. */
    private Risposta risposta;

    /**
     * Costruisce un oggetto <code>Recensioni</code> inizializzando tutti i campi
     * principali. Se vengono forniti autore e testo della risposta, viene creato
     * automaticamente un oggetto <code>Risposta</code>.
     *
     * @param id             identificativo della recensione
     * @param ristoranteid   identificativo del ristorante
     * @param autore         autore della recensione
     * @param numeroStelle   numero di stelle assegnate
     * @param commento       commento testuale
     * @param rispostaAutore autore della risposta (può essere null)
     * @param rispostaTesto  testo della risposta (può essere null)
     */
    public Recensioni(String id, String ristoranteid, String autore, int numeroStelle,
                      String commento, String rispostaAutore, String rispostaTesto) {

        this.id = id;
        this.ristoranteid = ristoranteid;
        this.autore = autore;
        this.numeroStelle = numeroStelle;
        this.commento = commento;

        if (rispostaAutore != null && rispostaTesto != null) {
            this.risposta = new Risposta(rispostaAutore, rispostaTesto);
        }
    }

    /** @return l'identificativo della recensione */
    public String getId() {
        return id;
    }

    /** @return l'identificativo del ristorante associato */
    public String getRistoranteid() {
        return ristoranteid;
    }

    /** @return l'autore della recensione */
    public String getAutore() {
        return autore;
    }

    /** @return il numero di stelle assegnate */
    public int getNumeroStelle() {
        return numeroStelle;
    }

    /** @return il commento testuale della recensione */
    public String getCommento() {
        return commento;
    }

    /**
     * Imposta un nuovo numero di stelle.
     *
     * @param numeroStelle nuovo valore di stelle
     */
    public void setNumeroStelle(int numeroStelle) {
        this.numeroStelle = numeroStelle;
    }

    /**
     * Imposta un nuovo commento.
     *
     * @param commento nuovo commento testuale
     */
    public void setCommento(String commento) {
        this.commento = commento;
    }

    /**
     * Imposta la risposta del ristoratore.
     *
     * @param risposta oggetto <code>Risposta</code> contenente autore e testo
     */
    public void setRisposta(Risposta risposta) {
        this.risposta = risposta;
    }

    /**
     * Restituisce la risposta del ristoratore, se presente.
     *
     * @return la risposta oppure null se non esiste
     */
    public Risposta getRisposta() {
        return risposta;
    }
}
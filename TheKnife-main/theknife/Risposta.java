package theknife;

/**
 * La classe <code>Risposta</code> modella la risposta fornita da un ristoratore
 * a una recensione lasciata da un cliente.
 * <p>
 * Una risposta è composta da due elementi principali:
 * </p>
 * <ul>
 *     <li><strong>autore</strong>: il nome del ristoratore che risponde</li>
 *     <li><strong>testo</strong>: il contenuto testuale della risposta</li>
 * </ul>
 *
 * <p>
 * Questa classe è utilizzata all'interno della classe <code>Recensioni</code>
 * per rappresentare in modo strutturato la replica del ristoratore.
 * </p>
 *
 * @author CADDU
 * @version 1.0
 */
public class Risposta {

    /** Nome dell'autore della risposta (tipicamente il ristoratore). */
    private String autore;

    /** Testo della risposta associata alla recensione. */
    private String testo;

    /**
     * Costruisce un nuovo oggetto <code>Risposta</code> inizializzando autore e testo.
     *
     * @param autore il nome dell'autore della risposta
     * @param testo  il contenuto testuale della risposta
     */
    public Risposta(String autore, String testo) {
        this.autore = autore;
        this.testo = testo;
    }

    /**
     * Restituisce il nome dell'autore della risposta.
     *
     * @return il nome dell'autore
     */
    public String getAutore() {
        return autore;
    }

    /**
     * Restituisce il testo della risposta.
     *
     * @return il contenuto testuale della risposta
     */
    public String getTesto() {
        return testo;
    }
}
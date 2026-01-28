package theknife;

/**
 * Rappresenta una risposta lasciata a una recensione.
 * <p>Contiene l’autore della risposta e il testo scritto.
 */
public class Risposta {

    /**
     * Nome di chi ha scritto la risposta.
     */
    private String autore;

    /**
     * Testo della risposta.
     */
    private String testo;

    /**
     * Crea una nuova risposta con autore e testo.
     *
     * @param autore chi ha scritto la risposta
     * @param testo contenuto della risposta
     */
    public Risposta(String autore, String testo) {
        this.autore = autore;
        this.testo = testo;
    }

    /**
     * @return l’autore della risposta
     */
    public String getAutore() {
        return autore;
    }

    /**
     * @return il testo della risposta
     */
    public String getTesto() {
        return testo;
    }
}

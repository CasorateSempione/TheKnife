package theknife;

/**
 * Rappresenta una recensione lasciata a un ristorante.
 * <p>Contiene voto in stelle, commento e un’eventuale risposta del ristoratore.
 */
public class Recensioni {

    /**
     * Id univoco della recensione.
     */
    private String id;

    /**
     * Id del ristorante a cui si riferisce la recensione.
     */
    private String ristoranteid;

    /**
     * Autore della recensione (es. username del cliente).
     */
    private String Autore;

    /**
     * Numero di stelle assegnate al ristorante.
     */
    private int numeroStelle;

    /**
     * Testo del commento scritto dal cliente.
     */
    private String commento;

    /**
     * Oggetto che rappresenta la risposta (se presente).
     */
    private Risposta risposta;

    /**
     * Autore della risposta (es. ristoratore).
     */
    protected String rispostaAutore;

    /**
     * Testo della risposta alla recensione.
     */
    protected String rispostaTesto;

    /**
     * Identificativo o riferimento al ristoratore collegato alla recensione.
     */
    private String ristostaristoratore;

    /**
     * Crea una nuova recensione con tutti i dati principali.
     * <p>Se vengono passati autore e testo della risposta,
     * viene creata anche l’oggetto {@link Risposta}.
     *
     * @param ristostaristoratore riferimento al ristoratore
     * @param id id della recensione
     * @param ristoranteid id del ristorante
     * @param Autore autore della recensione
     * @param numeroStelle numero di stelle assegnate
     * @param commento testo del commento
     * @param rispostaAutore autore della risposta (se già presente)
     * @param rispostaTesto testo della risposta (se già presente)
     */
    public Recensioni(String ristostaristoratore, String id, String ristoranteid,
                      String Autore, int numeroStelle, String commento,
                      String rispostaAutore, String rispostaTesto) {
        this.id = id;
        this.ristoranteid = ristoranteid;
        this.Autore = Autore;
        this.numeroStelle = numeroStelle;
        this.commento = commento;
        this.rispostaAutore = rispostaAutore;
        this.ristostaristoratore = ristostaristoratore;
        this.rispostaTesto = rispostaTesto;
        if (rispostaAutore != null && rispostaTesto != null) {
            this.risposta = new Risposta(rispostaAutore, rispostaTesto);
        }
    }

    /**
     * @return l’id della recensione
     */
    public String getId() {
        return id;
    }

    /**
     * @return l’id del ristorante recensito
     */
    public String getRistoranteid() {
        return ristoranteid;
    }

    /**
     * @return l’autore della recensione
     */
    public String getAutore() {
        return Autore;
    }

    /**
     * @return il numero di stelle assegnate
     */
    public int getNumeroStelle() {
        return numeroStelle;
    }

    /**
     * @return il testo del commento
     */
    public String getCommento() {
        return commento;
    }

    /**
     * @return l’autore della risposta (se presente)
     */
    public String getrispostaAutore() {
        return rispostaAutore;
    }

    /**
     * Imposta il numero di stelle della recensione.
     *
     * @param numeroStelle nuovo numero di stelle
     */
    public void numeroStelle(int numeroStelle) {
        this.numeroStelle = numeroStelle;
    }

    /**
     * Imposta il testo del commento.
     *
     * @param commento nuovo commento
     */
    public void commento(String commento) {
        this.commento = commento;
    }

    /**
     * Imposta l’oggetto risposta associato alla recensione.
     *
     * @param risposta nuova risposta
     */
    public void risposta(Risposta risposta) {
        this.risposta = risposta;
    }

    /**
     * @return il riferimento al ristoratore collegato alla recensione
     */
    public String getRiristoratore() {
        return ristostaristoratore;
    }

    /**
     * @return il testo della risposta, se presente
     */
    public String getRispostaTesto() {
        return rispostaTesto;
    }

    /**
     * Verifica se la recensione ha già una risposta valida.
     *
     * @return true se esiste una risposta non vuota, false altrimenti
     */
    public boolean haRisposta() {
        return (rispostaTesto != null && !rispostaTesto.isBlank());
    }

    /**
     * Aggiunge una risposta alla recensione, se non è già presente.
     *
     * @param autoreRisposta autore della risposta
     * @param testoRisposta testo della risposta
     */
    public void rispondi(String autoreRisposta, String testoRisposta) {
        if (haRisposta()) return;

        this.rispostaAutore = autoreRisposta;
        this.rispostaTesto = testoRisposta;
        this.risposta = new Risposta(autoreRisposta, testoRisposta);
    }
}
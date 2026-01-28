package theknife;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Rappresenta un ristorante con le sue informazioni principali,
 * come posizione, tipo di cucina e servizi disponibili.
 * <p>Gestisce anche l’elenco delle recensioni associate.
 *
 * @author Nome Cognome
 */
public class Ristorante {

    /**
     * Identificativo del ristorante.
     */
    private String Id;

    /**
     * Nome del ristorante.
     */
    private String nome;

    /**
     * Nazione in cui si trova il ristorante.
     */
    private String nazione;

    /**
     * Città in cui si trova il ristorante.
     */
    private String citta;

    /**
     * Indirizzo del ristorante.
     */
    private String indirizzo;

    /**
     * Latitudine della posizione del ristorante.
     */
    private Double latitudine;

    /**
     * Longitudine della posizione del ristorante.
     */
    private Double longitudine;

    /**
     * Fascia di prezzo indicativa del ristorante.
     */
    private double fasciaPrezzo;

    /**
     * Indica se il ristorante offre il servizio di delivery.
     */
    private boolean delivery;

    /**
     * Indica se è possibile prenotare online.
     */
    private boolean prenotazioneOnline;

    /**
     * Tipo di cucina offerta dal ristorante.
     */
    private String tipoCucina;

    /**
     * Username del ristoratore proprietario del ristorante.
     */
    private String usernameRistoratore;

    /**
     * Elenco delle recensioni associate al ristorante.
     */
    private List<Recensioni> recensioni = new ArrayList<>();

    /**
     * Costruisce un oggetto Ristorante con tutti i dati necessari.
     *
     * @param Id identificativo del ristorante
     * @param nome nome del ristorante
     * @param nazione nazione in cui si trova
     * @param citta città in cui si trova
     * @param indirizzo indirizzo completo
     * @param latitudine coordinata geografica
     * @param longitudine coordinata geografica
     * @param fasciaPrezzo fascia di prezzo indicativa
     * @param delivery true se offre delivery
     * @param prenotazioneOnline true se permette prenotazioni online
     * @param tipoCucina tipo di cucina offerta
     * @param usernameRistoratore username del proprietario
     */
    public Ristorante(String Id, String nome, String nazione, String citta, String indirizzo,
                      double latitudine, double longitudine, double fasciaPrezzo,
                      boolean delivery, boolean prenotazioneOnline, String tipoCucina, String usernameRistoratore) {
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
        this.usernameRistoratore = usernameRistoratore;
    }

    /** @return la nazione del ristorante */
    public String getNazione() { return nazione; }

    /** @return il nome del ristorante */
    public String getnome() { return nome; }

    /** @return l'id del ristorante */
    public String getId() { return Id; }

    /** @return la città del ristorante */
    public String getCitta() { return citta; }

    /** @return l'indirizzo del ristorante */
    public String getIndirizzo() { return indirizzo; }

    /** @return la latitudine */
    public Double getLatitudine() { return latitudine; }

    /** @return la longitudine */
    public Double getLongitudine() { return longitudine; }

    /** @return la fascia di prezzo */
    public double getFasciaPrezzo() { return fasciaPrezzo; }

    /** @return true se il ristorante offre delivery */
    public boolean isDelivery() { return delivery; }

    /** @return true se è disponibile la prenotazione online */
    public boolean isPrenotazioneOnline() { return prenotazioneOnline; }

    /** @return il tipo di cucina */
    public String getTipoCucina() { return tipoCucina; }

    /** @return la lista delle recensioni */
    public List<Recensioni> getRecensioni() { return recensioni; }

    /**
     * Aggiunge una recensione al ristorante.
     *
     * @param r la recensione da aggiungere
     */
    public void addRecensione(Recensioni r) {
        this.recensioni.add(r);
    }

    /**
     * @return il numero totale di recensioni
     */
    public int getNumeroRecensioni() {
        return recensioni.size();
    }

    /**
     * Rimuove una recensione dal ristorante.
     *
     * @param r la recensione da rimuovere
     */
    public void removeRecensione(Recensioni r) {
        this.recensioni.remove(r);
    }

    /**
     * Calcola la valutazione media del ristorante.
     *
     * @return la media delle stelle, oppure 0 se non ci sono recensioni
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
     * Stampa a schermo i dettagli principali del ristorante.
     */
    public void stampaDettagli() {
        System.out.println("Nome: " + getnome());
        System.out.println("Luogo: " + getNazione() + ", " + getCitta());
        System.out.println("Fascia di prezzo: " + getFasciaPrezzo());
        System.out.println("Delivery: " + (isDelivery() ? "Sì" : "No"));
        System.out.println("Prenotazione online: " + (isPrenotazioneOnline() ? "Sì" : "No"));
        System.out.println("Tipo cucina: " + getTipoCucina());
        System.out.println("--------------------------------");
    }

    /** @return lo username del ristoratore */
    public String getusernameRistoratore() { return usernameRistoratore; }

    /**
     * Crea un nuovo ristorante chiedendo i dati all’utente tramite input.
     *
     * @param usernameRistoratore username del proprietario
     * @return un nuovo oggetto Ristorante con i dati inseriti
     */
    public static Ristorante creaDaInput(String usernameRistoratore) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ora dovrai inserire i dati del tuo ristorante.");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Nazione: ");
        String nazione = sc.nextLine();

        System.out.print("Città: ");
        String citta = sc.nextLine();

        System.out.print("Indirizzo: ");
        String indirizzo = sc.nextLine();

        System.out.print("Tipo di cucina: ");
        String tipoCucina = sc.nextLine();

        System.out.print("Latitudine: ");
        double latitudine = Double.parseDouble(sc.nextLine());

        System.out.print("Longitudine: ");
        double longitudine = Double.parseDouble(sc.nextLine());

        System.out.print("Fascia di prezzo (inserire numero): ");
        double fasciaPrezzo = Double.parseDouble(sc.nextLine());

        System.out.print("Delivery disponibile? (si/no): ");
        boolean delivery = sc.nextLine().equalsIgnoreCase("si");

        System.out.print("Prenotazione online disponibile? (si/no): ");
        boolean prenotazioneOnline = sc.nextLine().equalsIgnoreCase("si");

        String id = UUID.randomUUID().toString();

        return new Ristorante(id, nome, nazione, citta, indirizzo, latitudine, longitudine,
                fasciaPrezzo, delivery, prenotazioneOnline, tipoCucina, usernameRistoratore);
    }
}
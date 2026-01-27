package theknife;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Ristorante{
    
    private String Id;
    private String nome;
    private String nazione;
    private String citta;
    private String indirizzo;
    private Double latitudine;
    private Double longitudine;
    private double fasciaPrezzo;
    private boolean delivery;
    private boolean prenotazioneOnline;
    private String tipoCucina;
    private String usernameRistoratore;
    private List<Recensioni> recensioni= new ArrayList<>();

    public Ristorante(String Id,String nome,String nazione, String citta, String indirizzo,
                       double latitudine, double longitudine, double fasciaPrezzo,
                       boolean delivery, boolean prenotazioneOnline, String tipoCucina,String usernameRistoratore) {
        this.Id=Id;
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
        this.usernameRistoratore=usernameRistoratore;
    }

    public String getNazione() { return nazione; }
    public String getnome() { return nome; }
    public String getId() { return Id; }
    public String getCitta() { return citta; }
    public String getIndirizzo() { return indirizzo; }
    public Double getLatitudine() { return latitudine; }
    public Double getLongitudine() { return longitudine; }
    public double getFasciaPrezzo() { return fasciaPrezzo; }
    public boolean isDelivery() { return delivery; }
    public boolean isPrenotazioneOnline() { return prenotazioneOnline; }
    public String getTipoCucina() { return tipoCucina; }
     public List<Recensioni> getRecensioni() {return recensioni; }
        public void addRecensione(Recensioni r){
            this.recensioni.add(r);
        }   
        public int getNumeroRecensioni() { 
            return recensioni.size();
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

 public void stampaDettagli() {
        System.out.println("Nome: " + getnome());
        System.out.println("Luogo: " + getNazione() + ", " + getCitta());
        System.out.println("Fascia di prezzo: " + getFasciaPrezzo());
        System.out.println("Delivery: " + (isDelivery() ? "Sì" : "No"));
        System.out.println("Prenotazione online: " + (isPrenotazioneOnline() ? "Sì" : "No"));
        System.out.println("Tipo cucina: " + getTipoCucina());
        System.out.println("--------------------------------");
    }

    public String getusernameRistoratore() { return usernameRistoratore;}
   

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
                         fasciaPrezzo, delivery, prenotazioneOnline, tipoCucina,usernameRistoratore);
}



}
        

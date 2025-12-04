package theknife;


public class Recensioni {
    private   String id ;
    private  String ristoranteid;
    private  String Autore;
    private int numeroStelle;
    private String commento;
    private Risposta risposta;
 public Recensioni(String id, String ristoranteid, String Autore, int numeroStelle, String commento, String rispostaAutore, String rispostaTesto){
     this.id=id;
     this.ristoranteid=ristoranteid;
     this.Autore=Autore;
     this.numeroStelle=numeroStelle;
     this.commento=commento;
        if (rispostaAutore != null && rispostaTesto != null){
            this.risposta=new Risposta(rispostaAutore, rispostaTesto);
        }
 }
    public String getId(){
        return id;
    }
    public String getRistoranteid(){
        return ristoranteid;
    }
    public String getAutore(){
        return Autore;
    }
    public int getNumeroStelle(){
        return numeroStelle;
    }
    public String getCommento(){
        return commento;
    }
    public Risposta getRisposta(){
        return risposta;
    }
    public void numeroStelle(int numeroStelle){
        this.numeroStelle=numeroStelle;
    }
    public void commento(String commento){
        this.commento=commento;
    }
    public void risposta(Risposta risposta){
        this.risposta=risposta;
    }

    private String rispostaRistoratore;

public String getRispostaRistoratore() {
    return rispostaRistoratore;
}

public void rispondi(String testo) {
    this.rispostaRistoratore = testo;
}

} 

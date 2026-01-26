package theknife;


public class Recensioni {
    private   String id ;
    private  String ristoranteid;
    private  String Autore;
    private int numeroStelle;
    private String commento;
    private Risposta risposta;
    protected  String rispostaAutore;
    protected  String rispostaTesto;
    private String ristostaristoratore;
    

 public Recensioni(String ristostaristoratore,String id, String ristoranteid, String Autore, int numeroStelle, String commento, String rispostaAutore, String rispostaTesto){
     this.id=id;
     this.ristoranteid=ristoranteid;
     this.Autore=Autore;
     this.numeroStelle=numeroStelle;
     this.commento=commento;
     this.rispostaAutore = rispostaAutore;
    this.ristostaristoratore=ristostaristoratore;
     this.rispostaTesto = rispostaTesto;
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
    public String getrispostaAutore()
     { 
        return rispostaAutore; 
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
    public String getRiristoratore(){
        return ristostaristoratore;
    }
public String getRispostaTesto() {
    return rispostaTesto;
}
} 

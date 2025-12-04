
package theknife;

public class TheKnife {
    public static void main(String[] args) {
        // Messaggio iniziale
        System.out.println("Benvenuto in The Knife!");

        //  Creazione del cliente (Carlo)
        Cliente cliente = new Cliente(
            "Carlo",            // nome
            "Studente",         // cognome
            "Busto Arsizio",    // domicilio
            "carlo@example.com",// mail
            "password123",      // password
            "carloUser",        // username
            "cliente"           // ruolo
        );

        // Creazione del ristoratore (Cicciogamer89)
        Ristoratore ristoratore = new Ristoratore(
            "Cicciogamer",        // nome
            "89",                 // cognome
            "ciccio@example.com", // mail
            "cicciogamer89",      // username
            "burgerPass",         // password
            "Roma",               // domicilio
            "ristoratore"         // ruolo
        );

        //creazione ristorante
        Ristorante ristorante = new Ristorante(
    "R001",                      // Id del ristorante
    "Burger di Cicciogamer89",   // nome
    "Italia",                    // nazione
    "Roma",                      // città
    "Via dei Panini 89",         // indirizzo
    41.9028,                     // latitudine
    12.4964,                     // longitudine
    3.0,                         // fasciaPrezzo (double)
    true,                        // delivery
    true,                        // prenotazioneOnline
    "Fast Food"                  // tipoCucina
);

    };

        //  Aggiunta del ristorante al ristoratore
        Ristoratore.aggiungiRistorante(ristorante);

        //  Stampa dei dati principali usando i getter
        System.out.println("\n=== Profilo cliente ===");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Cognome: " + cliente.getcognome());
        System.out.println("Domicilio: " + cliente.getdomicilio());
        System.out.println("Mail: " + cliente.getmail());
        System.out.println("Username: " + cliente.getusername());

        System.out.println("\n=== Profilo ristoratore ===");
        System.out.println("Nome: " + ristoratore.getNome());
        System.out.println("Cognome: " + ristoratore.getcognome());
        System.out.println("Domicilio: " + ristoratore.getdomicilio());
        System.out.println("Mail: " + ristoratore.getmail());
        System.out.println("Username: " + ristoratore.getusername());

        System.out.println("\n=== Ristorante: Burger di Cicciogamer89 ===");
        System.out.println("Nazione: " + ristorante.getNazione());
        System.out.println("Città: " + ristorante.getCitta());
        System.out.println("Indirizzo: " + ristorante.getIndirizzo());
        System.out.println("Latitudine: " + ristorante.getLatitudine());
        System.out.println("Longitudine: " + ristorante.getLongitudine());

        // Chiusura
        System.out.println("\nSetup completato. Pronto per aggiungere recensioni e risposte!");



}
}


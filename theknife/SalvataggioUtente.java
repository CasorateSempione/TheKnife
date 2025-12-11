package theknife;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SalvataggioUtente {
    private String file = "C:\\Users\\Utente\\Desktop\\TheKnife\\data\\utente.txt";

    private List<Utente> load() {
        List<Utente> lista = new ArrayList<>();
        try {
            if (!Files.exists(Paths.get(file))) return lista;

            for (String line : Files.readAllLines(Paths.get(file))) {
                if (line.isBlank()) continue;
                String[] t = line.split(";");

                if (t.length < 7) {
                    System.out.println("Riga malformata: " + line);
                    continue;
                }

                String ruolo = t[6].toLowerCase();
                Utente u;

                if (ruolo.equals("cliente")) {
                    u = new Cliente(t[0], t[1], t[2], t[3], t[4], t[5],t[6]);
                } else if (ruolo.equals("ristoratore")) {
                    u = new Ristoratore(t[0], t[1], t[2], t[3], t[4], t[5], t[6]);
                } else {
                    System.out.println("Ruolo sconosciuto: " + ruolo);
                    continue;
                }

                lista.add(u);
            }
        } catch (Exception e) {
            System.out.println("Errore caricando: " + e.getMessage());
        }
        return lista;
    }

    private void save(List<Utente> lista) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file))) {
            for (Utente u : lista) {
                writer.write(String.join(";",
                        u.getNome(),
                        u.getcognome(),
                        u.getdomicilio(),
                        u.getmail(),
                        u.getpassword(),
                        u.getusername(),
                        u.getruolo()
                        
                ));
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Errore salvando: " + e.getMessage());
        }
    }

    public void aggiungiUtente(Utente u) {
        List<Utente> lista = load();
        lista.add(u);
        save(lista);
    }

    public void rimuoviUtente(Utente u) {
        List<Utente> lista = load();
        lista.removeIf(existing -> existing.getusername().equals(u.getusername()));
        save(lista);
    }
    public List<Utente> getTutti() {
        return load();
    }
}



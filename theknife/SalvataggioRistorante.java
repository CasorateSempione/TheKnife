package theknife;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SalvataggioRistorante {
    private String file;
    private List<Ristorante> ristoranti = new ArrayList<>();

    public SalvataggioRistorante(String file) {
        this.file = file;
    }
     public void load() {
        ristoranti.clear();
        Ristorante current = null;
     try {
            if (!Files.exists(Paths.get(file))) return;

            for (String line : Files.readAllLines(Paths.get(file))) {
                if (line.isBlank()) continue;

                if (line.equals("[RISTORANTE]")) {
                    current = null;
                    continue;
                }

                if (line.equals("[RECENSIONE]")) {
                    continue;
                }

                // carico ristorante
                if (current == null) {
                    String[] t = line.split(";");
                    current = new Ristorante(
                            t[0], t[1], t[2], t[3], t[4],
                            Double.parseDouble(t[5]),
                            Double.parseDouble(t[6]),
                            Double.parseDouble(t[7]),
                            t[8].equals("si"),
                            t[9].equals("si"),
                            t[10]
                    );
                    ristoranti.add(current);
                } else {
                    // carico recensione
                    String[] t = line.split(";");
                    Recensioni rec = new Recensioni(
                            t[0], t[1], t[2],
                            Integer.parseInt(t[3]),
                            t[4],
                            t.length > 5 ? t[5] : "",
                            t.length > 6 ? t[6] : ""
                    );
                    current.addRecensione(rec);
                }
            }
        } catch (Exception e) {
            System.out.println("Errore caricando il file unico: " + e.getMessage());
        }
    }


    
}
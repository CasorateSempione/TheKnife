package theknife;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SalvataggioRistorante {

    private String file = "C:\\Users\\Danie\\Desktop\\TheKnife\\TheKnife-main\\theknife\\data\\ristorante.txt";

    private List<Ristorante> load() {
        List<Ristorante> lista = new ArrayList<>();
        Ristorante current = null;

        try {
            if (!Files.exists(Paths.get(file))) return lista;

            for (String line : Files.readAllLines(Paths.get(file))) {

                if (line.isBlank()) continue;

                if (line.equals("[RISTORANTE]")) {
                    current = null;
                    continue;
                }
                if (line.equals("[RECENSIONE]")) {
                    continue;
                }

                if (current == null) {
                    String[] t = line.split(";");
                    current = new Ristorante(
                            t[0], t[1], t[2], t[3],t[4],
                            Double.parseDouble(t[5]),
                            Double.parseDouble(t[6]),
                            Double.parseDouble(t[7]),
                            t[8].equals("si"),
                            t[9].equals("si"),
                            t[10]
                    );
                    lista.add(current);

                } else {
                    String[] t = line.split(";");
                    Recensioni rec = new Recensioni(
                            t[0], t[1], t[2],t[3],
                            Integer.parseInt(t[4]),
                            t[5],
                            t.length > 6 ? t[6] : "",
                            t.length > 7 ? t[7] : ""
                    );
                    current.addRecensione(rec);
                }
            }

        } catch (Exception e) {
            System.out.println("Errore caricando: " + e.getMessage());
        }

        return lista;
    }
    private void save(List<Ristorante> lista) {

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(file))) {

            for (Ristorante r : lista) {
                bw.write("[RISTORANTE]\n");
                bw.write(String.join(";",
                        r.getId(), 
                        r.getnome(),
                        r.getNazione(), 
                        r.getCitta(),
                        "" + r.getLatitudine(),
                        "" + r.getLongitudine(),
                        "" + r.getFasciaPrezzo(),
                        r.isDelivery() ? "si" : "no",
                        r.isPrenotazioneOnline()? "si" : "no",
                        r.getTipoCucina()
                ) + "\n");

                for (Recensioni rec : r.getRecensioni()) {
                     bw.write("[RECENSIONE]\n");
                    bw.write(String.join(";",
                            rec.getRiristoratore(),
                             rec.getId(),
                             rec.getRistoranteid(),
                            rec.getAutore(),
                            "" + rec.getNumeroStelle(),
                            rec.getCommento(),
                            rec.getrispostaAutore(),
                            rec.getRispostaTesto()
                    ) + "\n");
                }
            }

        } catch (Exception e) {
            System.out.println("Errore salvataggio: " + e.getMessage());
        }
    }
    public void aggiungiRistorante(Ristorante r) {
        List<Ristorante> lista = load();
        lista.add(r);
        save(lista);
    }

    public void aggiungiRecensione(String idRist, Recensioni rec) {
        List<Ristorante> lista = load();
        for (Ristorante r : lista) {
            if (r.getId().equals(idRist)) {
                r.addRecensione(rec);
                break;
            }
        }
        save(lista);
    }

    public void rimuoviRistorante(String id) {
        List<Ristorante> lista = load();
        lista.removeIf(r -> r.getId().equals(id));
        save(lista);
    }

    public List<Ristorante> getTutti() {
        return load();
    }
}
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import static java.lang.Integer.parseInt;

public class Tisch implements Serializable, Comparable<Tisch> {
    String bezeichnung;
    int plaetze;
    HashMap<LocalDate, Integer> reservierungen;

    public Tisch(String bezeichnung, int plaetze) throws TischExeption {
        reservierungen = new HashMap<>();
        setBezeichnung(bezeichnung);
        setPlaetze(plaetze);
        setReservierungen(reservierungen);
    }

    public void setBezeichnung(String bezeichnung) throws TischExeption{
        if (bezeichnung == null || bezeichnung.isEmpty()){
            throw new TischExeption("Die Bezeichnung darf nicht leer sein! setBezeichung() in Tisch");
        }
        this.bezeichnung = bezeichnung;
    }

    public void setPlaetze(int plaetze) throws TischExeption{
        if (plaetze < 1){
            throw new TischExeption("Es muss mindestens ein Platz frei sein setPlaetze() in Tisch");
        }
        this.plaetze = plaetze;
    }

    public void setReservierungen(HashMap<LocalDate, Integer> reservierungen) {
        this.reservierungen = reservierungen;
    }

    @SuppressWarnings("unused")
    public String getBezeichnung() {
        return bezeichnung;
    }
    @SuppressWarnings("unused")
    public int getPlaetze() {
        return plaetze;
    }
    @SuppressWarnings("unused")
    public HashMap<LocalDate, Integer> getReservierungen() {
        return reservierungen;
    }

    @Override
    public String toString() {
        return "Tisch{" +
                "bezeichnung='" + bezeichnung + '\'' +
                ", plaetze=" + plaetze +
                ", reservierungen=" + reservierungen +
                '}';
    }

    public String reserviere(LocalDate datum, Integer anz){
        if(datum.isBefore(LocalDate.now()) || anz > plaetze){
            return null;
        }
        reservierungen.put(datum, anz);
        return bezeichnung;
    }

    public boolean istReserviert(LocalDate datum){
        if (reservierungen.containsKey(datum)){
            return true;
        }
        return false;
    }

   public int storniere(LocalDate datum){
       int x = 0;

       if (datum == null) {
           for (LocalDate date : reservierungen.keySet()) {
               x = reservierungen.get(date).intValue();
               reservierungen.remove(date);
           }

           return x;
       }

       for (LocalDate date : reservierungen.keySet()) {
           if (date.getDayOfYear() != datum.getDayOfYear())
               continue;
           x += reservierungen.get(date).intValue();
           reservierungen.remove(date);
       }

       return x;
   }


    @Override
    public int compareTo(Tisch o) {
        return this.bezeichnung.compareTo(o.getBezeichnung());
    }
}

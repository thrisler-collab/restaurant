import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
@SuppressWarnings("unused")
public class Restaurant {
    ArrayList<Tisch> restaurant;
    @SuppressWarnings("unused")
    public Restaurant() {
        restaurant = new ArrayList<>();
    }

    public void aufstellen(Tisch tisch) throws TischExeption{
        for (Tisch t : restaurant){
            if (t.bezeichnung.equals(tisch.bezeichnung)){
                throw new TischExeption("Den Tisch gibt es bereits austellen() in Restaurant");
            }
        }
        restaurant.add(tisch);
    }

    public String reserviereTisch(LocalDate datum, Integer anz) throws TischExeption {
        for (Tisch tisch : restaurant){
            if(! tisch.istReserviert(datum)){
                if (tisch.plaetze > anz){
                    tisch.reserviere(datum, anz);
                    return tisch.bezeichnung;
                }
            }
        }
        return null;
    }

   public Integer storniereReservierung(String bez, LocalDate datum){
       int c = 0;
       for (Tisch tisch : restaurant){
           if (tisch.bezeichnung.equals(bez)){
               tisch.storniere(datum);
               c += tisch.reservierungen.get(tisch).intValue();
           }

       }
       return c;
   }

   public void nieBenutzteTischeEntfernen(){
       Iterator<Tisch> it = restaurant.iterator();
       while(it.hasNext()){

           Tisch t = it.next(); //Den nächste tisch aus dem Restaurant holen
           if(t.getReservierungen().isEmpty()){
               it.remove();//Tisch löschen mit ITERATOR!!!
           }
       }
   }

   public void ausgabeAllerReservierungen() throws IOException {
        ArrayList<Tisch> sortl = restaurant;
       Collections.sort(sortl);
       try(ObjectOutputStream ops = new ObjectOutputStream(new FileOutputStream("daten0.dat"))) {
          for (Tisch i : sortl){
              if (!i.reservierungen.isEmpty()){
                  ops.writeObject(i);
              }
          }
       }
   }

   public void speichereTisch() throws IOException{
       try(ObjectOutputStream ops = new ObjectOutputStream(new FileOutputStream("daten.dat"))) {
           ops.writeObject(restaurant);
       }
   }

   public void ladeTisch() throws IOException {
       try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream("daten.dat"))) {
           try {
               restaurant = (ArrayList<Tisch>) oos.readObject();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
   }

}

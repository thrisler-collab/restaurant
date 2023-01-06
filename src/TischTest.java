import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TischTest {

    Tisch t1;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        try{
            t1 = new Tisch("RunderTisch", 5);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Test ist vorbereiet");
    }

    @org.junit.jupiter.api.Test
    void reserviere() {
        assertEquals("RunderTisch", t1.reserviere(LocalDate.of(2023, 01, 16), 3));
    }

    //Die Methoden hänegn NiCHT zusammen das heißt wenn ein Tisch reserviert wird wie oben und man ihn
    //stornieren möchte muss man ihn vorher nochmals reservieren! Es wird nur jedes einzeln getestet und nicht zusammen!
    @org.junit.jupiter.api.Test
    void storniere() {
        t1.reserviere(LocalDate.of(2023, 01, 16), 3);
        assertEquals(3, t1.storniere(LocalDate.of(2023, 01, 16)));
    }

    @org.junit.jupiter.api.Test
    void storniere2(){
        t1.reserviere(LocalDate.of(2023, 01, 16), 3);
        assertEquals(0, t1.storniere(LocalDate.of(2023, 01, 14)));
    }
}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    Restaurant r1;
    @BeforeEach
    void setUp() {
        r1 = new Restaurant();
        System.out.println("Test ist vorbereitet");
    }

    @Test
    void aufstellen() {
        int groesse = r1.restaurant.size();
        Tisch t1 = new Tisch("EckigerTisch", 6);
        Tisch t2 = new Tisch("EckigerTisch1", 4);
        Tisch t3 = new Tisch("RunderTisch1", 7);
        Tisch t4 = new Tisch("RunderTisch2", 2);
        r1.aufstellen(t1);
        r1.aufstellen(t2);
        r1.aufstellen(t3);
        r1.aufstellen(t4);
        assertEquals(groesse + 4, r1.restaurant.size());
    }

    @Test
    void aufstellen2(){
        Tisch t1 = new Tisch("EckigerTischKK", 6);
        Tisch t2 = new Tisch("EckigerTischKK", 6);
        r1.aufstellen(t1);
        r1.aufstellen(t2); //FEHLERMELDUMG
    }
    @Test
    void reserviereTisch() {
        Tisch t1 = new Tisch("OvalerTisch", 6);
        r1.aufstellen(t1);
        assertEquals(t1.bezeichnung, r1.reserviereTisch(LocalDate.of(2023, 6, 16), 2));
    }

}
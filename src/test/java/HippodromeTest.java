import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    public void nullNameException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void listaVaciaException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));

        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void obtenerCaballos(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("caballo" + i,i,i));
           }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test

    public void mover() {
        List<Horse> horses = new ArrayList<>();

        for ( int i = 0; i < 50; i++ ){
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();

        for (Horse horse : horses){
            verify(horse).move();
        }

    }
    @Test

    public void getWinner(){
        Horse horse1 = new Horse("1",1,2.6);
        Horse horse2 = new Horse("1",1,3.2);
        Horse horse3 = new Horse("1",1,3.1);
        Horse horse4 = new Horse("1",1,2.8);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3,horse4));

        assertEquals(horse2, hippodrome.getWinner());


    }


}

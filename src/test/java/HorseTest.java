import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nullNameException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));

        assertEquals("Name cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "    ","\n\n\n\n", "\t\t\t\t"})
    public void blankNameException(String name){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));

        assertEquals("Name cannot be blank.", exception.getMessage());
    }
    @Test
    public void velocidadNegativoException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("nombre", -1, 1));

        assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    public void diatanciaNegativoException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("nombre", 1, -3));

        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    @Test
    public void obtenerNombre()  {
        String nombreDeCaballo = "Arabela";
        Horse horse = new Horse(nombreDeCaballo, 1, 1);

        assertEquals(nombreDeCaballo, horse.getName());

    }
    @Test
    public void obtenerVelocidad() {
        int velocidadDelCaballo = 1;
        Horse horse = new Horse("nombre", velocidadDelCaballo, 1);

        assertEquals( velocidadDelCaballo, horse.getSpeed());
    }
    @Test
    public void obtenerDistancia()  {
        int distanciaDelCaballo = 1;
        Horse horse = new Horse("nombre", 1, distanciaDelCaballo);

        assertEquals( distanciaDelCaballo, horse.getDistance());
    }
@Test
    public void obtenerCeroPorDefault(){
        Horse caballo = new Horse("arabela" , 1);

        assertEquals(0, caballo.getDistance());
    }
    @Test

    public void pruebaDeObetnerDobleAleatorioLlamadoEnElMetodoMover(){
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){

          new Horse("arabela",10,2).move();

          mockedStatic.verify(()-> Horse.getRandomDouble(0.2, 0.9));
        }

    }
    @ParameterizedTest
    @ValueSource(doubles = {1.0, 2.5, 999.214, 25.41, 0.0})
    public void move(double random){

        double speed = 20;
        double distance = 100;
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            Horse horse = new Horse("caballo" ,speed, distance);
            mockedStatic.when(()-> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(distance + speed * random, horse.getDistance());

        }

    }



}

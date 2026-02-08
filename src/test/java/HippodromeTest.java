import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

//    Проверить, что при передаче в конструктор null, будет выброшено IllegalArgumentException;
//    Проверить, что при передаче в конструктор null, выброшенное исключение будет содержать сообщение "Horses cannot be null.";
    @Test
    void constructor_whenHorsesIsNull_throwsIllegalArgumentException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(null));

        assertEquals("Horses cannot be null.", exception.getMessage());
    }

//    Проверить, что при передаче в конструктор пустого списка, будет выброшено IllegalArgumentException;
//    Проверить, что при передаче в конструктор пустого списка, выброшенное исключение будет содержать сообщение "Horses cannot be empty.";
    @Test
    void constructor_whenHorsesListIsEmpty_throwsIllegalArgumentException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(new ArrayList<>()));

        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

//    Проверить, что метод возвращает список, который содержит те же объекты и в той же последовательности, что и список который был передан в конструктор.
//    При создании объекта Hippodrome передай в конструктор список из 30 разных лошадей;
    @Test
    void getHorses_returnsSameListPassedInConstructor(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(" " +i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

//    Проверить, что метод вызывает метод move у всех лошадей.
//    При создании объекта Hippodrome передай в конструктор список из 50 моков лошадей и воспользуйся методом verify.
    @Test
    void move_invokesMoveMethodOnEachHorse(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();

        for(Horse horse : horses){
            verify(horse).move();
        }
    }

//    Проверить, что метод возвращает лошадь с самым большим значением distance.
        @Test
        void getWinner_returnsHorseWithMaxDistance(){
        Horse horse1 = new Horse("1",1,2.89);
        Horse horse2 = new Horse("2",2,1);
        Horse horse3 = new Horse("3",3,2);
        Horse horse4 = new Horse("4",2,4);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3,horse4));

        assertSame(horse4, hippodrome.getWinner());
    }
}

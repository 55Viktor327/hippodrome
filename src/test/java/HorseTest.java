import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    private String expectedName = "Рысак";
    private double expectedSpeed = 15;
    private double expectedDistance = 432;


    @Test
    void constructor_WhenNameIsNull_ThrowsExceptionWithCorrectMessage(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> new Horse(null,expectedSpeed,expectedDistance));

        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\t", "\n", "\r", "\t\n\r "})
    void constructor_WhenNameIsBlank_ThrowsIllegalArgumentException(String blankName){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> new Horse(blankName,expectedSpeed,expectedDistance));

        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructor_WhenSpeedIsNegative_ThrowsExceptionWithCorrectMessage(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> new Horse(expectedName, -1, expectedDistance));

        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructor_WhenDistanceIsNegative_ThrowsExceptionWithCorrectMessage(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> new Horse(expectedName, expectedSpeed, -3));

        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName_ShouldReturnNamePassedToConstructor() {
        Horse horse = new Horse(expectedName,expectedSpeed, expectedDistance);

        assertEquals(expectedName, horse.getName());
    }

    @Test
    void getSpeed_ShouldReturnSpeedPassedToConstructor() {
        Horse horse = new Horse(expectedName,expectedSpeed, expectedDistance);

        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    void getDistance_ShouldReturnDistancePassedToConstructor() {
        Horse horse = new Horse(expectedName,expectedSpeed, expectedDistance);

        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    void getDistance_ShouldReturnZeroDistanceByDefault(){
        Horse horse = new Horse(expectedName,expectedSpeed);

        assertEquals(0, horse.getDistance());
    }

    @Test
    void move_CallsGetRandomDoubleWithCorrectParameters() {
        System.out.println(">>> ТЕСТ НАЧАЛСЯ <<<");

        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9))
                    .thenReturn(0.5);

            Horse horse = new Horse("Буцефал", 10.0, 100.0);

            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }

        System.out.println(">>> ТЕСТ УСПЕШНО ЗАВЕРШЕН <<<");
    }

    @ParameterizedTest
    @CsvSource({
            "100, 50, 0.5, 100.0",   // distance = 50 + 100 * 0.5 = 100
            "80, 20, 0.3, 44.0",     // distance = 20 + 80 * 0.3 = 44
            "120, 0, 0.9, 108.0"     // distance = 0 + 120 * 0.9 = 108
    })
    void move_shouldCalculateDistanceCorrectly(
            double speed,
            double initialDistance,
            double mockedRandom,
            double expectedDistance) {

        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9))
                    .thenReturn(mockedRandom);

            Horse horse = new Horse("Буцефал", speed, initialDistance);

            horse.move();

            assertEquals(expectedDistance, horse.getDistance(), 0.001);
        }
    }
}

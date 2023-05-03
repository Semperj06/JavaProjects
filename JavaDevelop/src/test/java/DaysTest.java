import org.example.Days;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class DaysTest {

    @ParameterizedTest
    @MethodSource(value = "dataProvider")
    public void UnitTesting(Integer day, String expectedDayName) {
        if (day == null || expectedDayName == null) {
            assertThrows(NullPointerException.class, () -> {
                Days.getDay(day);
            });
        }
        else if (day < 1 || day > 7) {
            assertThrows(IllegalArgumentException.class, () -> {
                Days.getDay(day);
            });
        }

        else {
            String actualDayName = Days.getDay(day);
            assertEquals(expectedDayName, actualDayName);
        }
    }

    static Stream <Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(0, null),
                Arguments.of(8, null),
                Arguments.of(-1, null),
                Arguments.of(1, "Sunday"),
                Arguments.of(2, "Monday"),
                Arguments.of(3, "Tuesday"),
                Arguments.of(4, "Wednesday"),
                Arguments.of(5, "Thursday"),
                Arguments.of(6, "Friday"),
                Arguments.of(7, "Saturday")
        );
    }
    @ParameterizedTest
    @MethodSource(value = "dataProvider")
    public void UnitTesting(String FirstName, String LastName) {
        try {
            Logger.info("Name entered successfully");
            webDriver.findElement(By.name("//input[@id='search_form_input_homepage'")).sendKeys(FirstName);
        }
        catch (Exception err){
            Logger.error(err);
        }
        try {
            Logger.info("Last Name entered successfully");
            webDriver.findElement(By.name("//input[@name='lastname']")).sendKeys(LastName + Keys.ENTER);
        }
        catch (Exception err){
            Logger.error(err);
        }

    }


    static Stream <Arguments> dataProvider() {
        return Stream.of(Arguments.of("sdf", "null"));
    }
}
}


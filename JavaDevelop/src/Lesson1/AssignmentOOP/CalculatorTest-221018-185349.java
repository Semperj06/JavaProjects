import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {


    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before all method");
    }

    @BeforeEach
    public void beforeTest() {
        System.out.println("Before each method");

    }


    @AfterAll
    public static void afterAll() {
        System.out.println("After all method");

    }

    @AfterEach
    public void afterTest() {
        System.out.println("after each method");

    }


    @Test
    public void summaryTest() {
        double a = 5;
        double b = 6;
        double expectedResult = 11;

        double actualResult = Calculator.calculate(a, b, Calculator.Type.SUMMARY);

        assertEquals(expectedResult, actualResult, "result is incorrect");
    }

    @Test
    public void divisionTest() {
        double a = 10;
        double b = 2;
        double expectedResults = 5;

        double actualResult = Calculator.calculate(a, b, Calculator.Type.DIVISION);

        assertNotNull(actualResult, "result is null");
        assertEquals(expectedResults, actualResult);
        assertTrue(expectedResults == actualResult);
    }

    @Test
    @DisplayName("Nullponter error test")
    public void errorTest() {
        double a = 10;
        double b = 2;
        assertThrows(NullPointerException.class, () -> Calculator.calculate(a, b, null));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, 0, 27, -872299})
    public void summaryParamsTest(int a) {
        double b = 6;
        double expectedResults = b + a;
        double actualResult = Calculator.calculate(a, b, Calculator.Type.SUMMARY);

        assertEquals(expectedResults, actualResult);
    }

    @ParameterizedTest
    @EnumSource(Calculator.Type.class)
    public void typesParamsTest(Calculator.Type type) {
        double a = 6;
        double b = 2;

        double expectedResults = b + a;
        double actualResult = Calculator.calculate(a, b, type);

        assertNotNull(actualResult, "result is null");
        assertTrue(expectedResults > 0);
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void methodSourceParamsTest(int a, int b, double expectedResult, Calculator.Type type) {
        double actualResult = Calculator.calculate(a, b, type);

        assertEquals(expectedResult, actualResult);

    }

    static Stream <Arguments> dataProvider(){
        return Stream.of(
                Arguments.of(2, 2, 4, Calculator.Type.SUMMARY),
                Arguments.of(4, 2, 2, Calculator.Type.DIVISION),
                Arguments.of(2, 2, 4, Calculator.Type.MULTIPLICATION),
                Arguments.of(4, 2, 2, Calculator.Type.SUBTRACTION)
                );
    }

    @ParameterizedTest
    @CsvSource({"2, 2, 4, SUMMARY", "4, 2, 2, DIVISION"})
    public void csvParamsTest(int a, int b, double expectedResult, Calculator.Type type) {
        double actualResult = Calculator.calculate(a, b, type);
        assertEquals(expectedResult, actualResult);
    }



}


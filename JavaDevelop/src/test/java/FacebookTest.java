import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;


public class FacebookTest {
    private static final Logger Logger = LogManager.getLogger(FacebookTest.class);
    private static final String HOME_PAGE_URL = "https://www.facebook.com/";
    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        WebDriverManager.chromedriver().setup();
        driver = WebDrivers.getWebDriver();

        driver.get(HOME_PAGE_URL);
        driver.findElement(By.xpath("//*[text()='Создать новый аккаунт']")).click();
        Logger.info("The main page was run");
    }


    @AfterEach
    public void testTeardown() {
        driver.get(HOME_PAGE_URL);
        driver.findElement(By.xpath("//*[text()='Создать новый аккаунт']")).click();
    }

    @AfterAll
    public static void classTearDown() {
        WebDrivers.closeDriver();
        Logger.info("the program has ended");
    }

    public void pause() {
        try {
            Thread.sleep(1000);
        } catch (Exception err) {
            System.out.println("Something went wrong");
        }

    }

    @Test
    public void Months_drop_listTesting() {
        pause();
        Map<Integer, String> monthsMap = new HashMap<>();
        monthsMap.put(1, "янв");
        monthsMap.put(2, "фев");
        monthsMap.put(3, "мар");
        monthsMap.put(4, "апр");
        monthsMap.put(5, "мая");
        monthsMap.put(6, "июн");
        monthsMap.put(7, "июл");
        monthsMap.put(8, "авг");
        monthsMap.put(9, "сен");
        monthsMap.put(10, "окт");
        monthsMap.put(11, "ноя");
        monthsMap.put(12, "дек");
        for (int i = 1; i <= 12; i++) {
            String monthValue = monthsMap.get(i);
            driver.findElement(By.xpath("//select[@name='birthday_month']")).click();
            driver.findElement(By.xpath("//*[@data-name='birthday_wrapper']//descendant::*[@id='month']//descendant::option[@value="+i+"]")).click();
            try {
                Assertions.assertNotNull(driver.findElement(By.xpath("//*[contains(text(),'" + monthValue + "')]")));
                WebElement element = driver.findElement(By.xpath("//*[contains(text(),'" + monthValue + "')]"));
                Assertions.assertEquals(monthsMap.get(i), element.getText());
                Logger.info(monthValue+ " Has displayed");
            }
            catch (NoSuchElementException e){
                Logger.error(e);
            }

            }
    }
    @ParameterizedTest
    @ValueSource(ints = {1905, 1950, 2020})
    public void Years_drop_listTesting(int Year) {
        pause();
        driver.findElement(By.xpath("//select[@name='birthday_year']")).click();
        pause();
        driver.findElement(By.xpath("//*[@data-name='birthday_wrapper']//descendant::select[@name='birthday_year']//descendant::option[@value='" + Year + "']")).click();
        pause();
        try {
            WebElement element = driver.findElement(By.xpath("//*[contains(text(),'" + Year + "')]"));
            String elementText = element.getText();
            Assertions.assertEquals(Year, Integer.parseInt(elementText));
            Logger.info(Year + " Has displayed");
        } catch (NoSuchElementException e) {
            Logger.error(e);
        }
    }
    @ParameterizedTest
    @ValueSource(ints = {1, 2, -1})
    public void RadiobuttonTesting(int Value) {
        pause();
        driver.findElement(By.xpath("//*[@data-name='gender_wrapper']//descendant::input[@name='sex' and @value=" + Value + "]")).click();
        WebElement radioButton = driver.findElement(By.xpath("//*[@data-name='gender_wrapper']//descendant::input[@name='sex' and @value=" + Value + "]"));
        if(radioButton.isSelected()){
            Assertions.assertTrue(radioButton.isSelected());
            Logger.info("Radio button is selected");
        }
        else {
            Logger.error("Radio button is selected");
        }
    }

    @Test
    public void TermsTesting() {
        pause();
        String TermsLink = "https://www.facebook.com/legal/terms/update";
        driver.findElement(By.xpath("//a[@id='terms-link']")).click();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
        }
        String currentLink = driver.getCurrentUrl();
        Assertions.assertEquals(TermsLink, currentLink);
    }
    @Test
    public void DataPolicyTesting() {
        pause();
        String TermsLink = "https://www.facebook.com/privacy/policy/?entry_point=data_policy_redirect&entry=0";
        driver.findElement(By.xpath("//a[@id='privacy-link']")).click();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
        }
        String currentLink = driver.getCurrentUrl();
        Assertions.assertEquals(TermsLink, currentLink);

    }


    @ParameterizedTest
    @MethodSource(value = "dataProvider")
    public void errorMessageTest(String NameField, String FindError){
        pause();
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        driver.findElement(By.xpath("//input[@name='"+NameField+"']")).click();
        //pause();
        Assertions.assertNotNull(driver.findElement(By.xpath("//*[contains(text(),'" + FindError + "')]")));
        Logger.info("The error was displayed. Field "+ NameField + " was tested");
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of("lastname", "Как вас зовут"),
                Arguments.of("firstname", "Как вас зовут"),
                Arguments.of("reg_email__", "Они потребуются"),
                Arguments.of("reg_passwd__", "Введите не менее")
                );

    }


}
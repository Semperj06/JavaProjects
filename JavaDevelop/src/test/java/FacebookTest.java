import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class FacebookTest {
    private static final Logger Logger = LogManager.getLogger(FacebookTest.class);

    private static final String HOME_PAGE_URL = "https://www.facebook.com/";
    private static WebDriver webDriver;


    @BeforeEach
    public void createNewAccTest() {
        try {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
            webDriver.get(HOME_PAGE_URL);
            Logger.info("The page was open");
            webDriver.findElement(By.xpath("//*[text()='Создать новый аккаунт']")).click();
            Logger.info("Click on Создать новый аккаунт");
            Thread.sleep(1000);
            WebElement name = webDriver.findElement(By.xpath("//input[@name='firstname']"));
            assertNotNull(name);
            webDriver.findElement(By.xpath("//input[@value='-1']")).click();
            Logger.info("Gender Selected");
        } catch (InterruptedException err) {
            Logger.error(err);
        }
        try {
            WebElement dropdownElement = webDriver.findElement(By.xpath("//select[@name='birthday_year']"));
            Select dropdown = new Select(dropdownElement);
            dropdown.selectByValue("2000");
            Logger.info("Year was selected successfully");

            dropdownElement.isSelected();

        } catch (NoSuchElementException e) {
            Logger.error("Элемент не найден: " + e.getMessage());

        }
        try {
            WebElement dropdownElement = webDriver.findElement(By.xpath("//select[@name='preferred_pronoun']"));
            Select dropdown = new Select(dropdownElement);
            dropdown.selectByValue("1");
            Logger.info("Appeal was selected successfully");

            dropdownElement.isSelected();

        } catch (NoSuchElementException e) {
            Logger.error("Элемент не найден: " + e.getMessage());

        }
    }

    @AfterEach
    public void afterEachTest() throws InterruptedException {
        Thread.sleep(1000);
        webDriver.quit();
        Logger.info("The page was closed");
    }

    //    @BeforeAll
//    public static void createNewAccTest() {
//        try {
//            WebDriverManager.chromedriver().setup();
//            webDriver = new ChromeDriver();
//            webDriver.get(HOME_PAGE_URL);
//            Logger.info("The page was open");
//            webDriver.findElement(By.xpath("//*[text()='Создать новый аккаунт']")).click();
//            Logger.info("Click on Создать новый аккаунт");
//            Thread.sleep(1000);
//            WebElement name = webDriver.findElement(By.xpath("//input[@name='firstname']"));
//            assertNotNull(name);
//            webDriver.findElement(By.xpath("//input[@value='-1']")).click();
//            Logger.info("Gender Selected");
//        } catch (InterruptedException err) {
//            Logger.error(err);
//        }
//
//    }
    @AfterAll
    public static void classTearDown() {
        if (webDriver != null) {
            webDriver.quit();
            Logger.info("The page was closed");
        }

    }


//    @Test
//    public void findElement() {
//        WebElement Registration = webDriver.findElement(By.xpath("//button[@name='websubmit']"));
//        assertNotNull(Registration, "Contain Registration");
//        WebElement FirstName = webDriver.findElement(By.xpath("//input[@name='firstname']"));
//        assertNotNull(FirstName, "Contain FirstName");
//        WebElement LastName = webDriver.findElement(By.xpath("//input[@name='lastname']"));
//        assertNotNull(LastName, "Contain LastName");
//        WebElement Mail_Phone = webDriver.findElement(By.xpath("//input[@name='reg_email__']"));
//        assertNotNull(Mail_Phone, "Contain Mail_Phone");
//        WebElement NewPassword = webDriver.findElement(By.xpath("//input[@name='reg_passwd__']"));
//        assertNotNull(NewPassword, "Contain NewPassword");
//        WebElement BirthdayDay = webDriver.findElement(By.xpath("//select[@name='birthday_day']"));
//        assertNotNull(BirthdayDay, "Contain BirthdayDay");
//        WebElement BirthdayMonth = webDriver.findElement(By.xpath("//select[@name='birthday_month']"));
//        assertNotNull(BirthdayMonth, "Contain BirthdayMonth");
//        WebElement BirthdayYear = webDriver.findElement(By.xpath("//select[@name='birthday_year']"));
//        assertNotNull(BirthdayYear, "Contain BirthdayYear");
//        WebElement GenderWoman = webDriver.findElement(By.xpath("//input[@value='1']"));
//        assertNotNull(GenderWoman, "Contain GenderWoman");
//        WebElement GenderMan = webDriver.findElement(By.xpath("//input[@value='2']"));
//        assertNotNull(GenderMan, "Contain GenderMan");
//        WebElement GenderAnother = webDriver.findElement(By.xpath("//input[@value='-1']"));
//        assertNotNull(GenderAnother, "Contain GenderAnother");
//        WebElement PreferredPronoun = webDriver.findElement(By.xpath("//select[@name='preferred_pronoun']"));
//        assertNotNull(PreferredPronoun, "Contain PreferredPronoun");
//        WebElement CustomGender = webDriver.findElement(By.xpath("//input[@name='custom_gender']"));
//        assertNotNull(CustomGender, "Contain CustomGender");
//        Logger.info("The elements was found");
//
//    }

    @ParameterizedTest
    @MethodSource(value = "dataProvider")
    public void UnitTesting(String FirstName, String LastName, String Mail_Phone, String NewPassword, String CustomerGender) {
        try {
            webDriver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(FirstName);
            Logger.info("First Name entered in field - " + FirstName);
        } catch (IllegalArgumentException err) {
            Logger.error(err);

        }
        try {
            webDriver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(LastName);
            Logger.info("LastName entered in field - " + LastName);
        } catch (IllegalArgumentException err) {
            Logger.error(err);
        }
        try {
            webDriver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys(Mail_Phone);
            Thread.sleep(1000);
            Logger.info("Mail_Phone entered in field - " + Mail_Phone);

        } catch (IllegalArgumentException err) {
            Logger.error(err);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            webDriver.findElement(By.xpath("//input[@name='reg_passwd__']")).sendKeys(NewPassword);
            Logger.info("NewPassword  entered in field - " + NewPassword);
        } catch (IllegalArgumentException err) {
            Logger.error(err);
        }


        try {
            webDriver.findElement(By.xpath("//input[@name='custom_gender']")).sendKeys(CustomerGender);
            Logger.info("NewPassword  entered in field - " + CustomerGender);
        } catch (IllegalArgumentException err) {
            Logger.error(err);
        }

        WebElement RepeatEmail = webDriver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
        if (RepeatEmail.isDisplayed()) {
            RepeatEmail.sendKeys(Mail_Phone);
            Logger.info("The field was displayed");
        }

        webDriver.findElement(By.xpath("//button[@name='websubmit']")).click();
        Logger.info("Click on Registration Button");

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Logger.info("Waiting for page to load");
        String currentUrl = webDriver.getCurrentUrl();
        if (currentUrl.contains("confirmemail")) {
            assertNotEquals(HOME_PAGE_URL, currentUrl);
            Logger.info("***The registration was successful");
        }
        if (currentUrl.contains("checkpoint")) {
            assertNotEquals(HOME_PAGE_URL, currentUrl);
            Logger.info("***The registration was successful");
        } else {
            assertEquals(HOME_PAGE_URL, currentUrl);
            Logger.info("***The registration failed");

        }


    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of("null", "null", "null", "null", "null"),
                Arguments.of(" Иван", "Трифонов", "ruslan@gmail.com", "5435", "null"),
                Arguments.of("Devid", "Devid", "devid@gmail.com", "qwertyu76", "qwe"),
                Arguments.of("Милана ", null, "milana@gmail.com", "null54543", "null"),
                Arguments.of(null, "Ефремова", "milana@gmail.com", "null54543", "null"),
                Arguments.of("Милана", "Ефремова@", "milana@gmail.com", "qwertyu76", "null"),
                Arguments.of("Милана", "Ефремова", "milana@@gmail.com", "qwertyu76", "null"),
                Arguments.of("Милана", "Ефремова@", "@milana@gmail.com", "qwertyu76", "null"),
                Arguments.of("Милана@", "Ефремова", "milana@gmail.com", "qwertyu76", "null"),
                Arguments.of("Милана", "Ефремова@", "", "5435", "null"),
                Arguments.of("Милана", "Ефремова@", "milana@gmail.com", "Милана", "null"),
                Arguments.of("Милана", "Ефремова", "milana@gmail.com", "qwertyu76", "null"),
                Arguments.of("Милана", "Ефремова", "milana@gmail.com", "qwertyu76", "null"),
                Arguments.of("Милана", "Ефремова", "milana@gmail.com", "", "543362634"),
                Arguments.of("Милана", "Ефремова", "milana@gmail.com", null, "null"),
                Arguments.of("Милана", "Ефремова", "milana@gmail.com", "qwertyu76", null),
                Arguments.of("   ", "Милана", "milana@gmail.com", "qwertyu76", "qwe"),
                Arguments.of("Ефремова ", "     ", "milana@gmail.com", "qwertyu76", "qwe"),
                Arguments.of("Ефремова ", "Милана ", "milana@gmail.com", "qwertyu76", "qwe"),
                Arguments.of("Ефремова ", ">?<", "milana@gmail.com", "qwertyu76", "qwe"),
                Arguments.of("<>> ", "Милана ", "@@", "><?><?><>?<?><>?<>??>", "qwe"),
                Arguments.of("Ефремоваfdsfdsfdsfdsfdsfdssdfefwfwef ", "Милана ", "milana@gmail.com", "qwertyu76", "qwe"),
                Arguments.of("Ефремова ", "Милана ", "fsdfsdfsdafsadgsdgsdafsdfdgdsfasdafmilana@gmail.com", "qwertyu76", "qwe"),
                Arguments.of("Ефремова ", "Милана ", "milana@gmail.com", "qwertyu76", "fdsjhtisdjfidsjflidsjfioldsjfdsjfkdsjfkdsjfsdjklfqwe"),
                Arguments.of("Ефремова ", "Милана ", "milana@gmail.com", "qwertyu76sdflksdfmksdfmnsaflsdmfksdmfksdmfsdamfsdafnkasdnfgksdafnndaksfnsadkfndskafndksafmsdakfmlsadfmksdamfsadmfsdagnsdmkf", "qwe"),
                Arguments.of("پرسش های شما ", "Милана ", "milana@gmail.com", "qwertyu76", "qwe"),
                Arguments.of("Ефремова ", "چند روز قبل از پرواز، بلیط هواپیما را بخریم؟ ", "milana@gmail.com", "qwertyu76", "qwe"),
                Arguments.of("Ефремова ", "Милана ", "milana@gmail.com", "بلیط هواپیما", "qwe"),

                Arguments.of("Devid", "Devid", "weioe@gmail.com", "qwertyu76", "qwe")

        );

    }


}
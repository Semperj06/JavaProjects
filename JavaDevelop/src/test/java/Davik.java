
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;



public class Davik {
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(FacebookTest.class);
    private static final String HOME_PAGE_URL = "https://daviktapes.com/";
    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        WebDriverManager.chromedriver().setup();
        driver = WebDrivers.getWebDriver();
        driver.get(HOME_PAGE_URL);
        Logger.info("The main page was run");
    }

//    @AfterEach
//    public void testTeardown() {
//        driver.get(HOME_PAGE_URL);
//    }

    @AfterAll
    public static void classTearDown() {
        WebDrivers.closeDriver();
        Logger.info("the program has ended");
    }

    public void Drop_downTesting(String id) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Actions action = new Actions(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@id='menu-menu-1-en']//descendant::li[@id='"+id+"']")));
        WebElement element = driver.findElement(By.xpath("//ul[@id='menu-menu-1-en']//descendant::li[@id='"+id+"']"));
        action.moveToElement(element).build().perform();



    }

    @Test
    public void ContactTesting() {
        Logger.info("--Element Contact was selected");
        Drop_downTesting("menu-item-128");
    }
    @Test
    public void HomeTesting() {
        Logger.info("--Element Home was selected");
        Drop_downTesting("menu-item-4167");
    }
    @ParameterizedTest
    @ValueSource(strings = {"About us", "Our vision", "Our Team", "Quality", "R&D", "Sustainability", "Davik Ethical Code"})
    public void CompanyTesting(String ExpectElement) {
        Logger.info("--Element Company was selected");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Drop_downTesting("menu-item-2997");
        WebElement CurrentElement = driver.findElement(By.xpath("//*[contains(text(), '"+ExpectElement+"')]"));
        wait.until(ExpectedConditions.visibilityOf(CurrentElement));
        Assertions.assertEquals(ExpectElement, CurrentElement.getText());
        Logger.info("Element has displayed -- "+ExpectElement);
    }
    @ParameterizedTest
    @ValueSource(strings = {"Carry Handle Tape", "Resealable Finger Lift tape", "Splicing Tapes", "Pest Control",
            "White board sticker", "Low tack tapes", "Deep freezer tape", "Printable tapes", "Double sided tapes", "One Sided Tapes",
            "Developing Customized Products"})
    public void ProductsTesting(String ExpectElement) {
        Logger.info("--Element Products was selected");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Drop_downTesting("menu-item-134");
        WebElement CurrentElement = driver.findElement(By.xpath("//*[contains(text(), '"+ExpectElement+"')]"));
        wait.until(ExpectedConditions.visibilityOf(CurrentElement));
        Assertions.assertEquals(ExpectElement, CurrentElement.getText());
        Logger.info("Element has displayed -- "+ExpectElement);
    }
    @ParameterizedTest
    @ValueSource(strings = {"Food & Beverages", "Non Woven", "Agriculture", "Hygiene", "Retail", "Building & DIY"})
    public void IndustriesTesting(String ExpectElement) {
        Logger.info("--Element Industries was selected");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Drop_downTesting("menu-item-132");
        WebElement CurrentElement = driver.findElement(By.xpath("//*[contains(text(), '"+ExpectElement+"')]"));
        wait.until(ExpectedConditions.visibilityOf(CurrentElement));
        Assertions.assertEquals(ExpectElement, CurrentElement.getText());
        Logger.info("Element has displayed -- "+ExpectElement);
    }
    @ParameterizedTest
    @ValueSource(strings = {"Articles", "Events"})
    public void Knowledge_CenterTesting(String ExpectElement) {
        Logger.info("--Element Knowledge_Center was selected");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Drop_downTesting("menu-item-2552");
        WebElement CurrentElement = driver.findElement(By.xpath("//*[contains(text(), '"+ExpectElement+"')]"));
        wait.until(ExpectedConditions.visibilityOf(CurrentElement));
        Assertions.assertEquals(ExpectElement, CurrentElement.getText());
        Logger.info("Element has displayed -- "+ExpectElement);
    }
}

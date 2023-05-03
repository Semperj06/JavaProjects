import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class FacebookTest {
    private static final Logger Logger = LogManager.getLogger(FacebookTest.class);

    private static final String HOME_PAGE_URL = "https://www.facebook.com/";
    private static WebDriver webDriver;


    @BeforeEach
    public void createNewAccTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(HOME_PAGE_URL);
        Logger.info("The page was open");
        webDriver.findElement(By.xpath("//*[text()='Создать новый аккаунт']")).click();
//        Duration timeOut = Duration.ofSeconds(5);
//        WebDriverWait wait = new WebDriverWait(webDriver, timeOut);
//        WebElement name = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstname")));
        Thread.sleep(1000);
        WebElement name = webDriver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(name);
//        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//input[@value='-1']")).click();
    }

    @AfterEach
    public void classTearDown() {
        if (webDriver != null) {
            webDriver.close();
        }
    }


    @Test
    public void findElementsByXpathTest() {
        WebElement Registration = webDriver.findElement(By.xpath("//button[@name='websubmit']"));
        assertNotNull(Registration, "Contain Registration");
        WebElement FirstName = webDriver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(FirstName, "Contain FirstName");
        WebElement LastName = webDriver.findElement(By.xpath("//input[@name='lastname']"));
        assertNotNull(LastName, "Contain LastName");
        WebElement Mail_Phone = webDriver.findElement(By.xpath("//input[@name='reg_email__']"));
        assertNotNull(Mail_Phone, "Contain Mail_Phone");
        WebElement NewPassword = webDriver.findElement(By.xpath("//input[@name='reg_passwd__']"));
        assertNotNull(NewPassword, "Contain NewPassword");
        WebElement BirthdayDay = webDriver.findElement(By.xpath("//select[@name='birthday_day']"));
        assertNotNull(BirthdayDay, "Contain BirthdayDay");
        WebElement BirthdayMonth = webDriver.findElement(By.xpath("//select[@name='birthday_month']"));
        assertNotNull(BirthdayMonth, "Contain BirthdayMonth");
        WebElement BirthdayYear = webDriver.findElement(By.xpath("//select[@name='birthday_year']"));
        assertNotNull(BirthdayYear, "Contain BirthdayYear");
        WebElement GenderWoman = webDriver.findElement(By.xpath("//input[@value='1']"));
        assertNotNull(GenderWoman, "Contain GenderWoman");
        WebElement GenderMan = webDriver.findElement(By.xpath("//input[@value='2']"));
        assertNotNull(GenderMan, "Contain GenderMan");
        WebElement GenderAnother = webDriver.findElement(By.xpath("//input[@value='-1']"));
        assertNotNull(GenderAnother, "Contain GenderAnother");
        WebElement PreferredPronoun = webDriver.findElement(By.xpath("//select[@name='preferred_pronoun']"));
        assertNotNull(PreferredPronoun, "Contain PreferredPronoun");
        WebElement CustomGender = webDriver.findElement(By.xpath("//input[@name='custom_gender']"));
        assertNotNull(CustomGender, "Contain CustomGender");
    }

//    @Test
//    public void () {
//
//    }
//
}
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDrivers {
    private static WebDriver webDriver;

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // Добавьте это, если вы хотите, чтобы ChromeDriver работал в режиме "без графического интерфейса"
            webDriver = new ChromeDriver(options);
            webDriver.manage().window().maximize();
        }
        return webDriver;
    }

    public static void closeDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}

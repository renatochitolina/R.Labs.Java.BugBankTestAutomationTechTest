package framework.browser;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
    private static WebDriver driver;
    private static final TipoBrowser tipoBrowserDefault = TipoBrowser.CHROME;

    public static WebDriver obterDriver() {
        if (DriverManager.driver == null) {
            DriverManager.montarDriver();
        }
        return DriverManager.driver;
    }

    public static void finalizarDriver() {
        if (DriverManager.driver == null)
            return;

        DriverManager.driver.quit();

        DriverManager.driver = null;
    }

    public static void montarDriver() {
        montarDriver(tipoBrowserDefault);
    }

    public static void montarDriver(TipoBrowser tipoBrowser) {
        switch (tipoBrowser) {
            case CHROME:
                DriverManager.montarDriverChrome();
                break;
            case FIREFOX:
                DriverManager.montarDriverFirefox();
                break;
            case EDGE:
                DriverManager.montarDriverEdge();
                break;
            case HEADLESS:
            default:
                DriverManager.montarDriverHeadless();
        }
    }

    private static void montarDriverChrome() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions opcoes = new ChromeOptions()
                .addArguments("--start-maximized")
                .addArguments("--incognito")
                .addArguments("--remote-allow-origins=*");

        DriverManager.driver = new ChromeDriver(opcoes);
    }

    private static void montarDriverFirefox() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions opcoes = new FirefoxOptions()
                .addArguments("--start-maximized")
                .addArguments("--incognito")
                .addArguments("--remote-allow-origins=*");

        DriverManager.driver = new FirefoxDriver(opcoes);
    }

    private static void montarDriverEdge() {
        WebDriverManager.edgedriver().setup();

        EdgeOptions opcoes = new EdgeOptions()
                .addArguments("--start-maximized")
                .addArguments("--incognito")
                .addArguments("--remote-allow-origins=*");

        DriverManager.driver = new EdgeDriver(opcoes);
    }

    private static void montarDriverHeadless() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions opcoes = new ChromeOptions()
                .addArguments("--headless")
                .addArguments("--window-size(1366,768)")
                .addArguments("--remote-allow-origins=*");

        DriverManager.driver = new ChromeDriver(opcoes);
    }

    public static byte[] obterScreenShot() {
        return ((TakesScreenshot) DriverManager.driver).getScreenshotAs(OutputType.BYTES);
    }
}

package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBase {

    protected WebDriver driver;
    public static final int SLEEP_TIME = 3000;


    @BeforeTest
    public void start() {
        driver = new ChromeDriver();
    }

    @AfterTest
    public void stop() {
        driver.quit();
    }
}

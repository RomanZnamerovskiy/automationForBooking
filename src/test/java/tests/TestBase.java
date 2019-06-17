package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    public static final int SLEEP_TIME = 3000;


    @BeforeTest
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterTest
    public void stop() {
        driver.quit();
        driver = null;
    }
}

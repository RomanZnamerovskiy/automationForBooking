package tests;

import com.google.common.collect.Comparators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchResultsTest extends TestBase{

    @Test
    public void testSortingByPrice() {
        driver.get("https://www.booking.com/");
        driver.findElement(By.xpath("//*[@id='ss']")).sendKeys("Odessa" + Keys.ENTER);
        WebElement dateCheckin = driver.findElement(By.xpath("(//*[contains(@class, 'c2-wrapper-s-checkin')]//*[@class='c2-month'][2]//*[@class='c2-day-inner'])[1]"));
        dateCheckin.click();

        //find a list of properties for date field
        Map<String, Object> propertiesForDateField = (Map<String, Object>)((JavascriptExecutor) driver).executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", dateCheckin);
        List<String> listOfDateFieldProperties = propertiesForDateField.keySet().stream().collect(Collectors.toList());
        //find a list of properties for city field
        WebElement city = driver.findElement(By.xpath("//*[@id='ss']"));
        Map<String, Object> propertiesForCityField = (Map<String, Object>)((JavascriptExecutor) driver).executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", city);
        List<String> listOfCityFieldProperties = propertiesForCityField.keySet().stream().collect(Collectors.toList());

        //Sort the results by price
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath("//*[contains(@class, 'sort_price')]")).click();

        //Check that the sorting is correct

        //I understand that Thread.sleep is not the best way for waiting something.
        //I've tried another solution, but in this case I received the wrong price.
        //Therefore I've decided to live Thread.sleep, as a quick and working solution.
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> listOfPricesWithCurrency = driver.findElements(By.xpath("//*[@class='bui-price-display__value prco-inline-block-maker-helper ']"));
        List<Integer> listOfPrices = listOfPricesWithCurrency.stream()
                .map( elem -> Integer.parseInt(elem.getText().split(" ")[1]) )
                .collect(Collectors.toList());
        Comparators.isInOrder(listOfPrices, Comparator.naturalOrder());
    }


}

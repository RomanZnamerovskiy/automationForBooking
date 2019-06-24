package tests;

import Pages.HomePage;
import Pages.SearchResultsPage;
import com.google.common.collect.Comparators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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
        HomePage homePage = new HomePage(driver);

        homePage.open();
        SearchResultsPage searchResultsPage = homePage.search("Odessa");

//        TODO: need to realize this method: searchResultsPage.setCheckinDate("dateCheckin");
        WebElement dateCheckin = driver.findElement(By.xpath("(//*[contains(@class, 'c2-wrapper-s-checkin')]//*[@class='c2-month'][2]//*[@class='c2-day-inner'])[1]"));
        dateCheckin.click();

        //find a list of properties for date field
        Map<String, Object> propertiesForDateField = (Map<String, Object>)((JavascriptExecutor) driver).executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", dateCheckin);
        List<String> listOfDateFieldProperties = propertiesForDateField.keySet().stream().collect(Collectors.toList());
        //find a list of properties for city field
        Map<String, Object> propertiesForCityField = (Map<String, Object>)((JavascriptExecutor) driver).executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", searchResultsPage.placeInput);
        List<String> listOfCityFieldProperties = propertiesForCityField.keySet().stream().collect(Collectors.toList());

        searchResultsPage.submitSearch();
        searchResultsPage.sortByPrice();
        //TODO: change the below solution. As example use wait
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Check that the sorting is correct
        Assert.assertTrue(Comparators.isInOrder(searchResultsPage.getPrices(), Comparator.naturalOrder()));
    }

}

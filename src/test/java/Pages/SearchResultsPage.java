package Pages;

import com.google.common.collect.Comparators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends Page {

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public SearchResultsPage open() {
        driver.get("https://www.booking.com/searchresults.ru.html");
        return this;
    }

    @FindBy(xpath = "//*[@id='ss']")
    public WebElement placeInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitButton;

    @FindBy(xpath = "//*[contains(@class, 'sort_price')]")
    public WebElement sortByPriceButton;

    @FindBy(xpath = "//*[@class='bui-price-display__value prco-inline-block-maker-helper ']")
    public List<WebElement> listOfPricesWithCurrency;

    public SearchResultsPage enterPlace(String place) {
        placeInput.sendKeys(place);
        return this;
    }

    public SearchResultsPage submitSearch() {
        submitButton.click();
        return this;
    }

    public SearchResultsPage sortByPrice() {
        sortByPriceButton.click();
        return this;
    }

    public List<Integer> getPrices() {
        List<Integer> listOfPrices = listOfPricesWithCurrency.stream()
                .map( elem -> Integer.parseInt(elem.getText().split(" ")[1]) )
                .collect(Collectors.toList());
        return listOfPrices;
    }
}

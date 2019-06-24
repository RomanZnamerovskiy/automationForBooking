package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get("https://www.booking.com/");
        return this;
    }

    //TODO: some selectors are the same. Move them to one place
    @FindBy(xpath = "//*[@id='ss']")
    public WebElement placeInput;

    @FindBy(xpath = "//*[contains(@class, '-submit-button')]")
    public WebElement submitButton;

    public HomePage enterPlace(String place) {
        placeInput.sendKeys(place);
        return this;
    }


    public SearchResultsPage submitSearch() {
        submitButton.click();
        wait.until((WebDriver d) -> d.findElement(By.xpath("//*[@id='b2searchresultsPage']")));
        return new SearchResultsPage(driver);
    }

    //TODO: add dates and number of peoples
    public SearchResultsPage search(String place) {
        enterPlace(place);
        return submitSearch();
    }


}

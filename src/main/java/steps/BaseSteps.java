package steps;

import static drivermanager.CustomWebDriverManager.getDriverInstance;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.MainPage;
import pages.LoginPage;
import pages.components.AddFilterModal;
import pages.components.SideBarPage;
import rest.RestApiClient;
import utils.DataHolder;

public class BaseSteps {

    public static DataHolder dataHolder = new DataHolder();

    public LoginPage loginPage() {
        return new LoginPage();
    }

    public SideBarPage sideBarPage() {
        return new SideBarPage();
    }

    public MainPage mainPage() {
        return new MainPage();
    }

    public AddFilterModal filterModal() {
        return new AddFilterModal();
    }

    public RestApiClient apiClient() {
        return new RestApiClient();
    }

    public void clearAndThenFillInputField(WebElement element, String value) {
        while (!element.getAttribute("value").isEmpty()) {
            element.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        }
        element.sendKeys(value);
    }

    public void hover(WebElement element) {
        Actions action = new Actions(getDriverInstance());
        action.moveToElement(element).build().perform();
    }
}

package pages;

import static drivermanager.CustomWebDriverManager.getDriverInstance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class BasePage {

    public WebElement getElement(By elem) {
        return getDriverInstance().findElement(elem);
    }

    public List<WebElement> getElementList(By elem) {
        return getDriverInstance().findElements(elem);
    }
}

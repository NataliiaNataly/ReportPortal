package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

    public WebElement getNameInput() {
        return getElement(By.xpath("//div[contains(@class,'login-field')]//input"));
    }

    public WebElement getPasswordInput() {
        return getElement(By.xpath("//div[contains(@class,'type-password')]//input"));
    }

    public WebElement getSubmitButton() {
        return getElement(By.tagName("button"));
    }
}

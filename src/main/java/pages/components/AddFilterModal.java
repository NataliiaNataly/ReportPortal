package pages.components;

import static java.lang.String.format;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class AddFilterModal extends BasePage {

    public WebElement getModalWindow() {
        return getElement(By.cssSelector("[class*='modal-window']"));
    }

    public WebElement getNameInputOnModal() {
        return getModalWindow().findElement(By.cssSelector("[class*='input__input']"));
    }

    public WebElement getModalTitle() {
        return getElement(By.cssSelector("[class*='__modal-title']"));
    }

    public WebElement getModalBtnByName(String name) {
        return getElement(By.xpath(format("//button[text()='%s']", name)));
    }
}

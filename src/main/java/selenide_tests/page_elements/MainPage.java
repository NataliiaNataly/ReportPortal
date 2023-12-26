package selenide_tests.page_elements;

import static com.codeborne.selenide.Selenide.actions;
import static utils.SelenideElementHelper.findByXpath;
import static java.lang.String.format;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
public class MainPage {

    private SelenideElement getBtnByName(String btnName) {
        return findByXpath(format("//span[text()='%s']/parent::button", btnName));
    }

    private SelenideElement getNewLaunchNameInput() {
        return findByXpath("//div[contains(@class,'input-conditional')]/input");
    }

    public void clickOnButtonByName(String buttonName) {
        actions().moveToElement(getBtnByName(buttonName)).click().build().perform();
    }

    public void assignLaunchName(String launchName) {
        getNewLaunchNameInput().shouldBe(Condition.enabled).click();
        clearAndFillInFiledWithValue(getNewLaunchNameInput(), launchName);
    }

    public static void clearAndFillInFiledWithValue(SelenideElement element, String value) {
        while (!element.getAttribute("value").isEmpty()) {
            element.shouldBe(Condition.enabled).sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        }
        element.shouldBe(Condition.enabled).sendKeys(value);
    }
}

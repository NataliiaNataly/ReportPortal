package selenide_tests.page_elements;

import static org.testng.AssertJUnit.assertTrue;
import static utils.SelenideElementHelper.findByTag;
import static utils.SelenideElementHelper.findByXpath;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class LoginPage {

    private SelenideElement getInputField() {
        return findByXpath("//div[contains(@class,'login-field')]//input");
    }

    private SelenideElement getPasswordField() {
        return findByXpath("//div[contains(@class,'type-password')]//input");
    }

    private SelenideElement getSubmitButton() {
        return findByTag("button");
    }

    public void setLogin(String login) {
        getInputField().setValue(login);
    }

    public void setPassword(String password) {
        getPasswordField().setValue(password);
    }

    public void clickOnSubmitBtn() {
        getSubmitButton().shouldBe(Condition.enabled).click();
    }

    public void waitTillOauthPageVisible() {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.startsWith("https://login.microsoftonline.com/epam.com/oauth2"));
    }
}

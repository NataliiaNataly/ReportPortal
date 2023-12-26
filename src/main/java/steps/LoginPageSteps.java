package steps;

import static drivermanager.CustomWebDriverManager.getDriverInstance;
import static drivermanager.CustomWebDriverManager.waitForTime;
import static utils.TimeConstants.LONG_WAIT_MILLISECONDS;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Parameters;
import java.util.concurrent.TimeUnit;

public class LoginPageSteps extends BaseSteps {

    @Given("User opens the '$url' web site")
    public void openSite(String url) {
        getDriverInstance().get(url);
    }

    @When("User navigates back")
    public void navigateBack() {
        getDriverInstance().navigate().back();
    }

    @When("On Log In page, user clicks on the 'Login' button")
    public void clickOnLoginBtn() {
        loginPage().getSubmitButton().click();
        waitForTime(LONG_WAIT_MILLISECONDS, TimeUnit.MILLISECONDS);
    }

    @When("User logs in with the next data: $table")
    public void fillNameInput(ExamplesTable table) {
        Parameters parameters = table.getRowsAsParameters().get(0);
        String login = parameters.valueAs("login", String.class);
        String password = parameters.valueAs("password", String.class);

        loginPage().getNameInput().sendKeys(login);
        loginPage().getPasswordInput().sendKeys(password);
        loginPage().getSubmitButton().click();
        waitForTime(LONG_WAIT_MILLISECONDS, TimeUnit.MILLISECONDS);
    }
}

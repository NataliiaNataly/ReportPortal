package steps;

import static utils.StringUtils.getRandomStringFromFile;

import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebElement;
import utils.CustomLogger;

public class ModalSteps extends BaseSteps {

    private static final String FILE_NAME = "filterNaming";

    @When("On the 'ADD FILTER' modal, user fills in the name input field with random value and saves it to DataHolder")
    public void setFilterName() {
        String filterName = getRandomStringFromFile(FILE_NAME, 7);
        WebElement input = filterModal().getNameInputOnModal();
        clearAndThenFillInputField(input, filterName);
        unFocus();
        dataHolder.setName(filterName);
        CustomLogger.getLogger().info("The new filter name is: {}", dataHolder.getName());
    }

    @When("On the 'ADD FILTER' modal, user clicks on the '$button' button")
    public void clickOnButton(String button) {
        filterModal().getModalBtnByName(button).click();
    }

    private void unFocus() {
        filterModal().getModalTitle().click();
    }
}

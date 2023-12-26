package selenide_tests.page_elements;

import static com.codeborne.selenide.Selenide.actions;
import static selenide_tests.page_elements.MainPage.clearAndFillInFiledWithValue;
import static steps.CommonSteps.FILE_FOR_FILTER_NAMING;
import static utils.SelenideElementHelper.findByCss;
import static utils.SelenideElementHelper.findByXpath;
import static utils.StringUtils.getRandomStringFromFile;

import static java.lang.String.format;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import utils.DataHolder;

public class FilterModal {

    public static DataHolder dataHolder = new DataHolder();

    private SelenideElement getModalWindow() {
        return findByCss("[class*='modal-window']");
    }

    private SelenideElement getNameInputOnModal() {
        return getModalWindow().find("[class*='input__input']");
    }

    private SelenideElement getModalTitle() {
        return findByCss("[class*='__modal-title']");
    }

    private SelenideElement getModalBtnByName(String name) {
        return findByXpath(format("//button[text()='%s']", name));
    }

    public void fillInInputFieldOnModalWithRandomValueAndSaveIt() {
        String randomValue = getRandomStringFromFile(FILE_FOR_FILTER_NAMING, 5);
        clearAndFillInFiledWithValue(getNameInputOnModal(), randomValue);
        dataHolder.setName(randomValue);
        getModalTitle().shouldBe(Condition.visible, Condition.enabled).click();
    }

    public void clickOnBtnByName(String buttonName) {
        actions().moveToElement(getModalBtnByName(buttonName).shouldBe(Condition.enabled))
            .click().build().perform();
    }
}

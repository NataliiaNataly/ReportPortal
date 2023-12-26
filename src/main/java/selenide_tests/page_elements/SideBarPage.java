package selenide_tests.page_elements;

import static utils.SelenideElementHelper.findByXpath;

import static java.lang.String.format;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class SideBarPage {

    public SelenideElement getProjectsSelectorTab() {
        return findByXpath("//div[contains(@class,'sidebar__main-block')]/div");
    }

    public SelenideElement getProjectsSelectorDropdown(String project) {
        return findByXpath(format("//div[contains(@class,'projects-list')]//a/span[text()='%s']", project));
    }

    private SelenideElement getLaunchesTab() {
        return findByXpath("//div[contains(@class,'top-block')]/div[2]");
    }

    public void checkSideBarIsVisible() {
        getProjectsSelectorTab().shouldBe(Condition.visible);
    }

    public void clickOnLaunchesTab() {
        getLaunchesTab().click();
    }
}

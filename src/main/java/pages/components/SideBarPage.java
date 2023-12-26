package pages.components;

import static java.lang.String.format;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class SideBarPage extends BasePage {
    public WebElement getLaunchesTab() {
        return getElement(By.xpath("//div[contains(@class,'top-block')]/div[2]"));
    }

    public WebElement getProjectsSelectorTab() {
        return getElement(By.xpath("//div[contains(@class,'sidebar__main-block')]/div"));
    }

    public WebElement getProjectsSelectorDropdown(String project) {
        return getElement(By.xpath(format("//div[contains(@class,'projects-list')]//a/span[text()='%s']", project)));
    }

    public WebElement getUserIcon() {
        return getElement(By.cssSelector("[class*='userBlock__user-block']"));
    }

    public WebElement getUserAccountOptions(String option) {
        return getElement(By.xpath(format("//div[contains(@class,'userBlock__menu--')]" +
            "//*[text()='%s']", option)));
    }
}

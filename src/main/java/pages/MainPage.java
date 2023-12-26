package pages;

import static java.lang.String.format;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class MainPage extends BasePage {

    public WebElement getTableWithRuns() {
        return getElement(By.cssSelector("[class*='grid__grid']"));
    }

    public List<WebElement> getListOfRowsInTable() {
        return getTableWithRuns().findElements(By.xpath("./div[contains(@class,'gridRow')]"));
    }

    public WebElement getRowsNumberCounter() {
        return getElement(By.cssSelector("[class*='item-counter']"));
    }

    public WebElement getBtnByName(String btnName) {
        return getElement(By.xpath(format("//span[text()='%s']/parent::button", btnName)));
    }

    public WebElement getCellByColumnAndRow(int row, String columnTitle) {
        return getElementList(By.cssSelector("[class*='row-wrapper']"))
            .get(row).findElement(By.xpath(".//div[contains(@class,'__grid-row--')][1]"))
            .findElement(By.cssSelector(format("[class*='Grid__%s']", columnTitle)));
    }

    public WebElement getNameCellValue(int row, String columnTitle) {
        return getCellByColumnAndRow(row, columnTitle).findElement(By.cssSelector("[class*='itemInfo__main-info']"));
    }

    public WebElement getStartTestCellValue(int row) {
        return getElementList(By.cssSelector("[class*='row-wrapper']"))
            .get(row).findElement(By.cssSelector("[class*='_absolute-time-'"));
    }

    public WebElement getNewLaunchNameInput() {
        return getElement(By.xpath("//div[contains(@class,'input-conditional')]/input"));
    }

    public List<WebElement> getFilterList() {
        return getElementList(By.xpath("//div[contains(@class,'__filter-list')]//a"));
    }

    public WebElement getFilterByName(String filterName) {
        return getElement(By.xpath(format("//div[contains(@class,'__filter-list')]//a/span[text()='%s']", filterName)));
    }

    public WebElement getFilterDeleteIcon(String filterName) {
        return getElement(By.xpath(format("//span[text()='%s']/parent::a//div[contains(@class,'icon')]", filterName)));
    }
}

package utils;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class SelenideElementHelper {

    public static SelenideElement findByXpath(String xpath) {
        return $(By.xpath(xpath));
    }

    public static SelenideElement findByTag(String tag) {
        return $(By.tagName(tag));
    }

    public static SelenideElement findByCss(String css) {
        return $(By.cssSelector(css));
    }
}

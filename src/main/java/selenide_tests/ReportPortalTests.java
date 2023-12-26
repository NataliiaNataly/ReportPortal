package selenide_tests;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.ScreenShooter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import selenide_tests.page_elements.FilterModal;
import selenide_tests.page_elements.LoginPage;
import selenide_tests.page_elements.MainPage;
import selenide_tests.page_elements.SideBarPage;
import utils.dto.User;

@Listeners({ScreenShooter.class})
public class ReportPortalTests {

    private final LoginPage loginPage = new LoginPage();
    private final SideBarPage sideBarPage = new SideBarPage();
    private final MainPage mainPage = new MainPage();
    private final FilterModal filterModal = new FilterModal();
    private final User defaultUser = new User();
    private final static String WEB_SITE_URL = "https://rp.epam.com/ui/#login";
    private final static String DEFAULT_PROJECT_NAME = "defaultpersonaluser_personal";

    @BeforeTest
    public void takeScreenshot() {
        ScreenShooter.captureSuccessfulTests = true;
        Configuration.reportsFolder = "target/screenshots";
        login();
    }

    private void login() {
        open(WEB_SITE_URL);
        loginPage.setLogin(defaultUser.getLogin());
        loginPage.setPassword(defaultUser.getPassword());
        loginPage.clickOnSubmitBtn();
        loginPage.waitTillOauthPageVisible();
        back();
        loginPage.clickOnSubmitBtn();
    }

    @Test
    public void checkUserCanLogIn() {
        sideBarPage.checkSideBarIsVisible();
    }

    @Test
    public void checkUserCanAddFilter() {
        actions().moveToElement(sideBarPage.getProjectsSelectorTab()).click()
            .moveToElement(sideBarPage.getProjectsSelectorDropdown(DEFAULT_PROJECT_NAME)).click()
            .build().perform();
        sideBarPage.clickOnLaunchesTab();
        mainPage.clickOnButtonByName("Add filter");
        mainPage.assignLaunchName("random value");
        mainPage.clickOnButtonByName("Save");
        filterModal.fillInInputFieldOnModalWithRandomValueAndSaveIt();
        filterModal.clickOnBtnByName("Add");
        sideBarPage.checkSideBarIsVisible();
    }
}

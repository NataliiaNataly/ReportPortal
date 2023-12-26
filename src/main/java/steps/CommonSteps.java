package steps;

import static drivermanager.CustomWebDriverManager.waitForTime;
import static drivermanager.CustomWebDriverManager.waitUntilPageLoaded;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.TimeConstants.LONG_WAIT_MILLISECONDS;
import static utils.TimeConstants.SHORT_WAIT_MILLISECONDS;
import static utils.TimeConstants.WAIT_SHORT_SECONDS;
import static utils.StringUtils.getRandomStringFromFile;

import static java.lang.String.format;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Parameters;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import utils.CustomLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CommonSteps extends BaseSteps {

    public static final String FILE_FOR_FILTER_NAMING = "filterNaming";
    private static final String ADD_FILTER_BUTTON = "Add filter";
    private static final String SAVE_FILTER_BUTTON = "Save";
    private static final String ADD_BUTTON = "Add";
    private static final String PASSED_TESTS = "passed";
    private static final String TOTAL_TESTS = "total";
    private static final String FAILED_TESTS = "failed";
    private static final String SKIPPED_TESTS = "skipped";
    private static final String NAME = "name";
    private static final String START_TIME = "start";

    private static final String PARAMETRIZED_INFO_MESSAGE = "%s row '%s' cell value is: %s";

    @When("On Main RP page, user clicks on the 'LAUNCHES' tab")
    public void clickOnTab() {
        sideBarPage().getLaunchesTab().click();
    }

    @When("On Main RP page in sidebar, user clicks on the 'PROJECTS SELECTOR' tab")
    public void clickOnProjectSelectorTab() {
        sideBarPage().getProjectsSelectorTab().click();
    }

    @Then("On Main RP page in sidebar, user should see the 'PROJECTS SELECTOR' tab")
    public void getProjectSelectorTab() {
        assertTrue(sideBarPage().getProjectsSelectorTab().isDisplayed());
    }

    @When("User logs out")
    public void logOut() {
        sideBarPage().getUserIcon().click();
        sideBarPage().getUserAccountOptions("Logout").click();
    }

    @When("On Main RP page in Project Selector dropdown, user selects '$projectName' project")
    public void clickOnProjectSelectorTab(String projectName) {
        sideBarPage().getProjectsSelectorDropdown(projectName).click();
    }

    @Then("On Main RP page, user should see the table with runs")
    public void getTable() {
        assertTrue(mainPage().getTableWithRuns().isDisplayed());
    }

    @Then("On Main RP page, user verifies that the number of rows in the table is equal to value in counter")
    public void checkRunNumber() {
        List<WebElement> rows = mainPage().getListOfRowsInTable();
        int counter = Integer.parseInt(mainPage().getRowsNumberCounter().getText().split("of")[1].strip());
        Assert.assertEquals(counter, rows.size());
    }

    @Then("On Main RP page in 'LAUNCHES' results table, user should see the following data: $table")
    public void checkDataInResultsTable(ExamplesTable table) {
        for (int i = 0; i < table.getRows().size(); i++) {
            Parameters parameters = table.getRowsAsParameters().get(i);
            String name = parameters.valueAs(NAME, String.class);
            String testStart = parameters.valueAs(START_TIME, String.class);
            String totalTests = parameters.valueAs(TOTAL_TESTS, String.class);
            String passedTests = parameters.valueAs(PASSED_TESTS, String.class);
            String failedTests = parameters.valueAs(FAILED_TESTS, String.class);
            String skippedTests = parameters.valueAs(SKIPPED_TESTS, String.class);

            String actualName = mainPage().getNameCellValue(i, NAME).getText();
            CustomLogger.getLogger().info(format(PARAMETRIZED_INFO_MESSAGE, i + 1, NAME, actualName));
            assertEquals(name, actualName);

            hover(mainPage().getCellByColumnAndRow(i, START_TIME));
            waitForTime(LONG_WAIT_MILLISECONDS, TimeUnit.MILLISECONDS);
            String actualStartDate = mainPage().getStartTestCellValue(i).getText();
            CustomLogger.getLogger().info(format(PARAMETRIZED_INFO_MESSAGE, i + 1, START_TIME, actualStartDate));
            assertEquals(testStart, actualStartDate);

            String actualTotalCount = mainPage().getCellByColumnAndRow(i, TOTAL_TESTS).getText();
            CustomLogger.getLogger().info(format(PARAMETRIZED_INFO_MESSAGE, i + 1, TOTAL_TESTS, actualTotalCount));
            assertEquals(totalTests, actualTotalCount);

            String actualPassedCount = mainPage().getCellByColumnAndRow(i, PASSED_TESTS).getText();
            CustomLogger.getLogger().info(format(PARAMETRIZED_INFO_MESSAGE, i + 1, PASSED_TESTS, actualPassedCount));
            assertEquals(passedTests, actualPassedCount);

            String actualFailedCount = mainPage().getCellByColumnAndRow(i, FAILED_TESTS).getText();
            CustomLogger.getLogger().info(format(PARAMETRIZED_INFO_MESSAGE, i + 1, FAILED_TESTS, actualFailedCount));
            assertEquals(failedTests, actualFailedCount);

            String actualSkippedCount = mainPage().getCellByColumnAndRow(i, SKIPPED_TESTS).getText();
            CustomLogger.getLogger().info(format(PARAMETRIZED_INFO_MESSAGE, i + 1, SKIPPED_TESTS, actualSkippedCount));
            assertEquals(skippedTests, actualSkippedCount);
        }
    }

    @When("User creates '$number' new launch {filter|filters} and saves {their names|it} to DataHolder")
    public void createNewFilters(int number) {
        ArrayList<String> filters = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            clickOnAddFilerBtn(ADD_FILTER_BUTTON);
            enterValue(getRandomStringFromFile(FILE_FOR_FILTER_NAMING, 3));
            clickOnAddFilerBtn(SAVE_FILTER_BUTTON);

            String filterName = getRandomStringFromFile(FILE_FOR_FILTER_NAMING, 7);
            filters.add(filterName);

            WebElement input = filterModal().getNameInputOnModal();
            clearAndThenFillInputField(input, filterName);
            filterModal().getModalTitle().click();
            filterModal().getModalBtnByName(ADD_BUTTON).click();

            dataHolder.setListOfFilters(filters);
            CustomLogger.getLogger().info("The new filter name is: {}", dataHolder.getListOfFilters().get(i));
        }
    }

    @When("User deletes all newly created filters that were saved to DataHolder")
    public void deleteNewFilters() {
        waitForTime(WAIT_SHORT_SECONDS, TimeUnit.SECONDS);
        List<String> newFilters = dataHolder.getListOfFilters();
        deleteFilters(newFilters);
    }

    @When("User waits some time until page is loaded")
    public void waitUntilLoaded() {
        waitUntilPageLoaded();
    }

    @When("On the Main page, user deletes all filters from UI")
    public void deleteAllFilters() {
        Optional<List<String>> list = Optional.of(mainPage().getFilterList()
            .stream().map(WebElement::getText).collect(Collectors.toList()));
        if (!list.get().isEmpty()) {
            deleteFilters(list.get());
        }
    }

    private void deleteFilters(List<String> list) {
        CustomLogger.getLogger().info("The filters to be deleted: {}", list);
        for (String s : list) {
            String currentFilter = mainPage().getFilterByName(s).getText();
            try {
                mainPage().getFilterDeleteIcon(currentFilter);
            } catch (NoSuchElementException ex) {
                mainPage().getFilterByName(currentFilter).click();
            }
            mainPage().getFilterDeleteIcon(currentFilter).click();
            CustomLogger.getLogger().info("Filter with name '{}' has been deleted", currentFilter);
        }
    }

    @Then("User verifies that newly created and saved to DataHolder filters are all displayed on UI")
    public void checkNewFiltersDisplayed() {
        List<String> newFilters = dataHolder.getListOfFilters().stream().sorted().collect(Collectors.toList());
        CustomLogger.getLogger().info("Filters list from DataHolder is: {}", newFilters);
        waitForTime(WAIT_SHORT_SECONDS, TimeUnit.SECONDS);
        WebElement launchTabButton = sideBarPage().getLaunchesTab();
        if (!launchTabButton.isSelected()) {
            launchTabButton.click();
        }
        List<String> filtersFromUi = mainPage().getFilterList()
            .stream().map(WebElement::getText).sorted().collect(Collectors.toList());
        CustomLogger.getLogger().info("Filters list from UI is: {}", filtersFromUi);
        assertEquals(newFilters, filtersFromUi);
    }

    @When("On Main RP page, user clicks on the '$button' button")
    public void clickOnAddFilerBtn(String button) {
        mainPage().getBtnByName(button).click();
    }

    @When("On Main RP page in 'Launch Name' input, user enters the '$value' value")
    public void enterValue(String value) {
        WebElement input = mainPage().getNewLaunchNameInput();
        clearAndThenFillInputField(input, value);
    }

    @Then("On Main RP page on the header, user should see just created filter with name from DataHolder")
    public void getFilter() {
        String expectedFilterValue = dataHolder.getName();
        CustomLogger.getLogger().info("The expected filter name value is: {}", expectedFilterValue);
        waitForTime(SHORT_WAIT_MILLISECONDS, TimeUnit.MILLISECONDS);
        List<String> filterNames = mainPage().getFilterList().stream().map(WebElement::getText)
            .collect(Collectors.toList());
        assertTrue(filterNames.contains(expectedFilterValue));
    }

    @When("On Main RP page on the header, user deletes just created filter")
    public void deleteNewFilter() {
        mainPage().getFilterDeleteIcon(dataHolder.getName()).click();
    }
}

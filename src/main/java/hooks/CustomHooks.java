package hooks;

import static drivermanager.CustomWebDriverManager.closeDriver;
import static drivermanager.CustomWebDriverManager.getDriverInstance;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;

public class CustomHooks {

    @BeforeScenario
    public void initializeDriver() {
        getDriverInstance();
    }

    @AfterScenario
    public void tearDown() {
        closeDriver();
    }
}

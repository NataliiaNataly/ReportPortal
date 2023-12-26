package drivermanager;

import static utils.TimeConstants.WAIT_EXTREMELY_LONG_SECONDS;
import static utils.TimeConstants.WAIT_SECONDS;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CustomLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomWebDriverManager {

    private static WebDriver driver;

    public static WebDriver getDriverInstance() {
        if (driver == null) {
            CustomLogger.getLogger().info("*************************");
            CustomLogger.getLogger().info("Creating new WebDriver...");
            CustomLogger.getLogger().info("*************************");
            return createDriver();
        } else {
            return driver;
        }
    }

    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(WAIT_SECONDS, TimeUnit.SECONDS);
        return driver;
    }

    public static void waitForTime(int time, TimeUnit timeUnit) {
        driver.manage().timeouts().implicitlyWait(time, timeUnit);
    }

    public static void waitUntilPageLoaded() {
        WebDriverWait waiter =  new WebDriverWait(driver, Duration.ofSeconds(WAIT_EXTREMELY_LONG_SECONDS));
        waiter.until(
            webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    public static void closeDriver() {
        CustomLogger.getLogger().info("***********************************");
        CustomLogger.getLogger().info("Closing/Removing the current driver");
        CustomLogger.getLogger().info("***********************************");
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
        driver = null;
        try {
            if (isChromeProcessRunning()) {
                CustomLogger.getLogger().info("Killing .exe processes");
                killChromedriverExeProcess();
            }
        } catch (Exception error) {
            CustomLogger.getLogger().debug("Error occurred", error);
        }
    }

    private static void killChromedriverExeProcess() {
        String systemType = System.getProperty("os.name").toLowerCase();
        if (systemType.contains("win")) {
            try {
                Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
                CustomLogger.getLogger().info(".exe process is killed");
            } catch (IOException e) {
                CustomLogger.getLogger().error("Failed to close one or more driver .exe files");
            }
        }
    }

    private static boolean isChromeProcessRunning() throws Exception {
        Process process = Runtime.getRuntime().exec("tasklist");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        boolean processPunning = false;
        while ((line = reader.readLine()) != null) {
            if (line.contains("chromedriver.exe")) {
                processPunning = true;
            }
        }
        return processPunning;
    }
}


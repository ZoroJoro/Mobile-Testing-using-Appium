package test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    protected AndroidDriver driver;
    private String appPackage = "com.example.android.architecture"; // Update with your package

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();

        // Device capabilities
        options.setDeviceName("Pixel4");
        options.setPlatformVersion("13");

        // App - Update this to your actual APK path
        options.setApp("C:\\Users\\omar2\\Desktop\\java\\app-debug.apk");

        options.setAutoGrantPermissions(true);
        options.setNoReset(false); // Set to true if you want to keep app data between tests
        options.setFullReset(false); // Set to false to not reinstall app each time

        URL appiumServerUrl = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(appiumServerUrl, options);

        // Longer implicit wait for performance tests
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Grant all permissions upfront
        try {
            Thread.sleep(3000); // Wait for app to load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            // Optional: Take screenshot before quitting
            try {
                // driver.getScreenshotAs(...); // Uncomment if you want screenshots
            } catch (Exception e) {
                // Ignore screenshot errors
            }

            // Optional: Clear app data if needed for next test
            // driver.terminateApp(appPackage);

            driver.quit();
        }
    }

    // Helper method to bring app to foreground
    public void bringAppToForeground() {
        try {
            driver.activateApp(appPackage);
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Warning: Could not bring app to foreground: " + e.getMessage());
        }
    }
    // In BaseTest.java, add this helper method:
    protected void safeSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Sleep interrupted", e);
        }
    }
}
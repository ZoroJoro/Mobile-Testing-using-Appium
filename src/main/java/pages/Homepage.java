package pages;

import Utilities.Utility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import org.openqa.selenium.By;

public class Homepage {
    AndroidDriver driver;
    private final By newtaskbutton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(3)");
    private final By clickoncreaedtask = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(3)");
    private final By checkbox = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\")");
    private final By multiplecheckbox = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\").instance(1)");
    private final By completedtext = AppiumBy.androidUIAutomator("new UiSelector().text(\"Completed Tasks\")");
    private final By filter = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)");
    private final By filtercompleted = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(5)");


    public Homepage(AndroidDriver driver) {
        this.driver = driver;
    }
    public void clicknewtaskbutton(){
        Utility.clickElement(driver,newtaskbutton);
    }

    public void clickontask(){
        Utility.clickElement(driver,clickoncreaedtask);
    }
    public void clickcheckbox(){
        Utility.clickElement(driver,checkbox);
    }
    public void clickCheckboxInstance(int instanceNumber) {
        // Create dynamic locator for specific instance
        By checkboxLocator = AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.CheckBox\").instance(" + instanceNumber + ")"
        );
        Utility.clickElement(driver, checkboxLocator);
    }

    // NEW: Method to click the first checkbox (instance 0)
    public void clickFirstCheckbox() {
        clickCheckboxInstance(0);
    }

    // NEW: Method to click checkbox for a specific task by index
    public void clickCheckboxForTask(int taskIndex) {
        clickCheckboxInstance(taskIndex);
    }
    public void filtercompletedtasks(){
        Utility.clickElement(driver,filter);
        Utility.clickElement(driver,filtercompleted);
    }


}

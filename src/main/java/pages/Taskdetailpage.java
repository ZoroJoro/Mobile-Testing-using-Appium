package pages;

import Utilities.Utility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class Taskdetailpage {
    AndroidDriver driver;
    private final By editbutton = AppiumBy
            .androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(2)");
    private final By edittitle = AppiumBy
            .androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
    private final By confirmbutton = AppiumBy
            .androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)");
    private final By deletebutton = AppiumBy
            .androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)");

    public Taskdetailpage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void clickeditbutton() {
        Utility.clickElement(driver, editbutton);
    }

    public void editthetitle() {
        Utility.sendKey(driver, edittitle, "edited Buy Vegetables");
        Utility.clickElement(driver, confirmbutton);
    }

    public void clickdeletebutton() {
        Utility.clickElement(driver, deletebutton);
    }

}

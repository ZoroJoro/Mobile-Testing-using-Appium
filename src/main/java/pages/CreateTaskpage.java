package pages;

import Utilities.Utility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class CreateTaskpage {
    AndroidDriver driver;
    private final By titleinput = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
    private final By taskinput = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
    private final By confirmbutton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)");

    public CreateTaskpage(AndroidDriver driver) {
        this.driver = driver;
    }



   public void createtask(){
       Utility.sendKey(driver,titleinput,"Buy Groceries");
       Utility.sendKey(driver,taskinput,"Buy milk, eggs, and bread");
       Utility.clickElement(driver,confirmbutton);

   }
    public void createtask(String title, String description) {
        Utility.sendKey(driver, titleinput, title);
        Utility.sendKey(driver, taskinput, description);
        Utility.clickElement(driver, confirmbutton);
    }


}

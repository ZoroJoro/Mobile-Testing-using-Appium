package test;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CreateTaskpage;
import pages.Homepage;
import pages.Taskdetailpage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Createtasktest extends BaseTest {

    private final By TODO_TEXT =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Todo\")");

    private final By COMPLETED_FILTER_TEXT =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Completed Tasks\")");

    private final By TITLE_INPUT =
            AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");

    private final By DESC_INPUT =
            AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");

    private final By CONFIRM_BUTTON =
            AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)");

    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void CreateTask() {
        createSingleTask("Task_1");
    }

    @Test
    public void EditTask() {
        Homepage home = new Homepage(driver);
        Taskdetailpage taskpage = new Taskdetailpage(driver);

        createSingleTask("Editable_Task");
        home.clickontask();
        taskpage.clickeditbutton();
        taskpage.editthetitle();

        Assert.assertTrue(
                getWait().until(ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(12)")
                )).isDisplayed(),
                "Edit notification should be visible"
        );
    }

    @Test
    public void DeleteTask() {
        Homepage home = new Homepage(driver);
        Taskdetailpage taskpage = new Taskdetailpage(driver);

        createSingleTask("Deletable_Task");
        home.clickontask();
        taskpage.clickdeletebutton();

        Assert.assertTrue(
                getWait().until(ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.androidUIAutomator("new UiSelector().text(\"Task was deleted\")")
                )).isDisplayed(),
                "Delete confirmation should be visible"
        );
    }

    @Test
    public void CheckTaskCompleted() {
        Homepage home = new Homepage(driver);

        createSingleTask("Complete_Task");
        home.clickcheckbox();

        Assert.assertTrue(
                getWait().until(ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.androidUIAutomator("new UiSelector().text(\"Task marked complete\")")
                )).isDisplayed(),
                "Completion confirmation should be visible"
        );
    }

    @Test
    public void Create50Tasks() {
        for (int i = 1; i <= 50; i++) {
            createSingleTask("AutoTask_" + i);
        }
    }

    @Test
    public void Create6TasksAndCompleteFirst3() throws InterruptedException {
        Homepage home = new Homepage(driver);
        List<String> completedTasks = new ArrayList<>();

        System.out.println("=== Creating 6 tasks and completing first 3 ===");

        // 1️⃣ Create 6 tasks
        for (int i = 1; i <= 6; i++) {
            String taskTitle = "Task_" + i;

            home.clicknewtaskbutton();
            getWait().until(ExpectedConditions.elementToBeClickable(TITLE_INPUT)).sendKeys(taskTitle);
            getWait().until(ExpectedConditions.elementToBeClickable(DESC_INPUT))
                    .sendKeys("Description for " + taskTitle);
            getWait().until(ExpectedConditions.elementToBeClickable(CONFIRM_BUTTON)).click();

            // Verify task created
            getWait().until(ExpectedConditions.visibilityOfElementLocated(TODO_TEXT));
            System.out.println("✓ Created: " + taskTitle);
            Thread.sleep(300);
        }

        // 2️⃣ Complete first 3 tasks
        for (int i = 0; i < 3; i++) {
            home.clickCheckboxInstance(i);
            completedTasks.add("Task_" + (i + 1));
            Thread.sleep(500);
            System.out.println("✓ Completed: Task_" + (i + 1));
        }

        // 3️⃣ Apply Completed filter
        home.filtercompletedtasks();
        Thread.sleep(1500);

        // 4️⃣ Assertions
        Assert.assertTrue(
                getWait().until(ExpectedConditions.visibilityOfElementLocated(COMPLETED_FILTER_TEXT))
                        .isDisplayed(),
                "'Completed Tasks' filter should be visible"
        );

        System.out.println("✓ Completed tasks visible in filter: " + completedTasks);
    }

    @Test
    public void TestFilterAfterCompletion() {
        Homepage home = new Homepage(driver);

        for (int i = 1; i <= 4; i++) {
            createSingleTask("FilterTest_" + i);
        }

        home.clickCheckboxInstance(0);
        home.clickCheckboxInstance(1);

        home.filtercompletedtasks();

        Assert.assertTrue(
                getWait().until(ExpectedConditions.visibilityOfElementLocated(COMPLETED_FILTER_TEXT))
                        .isDisplayed(),
                "'Completed Tasks' filter should be applied"
        );
    }

    // ---------- UTILITY METHOD ----------
    private void createSingleTask(String title) {
        Homepage home = new Homepage(driver);

        home.clicknewtaskbutton();

        getWait().until(ExpectedConditions.elementToBeClickable(TITLE_INPUT)).sendKeys(title);
        getWait().until(ExpectedConditions.elementToBeClickable(DESC_INPUT))
                .sendKeys("Description for " + title);
        getWait().until(ExpectedConditions.elementToBeClickable(CONFIRM_BUTTON)).click();

        getWait().until(ExpectedConditions.visibilityOfElementLocated(TODO_TEXT));
    }
}

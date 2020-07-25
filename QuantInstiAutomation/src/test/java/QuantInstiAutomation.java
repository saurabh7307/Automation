import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class QuantInstiAutomation {

    public static WebDriver webDriver;
    String URL = "https://quantra.quantinsti.com/";
    String userEmail = "sau7307@gmail.com";
    String userPassword = "Quant@12345678";
    int WAIT = 10;
    ExtentHtmlReporter htmlReporter;
    ExtentReports report;
    public static ExtentTest testinfo;

    /************************ Few Utils Function **********************************************/
    public static void switchToNewTab(int tabIndex) {
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabIndex));
    }

    public static void closeCurrentTab() {
        webDriver.close();
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(0)); // switches to previous tab
    }

    // wait for the visibility of element till given time
    public static void waitForVisibilityOfElement(By path, int timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSecond);
        wait.until(ExpectedConditions.visibilityOfElementLocated(path));
    }

    // wait for the element to be clickable till given time
    public static void waitForElementToBeClickable(By path, int timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSecond);
        wait.until(ExpectedConditions.elementToBeClickable(path));
    }

    /************************ Automation **********************************************/

    @BeforeSuite
    public void setUp() {
        htmlReporter = new ExtentHtmlReporter("QuantInstiAutomation.html");
        // create ExtentReports and attach reporter(s)
        report = new ExtentReports();
        report.attachReporter(htmlReporter);
        // Set up chrome driver
        System.setProperty(
                "webDriver.chrome.driver",
                System.getProperty("user.dir") + "/chromedriver"); // Set Property
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
    }

    @BeforeMethod
    public void register(Method method) {
        String testName = method.getName();
        testinfo = report.createTest(testName);
    }

    @Test(priority = 1)
    public void openHomePage() {
        webDriver.get(URL);
    }

    @Test(priority = 2)
    public void userLogIn() throws InterruptedException {
        waitForElementToBeClickable(QuantInstiAutomationElements.USER_LOG_IN_BUTTON, WAIT);
        webDriver.findElement(QuantInstiAutomationElements.USER_LOG_IN_BUTTON).click();
        Thread.sleep(3000);
        waitForVisibilityOfElement(QuantInstiAutomationElements.LOG_IN_TEXT_ON_LOG_IN_PAGE, WAIT);
        webDriver
                .findElement(QuantInstiAutomationElements.EMAIL_ID_INPUT_FIELD)
                .sendKeys(userEmail);
        testinfo.log(Status.INFO, "User email : " + userEmail);
        webDriver
                .findElement(QuantInstiAutomationElements.PASSWORD_INPUT_FIELD)
                .sendKeys(userPassword);
        testinfo.log(Status.INFO, "User password : " + userPassword);
        webDriver.findElement(QuantInstiAutomationElements.LOGIN_BUTTON).click();
        Thread.sleep(1000);
    }

    @Test(priority = 3)
    public void browseCourseAndGetDetails() throws InterruptedException {
        waitForVisibilityOfElement(QuantInstiAutomationElements.BROWSE_COURSE_BUTTON, WAIT);
        webDriver.findElement(QuantInstiAutomationElements.BROWSE_COURSE_BUTTON).click();
        Thread.sleep(2000);
        waitForElementToBeClickable(QuantInstiAutomationElements.NEXT_ARROW, WAIT);
        webDriver.findElement(QuantInstiAutomationElements.NEXT_ARROW).click();
        Thread.sleep(500);
        waitForElementToBeClickable(QuantInstiAutomationElements.NEXT_ARROW, WAIT);
        webDriver.findElement(QuantInstiAutomationElements.NEXT_ARROW).click();
        waitForVisibilityOfElement(
                QuantInstiAutomationElements.SENTIMENT_ANALYSIS_IN_TRADING_COURSE, WAIT);
        webDriver
                .findElement(QuantInstiAutomationElements.SENTIMENT_ANALYSIS_IN_TRADING_COURSE)
                .click();
        waitForVisibilityOfElement(
                QuantInstiAutomationElements.SENTIMENT_ANALYSIS_IN_TRADING_COURSE_STRING, WAIT);
        String courseName =
                webDriver
                        .findElement(
                                QuantInstiAutomationElements
                                        .SENTIMENT_ANALYSIS_IN_TRADING_COURSE_STRING)
                        .getText();
        testinfo.log(Status.INFO, "Course Name : " + courseName);
        String courseOriginalPrice =
                webDriver
                        .findElement(QuantInstiAutomationElements.ORIGINAL_PRICE_OF_COURSE)
                        .getText();
        testinfo.log(Status.INFO, "Course original price : " + courseOriginalPrice);
        String courseOfferPrice =
                webDriver.findElement(QuantInstiAutomationElements.OFFER_PRICE_OF_COURSE).getText();
        testinfo.log(Status.INFO, "Course price during offer : " + courseOfferPrice);
    }

    @Test(priority = 4)
    public void enrollInCourse() throws InterruptedException {
        webDriver.findElement(QuantInstiAutomationElements.ENROLL_NOW_BUTTON).click();
        Thread.sleep(1000);
        int courseCount =
                webDriver
                        .findElements(QuantInstiAutomationElements.GET_DISPLAYING_COURSES_COUNT)
                        .size(); // get courses count
        testinfo.log(Status.INFO, "Displaying courses count : " + courseCount);
        Thread.sleep(1000);
        String countInCart =
                webDriver
                        .findElement(QuantInstiAutomationElements.ITEM_IN_CART)
                        .getText(); // get course count from the Cart
        testinfo.log(Status.INFO, "Courses in cart : " + countInCart);
        HashMap<String, String> courses = new HashMap<String, String>(); // Store Courses
        for (int i = 1; i <= courseCount; i++) {
            String getCourseNameElement =
                    String.format(QuantInstiAutomationElements.GET_COURSES_NAME, i);
            courses.put(
                    "course " + i,
                    webDriver
                            .findElement(By.xpath(getCourseNameElement))
                            .getText()); // course 1 will be a key of first course
            testinfo.log(
                    Status.INFO,
                    "course "
                            + i
                            + " : "
                            + webDriver.findElement(By.xpath(getCourseNameElement)).getText());
        }
        boolean flag;
        Thread.sleep(1000);
        flag = courseCount == Integer.parseInt(countInCart);
        Assert.assertTrue(flag);
    }

    @Test(priority = 5)
    public void captureBaseAmountAndAmountPayable() {
        String baseAmount =
                webDriver.findElement(QuantInstiAutomationElements.GET_BASE_AMOUNT).getText();
        testinfo.log(Status.INFO, "Course base amount : " + baseAmount);
        String payableAmount =
                webDriver.findElement(QuantInstiAutomationElements.GET_AMOUNT_PAYABLE).getText();
        testinfo.log(Status.INFO, "Courses payable amount : " + payableAmount);
    }

    @Test(priority = 6)
    public void openViewDetailsPageTab() {
        webDriver.findElement(QuantInstiAutomationElements.CLICK_ON_VIEW_DETAILS_OF_FIRST).click();
        switchToNewTab(1); // to switching new tab
        String netTabTitle = webDriver.getTitle();
        testinfo.log(Status.INFO, "New tab title : " + netTabTitle);
        System.out.println(netTabTitle);
        closeCurrentTab(); // closing current tab
    }

    @Test(priority = 7)
    public void removeCourseFromCart() {
        webDriver.findElement(QuantInstiAutomationElements.REMOVE_FIRST_COURSE).click();
        waitForVisibilityOfElement(
                QuantInstiAutomationElements.GET_ALERT_MESSAGE_AFTER_REMOVING_COURSE, WAIT);
        String alertMessage =
                webDriver
                        .findElement(
                                QuantInstiAutomationElements
                                        .GET_ALERT_MESSAGE_AFTER_REMOVING_COURSE)
                        .getText();
        testinfo.log(Status.INFO, "Alert message after removing course : " + alertMessage);
    }

    @Test(priority = 8)
    public void applyCouponAndGetErrorMessage() throws InterruptedException {
        webDriver.findElement(QuantInstiAutomationElements.APPLY_COUPON_BUTTON).click();
        waitForVisibilityOfElement(QuantInstiAutomationElements.COUPON_TITLE_STRING, WAIT);
        Thread.sleep(500);
        webDriver
                .findElement(QuantInstiAutomationElements.INPUT_FIELD_TO_ENTER_COUPON)
                .sendKeys("ABC");
        Thread.sleep(500);
        webDriver.findElement(QuantInstiAutomationElements.APPLY_BUTTON).click();
        waitForVisibilityOfElement(
                    QuantInstiAutomationElements.GET_WRONG_COUPON_ALERT_MESSAGE, WAIT);
        String wrongCouponAlertMessage =
                    webDriver
                            .findElement(
                                    QuantInstiAutomationElements.GET_WRONG_COUPON_ALERT_MESSAGE)
                            .getText();
        testinfo.log(
                    Status.INFO,
                    "Alert message after applying wrong coupon : " + wrongCouponAlertMessage);
    }

    @Test(priority = 9)
    public void closeCouponPopUp() {
        webDriver.findElement(QuantInstiAutomationElements.CLOSE_ICON_ON_COUPON_POP_UP).click();
        waitForVisibilityOfElement(QuantInstiAutomationElements.COURSE_IN_CART, WAIT);
        webDriver.findElement(QuantInstiAutomationElements.COURSE_IN_CART).isDisplayed();
    }

    @Test(priority = 10)
    public void signOut() {
        waitForVisibilityOfElement(QuantInstiAutomationElements.PROFILE_ICON, WAIT);
        webDriver.findElement(QuantInstiAutomationElements.PROFILE_ICON).click();
        waitForVisibilityOfElement(QuantInstiAutomationElements.LOGOUT, WAIT);
        webDriver.findElement(QuantInstiAutomationElements.LOGOUT).click();
        waitForVisibilityOfElement(QuantInstiAutomationElements.STRING_ON_HOME_PAGE, WAIT);
        webDriver.findElement(QuantInstiAutomationElements.STRING_ON_HOME_PAGE).isDisplayed();
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            testinfo.log(Status.FAIL, "Test Method name as : " + result.getName() + " is failed");
            testinfo.log(Status.FAIL, "Test failure :" + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            testinfo.log(Status.PASS, "Test Method name as : " + result.getName() + " is passed");
        }
    }

    @AfterSuite
    public void tearDown() {
        webDriver.quit();
        report.flush();
    }
}

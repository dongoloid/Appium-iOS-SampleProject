package test.java;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;

import java.net.URL;

public class Calendar {
    private static DesiredCapabilities capabilities;
    private static WebDriverWait wait;
    private static IOSDriver<MobileElement> driver;

    private static String validatedDate = "";
    private static String URL;

    @Given("User Open the Calendar")
    public void openApp() throws Exception {

        URL = "http://127.0.0.1:4723/wd/hub";
        capabilities = new DesiredCapabilities();

        capabilities.setCapability("udid", "5A52D195-0065-471D-BD57-F426E38DC5EF");
        capabilities.setCapability("xcodeOrgId", "69Y5G9P4NC");
        capabilities.setCapability("xcodeSigningId", "iPhone Developer");
        capabilities.setCapability("usePrebuiltWDA", "false");
        capabilities.setCapability("bundleId", "com.apple.mobilecal");
        capabilities.setCapability("deviceName", "iPhone Xr");

        capabilities.setCapability("platformVersion", "12.2");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("noReset", "false");
        capabilities.setCapability("wdaLocalPort", 8100);
        capabilities.setCapability("recordVideo", "true");
        capabilities.setCapability("recordScreenshots", "true");
        capabilities.setCapability("idleTimeout", 90);

        driver = new IOSDriver<MobileElement>(new URL(URL), capabilities);
        wait = new WebDriverWait(driver, 60);
    }

    @When("User Taps Date: $Date")
    public void clickDate(String Date){
        driver.findElementByXPath("//XCUIElementTypeButton[contains(@name,\"December " + Date + "\")]").click();

        validatedDate = Date;
    }

    @Then("User Checks Actual Date")
    public void validateDate(){
        Assert.assertTrue(driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Tuesday, December 15, 2020\"]").isDisplayed());
    }

    @Then("User Navigates Back To Home Screen")
    public void navigateToHomeScreen(){
        driver.navigate().back();

        Assert.assertTrue(driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"December 2020\"]").isDisplayed());
        Assert.assertTrue(driver.findElementByXPath("//XCUIElementTypeButton[contains(@name,\"December " + validatedDate + "\")]").getAttribute("name").contains("Tuesday"));
    }

}

package HW3.Ex2Steps;

import HW3.PageObjects.CommonElements;
import HW3.PageObjects.IndexPageElements;
import HW3.PageObjects.DifPageElements;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Ex2Steps {
    private WebDriver webDriver;
    private IndexPageElements indexPage;
    private CommonElements commonElements;
    private DifPageElements elementsPage;
    private Properties properties;
    private SoftAssert softAssert;

    @Before
    public void setup() throws IOException {
        Path resourceDirectory = Paths.get("src", "test", "resources", "test.properties");
        properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream(resourceDirectory.toFile()), StandardCharsets.UTF_8));
        softAssert = new SoftAssert();
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(properties.getProperty("URL"));
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void quit() {
        webDriver.quit();
    }


    @Given("I open test site by URL")
    public void openSite() {
        webDriver.get(properties.getProperty("URL"));
        indexPage = new IndexPageElements(webDriver);
        commonElements = new CommonElements(webDriver);
        elementsPage = new DifPageElements(webDriver);
    }

    @Then("Home page is opened")
    public void siteURLTest() {
        Assert.assertEquals(webDriver.getCurrentUrl(), properties.getProperty("URL"));
    }

    @Given("User log in as {string} with password {string}")
    public void login(String userName, String password) {
        indexPage.login(userName, password);
    }


    @Then("User logged in as {string}")
    public void userLoginTest(String expectedUserName) {
        Assert.assertEquals(indexPage.getUsername(), expectedUserName);
    }



    @Then("Browser title is {string}")
    public void browserTitleTest(String expectedTitle) {
        Assert.assertEquals(webDriver.getTitle(), expectedTitle);
    }

    @When("Header service is opened")
    public void clickOnServiceInTheHeader() {
        commonElements.getDropdown().click();
    }

    @Then("Header has {int} options")
    public void serviceSubcategoryElementsTest(int  count) {
        List<String> serviceElements = commonElements.getHeaderDropdownElements();
        Assert.assertEquals(serviceElements.size(), count);
        List<String> expectedTitles = Arrays.asList(properties.getProperty("menuOptions").split(","));
        for (int i = 0; i < serviceElements.size(); i++) {
            softAssert.assertEquals(serviceElements.get(i), expectedTitles.get(i));
        }
        softAssert.assertAll();
    }

    @When("Left Section is opened")
    public void clickOnServiceLeftSection() {
        commonElements.getLeftSectionDropdown().click();
    }

    @Then("Left Section has {int} options")
    public void thereAreElementsWithProperTextsIntTheLeftSection(int count) {
        List<String> serviceElements = commonElements.getLeftSectionDropdownElements();
        Assert.assertEquals(serviceElements.size(), count);
        List<String> expectedTitles = Arrays.asList(properties.getProperty("sideMenuOptions").split(","));
        for (int i = 0; i < serviceElements.size(); i++) {
            softAssert.assertEquals(serviceElements.get(i), expectedTitles.get(i));
        }
        softAssert.assertAll();
    }

    @When("Different Elements page is clicked")
    public void clickOnDifferentElementsPageInServiceSubcategory() {
        WebElement service = commonElements.getDropdown();
        service.click();
        WebElement toggle = commonElements.getDifferentElementsPage();
        toggle.click();
    }

    @Then("{string} page is opened")
    public void differentElementsPageOpenTest(String title) {
        Assert.assertEquals(webDriver.getTitle(), title);
    }

    @Then("There are {int} checkboxes")
    public void checkboxesCountTest(int count) {
        Assert.assertEquals(elementsPage.getCheckboxes().size(), count);
        for(WebElement we : elementsPage.getCheckboxes())
        {
            softAssert.assertTrue(we.isDisplayed(),"");
        }
        softAssert.assertAll();
    }

    @And("There are {int} radiobuttons")
    public void radiobuttonsAmountTest(int count) {
        Assert.assertEquals(elementsPage.getRadioButtons().size(), count);
        for(WebElement we : elementsPage.getRadioButtons())
        {
            softAssert.assertTrue(we.isDisplayed(), "");
        }
        softAssert.assertAll();
    }

    @And("There is a dropdown")
    public void dropdownTest() {
        Assert.assertTrue(elementsPage.getDropdown().isDisplayed());
    }

    @And("There are {int} buttons")
    public void buttonsAmountTest(int amount) {
        Assert.assertEquals(elementsPage.getButtons().size(), amount);
        for(WebElement we : elementsPage.getButtons())
        {
            softAssert.assertTrue(we.isDisplayed(),"");
        }
        softAssert.assertAll();
    }

    @And("There is the Right Section")
    public void rightSectionTest() {
        Assert.assertTrue(elementsPage.getLogSideBar().isDisplayed());
    }

    @And("There is the Left Section")
    public void leftSectionTest() {
        Assert.assertTrue(commonElements.getLeftSection().isDisplayed());
    }

    @When("Water and Wind checkboxes is clicked")
    public void selectWaterAndWindCheckboxes() {
        List<WebElement> checkboxes = elementsPage.getCheckboxes();
        WebElement water = checkboxes.get(0);
        WebElement wind = checkboxes.get(2);
        water.click();
        wind.click();
    }

    @Then("Checkboxes are selected")
    public void checkboxesSelectionTest() {
        List<WebElement> checkboxList = elementsPage.getCheckboxes();
        softAssert.assertTrue(checkboxList.get(0).isSelected(), "");
        softAssert.assertTrue(checkboxList.get(2).isSelected(), "");
        softAssert.assertAll();
    }

    @And("For each checkbox there is an individual log row and value is corresponded to the status of checkbox")
    public void checkboxesLogTest() {
        List<WebElement> logs = elementsPage.getLogs();
        softAssert.assertTrue(logs.get(0).isDisplayed(), "");
        softAssert.assertTrue(logs.get(1).isDisplayed(), "");
        String waterLog = logs.get(1).getText();
        String windLog = logs.get(0).getText();
        softAssert.assertTrue(waterLog.contains("Water") && (waterLog.contains("true")), "");
        softAssert.assertTrue(windLog.contains("Wind") && (windLog.contains("true")), "");
        softAssert.assertAll();
    }

    @When("Selen radiobutton is clicked")
    public void selenRadiobuttonSelection() {
        List<WebElement> radioList = elementsPage.getRadioButtons();
        WebElement selen = radioList.get(3);
        selen.click();
    }

    @Then("There is a log row and value is corresponded to the status of radiobutton")
    public void radiobuttonLogTest() {
        String radiobuttonLog = elementsPage.getLogs().get(0).getText();
        Assert.assertTrue(radiobuttonLog.contains("metal") && radiobuttonLog.contains("Selen"));
    }

    @When("Yellow in dropdown is clicked")
    public void dropdownOptionSelection() {
        elementsPage.getYellow().click();
    }

    @Then("There is a log row and value is corresponded to the selected value")
    public void dropdownLogTest() {
        String dropdownLog = elementsPage.getLogs().get(0).getText();
        Assert.assertTrue(dropdownLog.contains("Colors") && dropdownLog.contains("Yellow"));
    }


    @Then("Checkboxes are unselected")
    public void checkboxesUnselectionTest() {
        List<WebElement> checkboxList = elementsPage.getCheckboxes();
        softAssert.assertFalse(checkboxList.get(0).isSelected());
        softAssert.assertFalse(checkboxList.get(2).isSelected());
        softAssert.assertAll();
    }


    @And("For each checkbox there is an individual log row and value is corresponded to the unselected status of checkbox")
    public void checkboxesUnselectedLogTest() {
        List<WebElement> logs = elementsPage.getLogs();
        softAssert.assertTrue(logs.get(0).isDisplayed(), "");
        softAssert.assertTrue(logs.get(1).isDisplayed(), "");
        String waterLog = logs.get(1).getText();
        String windLog = logs.get(0).getText();
        softAssert.assertTrue(waterLog.contains("Water") && (waterLog.contains("false")), "");
        softAssert.assertTrue(windLog.contains("Wind") && (windLog.contains("false")), "");
        softAssert.assertAll();
    }
}
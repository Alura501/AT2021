package HW3.Ex1steps;

import HW3.PageObjects.CommonElements;
import HW3.PageObjects.IndexPageElements;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Ex1steps {
    private WebDriver webDriver;
    private IndexPageElements indexPage;
    private CommonElements commonElements;
    private Properties properties;

    @Before
    public void setup() throws IOException {
        Path resourceDirectory = Paths.get("src", "test", "resources", "test.properties");
        properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream(resourceDirectory.toFile()), StandardCharsets.UTF_8));
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

    @Then("Header section contains expected items")
    public void headerSectionTest() {
        ArrayList<String> menuElements = commonElements.getHeaderElementsTitles();
        List<String> serviceMenuExpected = Arrays.asList(properties.getProperty("headerElementsExpectedTexts").split(","));
        Assert.assertTrue(menuElements.containsAll(serviceMenuExpected));
        Assert.assertTrue(serviceMenuExpected.containsAll(menuElements));
    }


    @Then("Index page has {int} images")
    public void indexPageImagesTest(int count) {
        Assert.assertEquals(indexPage.getImagesList().size(), count);
    }

    @And("Images are displayed")
    public void indexPageImagesDisplayedTest() {
        for(WebElement we : indexPage.getImagesList())
        {
            Assert.assertTrue(we.isDisplayed());
        }
    }

    @Then("There are {int} texts under images")
    public void indexPageTextsTest(int amount) {
        Assert.assertEquals(indexPage.getTextList().size(), amount);
    }

    @And("They have proper text")
    public void indexPageTextsContentsTest() {
        List<String> indexPageExpectedTexts = Arrays.asList(properties.getProperty("underTexts").split(";"));
        List<WebElement> indexPageActualTexts = indexPage.getTextList();
        for(int i = 0; i < indexPageActualTexts.size(); i++)
        {
            Assert.assertEquals(indexPageActualTexts.get(i).getText().replaceAll("…", ""), indexPageExpectedTexts.get(i));
        }
    }

    @Then("Text equals to expected result")
    public void indexPageMainHeadersTextsTest() {
        Assert.assertEquals(indexPage.getText1().replaceAll("…", ""),
                            properties.getProperty("mainText1"));
        Assert.assertEquals(indexPage.getText2(), properties.getProperty("mainText2"));
    }

    @Then("Central iframe is displayed")
    public void centerIFrameIsDisplayed() {
        List<WebElement> imagesList = webDriver.findElements(By.tagName("iframe"));
        Assert.assertTrue(imagesList.get(0).isDisplayed());
    }

    @When("Focus is switched to the iframe")
    public void switchToTheIframe() {
        webDriver.switchTo().frame(properties.getProperty("frameSite"));
    }

    @Then("There is epam-logo")
    public void epamLogoTest() {
        Assert.assertFalse(indexPage.getLogo().isEmpty());
    }

    @And("Focus is switched to original window back")
    public void switchToOriginalWindowBack() {
        webDriver.switchTo().parentFrame();
        webDriver.switchTo().defaultContent();
    }

    @Then("Sub header text is {string}")
    public void subHeaderTextTest(String expectedText) {
        Assert.assertEquals(indexPage.getTextCenter().getText(), expectedText);
    }

    @And("Sub header text is a link {string}")
    public void subHeaderLinkTest(String URL) {
        Assert.assertEquals(indexPage.getTextCenter().getAttribute("href"), URL);
    }

    @Then("There is a Left Section")
    public void leftSectionTest() {
        Assert.assertTrue(commonElements.getLeftSection().isDisplayed());
    }

    @Then("There is a Footer")
    public void footerTest() {
        Assert.assertTrue(indexPage.getFooter().isDisplayed());
    }
}
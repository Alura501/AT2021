package hw2.ex2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Exercise2 {
    private WebDriver webDriver;


    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to("https://jdi-testing.github.io/jdi-light/index.html");
        webDriver.manage().window().maximize();
    }

    @AfterClass
    public void quit() {
        webDriver.quit();
    }

    @Test
    public void siteURLTest() {
        // 1. Open test site by URL
        Assert.assertEquals(webDriver.getCurrentUrl(),
                "https://jdi-testing.github.io/jdi-light/index.html");
    }

    @Test
    public void browserTitleTest() {
        // 2. Assert Browser title
        Assert.assertEquals(webDriver.getTitle(), "Home Page");
    }

    @Test
    public void userLoginTest() {
        // 3. Perform login
        webDriver.findElement(By.xpath("//*[@id='user-icon']")).click();
        webDriver.findElement(By.xpath("//*[@id='name']")).sendKeys("Roman");
        webDriver.findElement(By.xpath("//*[@id='password']")).sendKeys("Jdi1234");
        webDriver.findElement(By.xpath("//*[@id='login-button']")).click();

    }

    @Test(dependsOnMethods = {"userLoginTest"})
    public void LogginedTest() {
        // 4. Assert User name in the left-top side of screen that user is loggined
        String curUsername = webDriver.findElement(By.xpath("//*[@id='user-name']")).getAttribute("innerHTML");
        Assert.assertEquals(curUsername, "Roman Iovlev");
    }

    @Test
    public void headerServiceTest() {
        // 5. Click on "Service" subcategory in the header and check that drop down contains options

        String[] expectedMenuOptions = {
                "SUPPORT",
                "DATES",
                "SEARCH",
                "COMPLEX TABLE",
                "SIMPLE TABLE",
                "USER TABLE",
                "TABLE WITH PAGES",
                "DIFFERENT ELEMENTS",
                "PERFORMANCE"
        };

        webDriver.findElement(By.className("dropdown-toggle")).click();
        List<WebElement> options = webDriver.findElements(By.xpath("//*[@class='dropdown-menu']/li"));
       
        Assert.assertEquals(options.size(), expectedMenuOptions.length);

        SoftAssert softAssert = new SoftAssert();
        for(int i = 0; i < options.size(); i++) {
            WebElement option = options.get(i).findElement(By.tagName("a"));
            softAssert.assertTrue(option.isDisplayed());
            softAssert.assertEquals(option.getText(), expectedMenuOptions[i]);
        }

        softAssert.assertAll();
    }

    @Test
    public void leftSectionServiceTest() {
        // 6. Click on Service subcategory in the left section and check
        // that drop down contains options

        String[] expectedSideMenuOptions = {
                "Support",
                "Dates",
                "Complex Table",
                "Simple Table",
                "Search",
                "User Table",
                "Table with pages",
                "Different elements",
                "Performance"
        };

        webDriver.findElement(By.className("menu-title")).click();
        List<WebElement> options = webDriver.findElements(By.xpath("//*[@class='sub']/li"));
       

        Assert.assertEquals(options.size(), expectedSideMenuOptions.length);

        SoftAssert softAssert = new SoftAssert();
        for(int i = 0; i < options.size(); i++) {
            WebElement option = options.get(i).findElement(By.tagName("span"));
            softAssert.assertTrue(option.isDisplayed());
            softAssert.assertEquals(option.getText(), expectedSideMenuOptions[i]);
        }

        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"LogginedTest", "headerServiceTest"})
    public void DifElementsTest() {
        // 7. Open through the header menu Service -> Different Elements Page
        WebElement dropdown = webDriver.findElement(By.xpath("//div[starts-with(@class, uui-header)]//li[@class='dropdown']"));
        dropdown.click();
        dropdown.findElement(By.xpath("//a[text()='Different elements']")).click();

        // 8. Check interface on Different elements page, it contains all needed elements
        SoftAssert softAssert = new SoftAssert();

        List<WebElement> checkboxes = webDriver.findElements(By.xpath("//*[@type='checkbox']"));
        softAssert.assertEquals(checkboxes.size(), 4);
        for(WebElement we : checkboxes) {
            softAssert.assertTrue(we.isDisplayed());
        }

        List<WebElement> radiobuttons = webDriver.findElements(By.xpath("//*[@type='radio']"));
        softAssert.assertEquals(radiobuttons.size(), 4);
        for(WebElement we : radiobuttons) {
            softAssert.assertTrue(we.isDisplayed());
        }

        softAssert.assertTrue(webDriver.findElement(By.xpath("//*[@class='colors']/select")).isDisplayed());
        softAssert.assertTrue(webDriver.findElement(By.xpath("//*[@name='Default Button']")).isDisplayed());
        softAssert.assertTrue(webDriver.findElement(By.xpath("//*[@value='Button']")).isDisplayed());

        // 9. Assert that there is Right Section
        Assert.assertTrue(webDriver.findElement(By.xpath("//*[@name='navigation-sidebar']")).isDisplayed());

        // 10. Assert that there is Left Section
        Assert.assertTrue(webDriver.findElement(By.xpath("//*[@name='log-sidebar']")).isDisplayed());
        softAssert.assertAll();
    }


    @Test(dependsOnMethods = {"DifElementsTest"})
    public void checkboxesTest() {
        // 11. Select checkboxes
        WebElement waterCheckbox = webDriver.findElement(By.xpath("//*[@class='checkbox-row'][1]/label[1]/input"));
        waterCheckbox.click();

        WebElement windCheckbox = webDriver.findElement(By.xpath("//*[@class='checkbox-row'][1]/label[3]/input"));
        windCheckbox.click();

        // 12. Assert that for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox.
        WebElement waterCheckboxLog = webDriver.findElement(By.xpath("//*[starts-with(@class, 'panel-body-list')]/li[2]"));
        String waterCheckboxLogStatus = waterCheckboxLog.getText();

        WebElement windCheckboxLog = webDriver.findElement(By.xpath("//*[starts-with(@class, 'panel-body-list')]/li[1]"));
        String windCheckboxLogStatus = windCheckboxLog.getText();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(waterCheckbox.isSelected());
        softAssert.assertTrue(waterCheckboxLog.isDisplayed());
        softAssert.assertTrue(waterCheckboxLogStatus.contains("Water"));
        softAssert.assertTrue(waterCheckboxLogStatus.contains("true"));

        softAssert.assertTrue(windCheckbox.isSelected());
        softAssert.assertTrue(windCheckboxLog.isDisplayed());
        softAssert.assertTrue(windCheckboxLogStatus.contains("Wind"));
        softAssert.assertTrue(windCheckboxLogStatus.contains("true"));

        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"DifElementsTest"})
    public void radioTest() {
        // 13. Select radio
        WebElement selenRadiobutton = webDriver.findElement(By.xpath("//*[@class='checkbox-row'][2]/label[4]/input"));
        selenRadiobutton.click();

        // 14. Assert that for radiobutton there is a log row
        // and value is corresponded to the status of radiobutton.
        WebElement selenRadiobuttonLog = webDriver.findElement(By.xpath("//*[starts-with(@class, 'panel-body-list')]/li[1]"));
        String selenRadiobuttonLogStatus = selenRadiobuttonLog.getText();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(selenRadiobutton.isSelected());
        softAssert.assertTrue(selenRadiobuttonLog.isDisplayed());
        softAssert.assertTrue(selenRadiobuttonLogStatus.contains("metal"));
        softAssert.assertTrue(selenRadiobuttonLogStatus.contains("Selen"));
    }

    @Test(dependsOnMethods = {"DifElementsTest"})
    public void dropdownTest() {
        // 15. Select in dropdown
        WebElement dropdown = webDriver.findElement(By.xpath("//*[@class='colors']"));
        dropdown.click();

        WebElement optionYellow = dropdown.findElement(By.xpath(".//*[text()='Yellow']"));
        optionYellow.click();

        // 16. Assert that for dropdown there is a log row
        // and value is corresponded to the selected value.
        WebElement optionYellowLog = webDriver.findElement(By.xpath("//*[starts-with(@class, 'panel-body-list')]/li[1]"));
        String optionYellowLogStatus = optionYellowLog.getText();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(optionYellow.isSelected());
        softAssert.assertTrue(optionYellowLog.isDisplayed());
        softAssert.assertTrue(optionYellowLogStatus.contains("Colors"));
        softAssert.assertTrue(optionYellowLogStatus.contains("Yellow"));

        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"checkboxesTest"})
    public void unselectCheckboxesTest() {
        // 17. Unselect and assert checkboxes

        WebElement waterCheckbox = webDriver.findElement(By.xpath("//*[@class='checkbox-row'][1]/label[1]/input"));
        WebElement windCheckbox = webDriver.findElement(By.xpath("//*[@class='checkbox-row'][1]/label[3]/input"));

        waterCheckbox.click();
        windCheckbox.click();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertFalse(waterCheckbox.isSelected());
        softAssert.assertFalse(windCheckbox.isSelected());

        // 18. Assert that for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox.

        WebElement waterCheckboxLog = webDriver.findElement(By.xpath("//*[starts-with(@class, 'panel-body-list')]/li[2]"));
        WebElement windCheckboxLog = webDriver.findElement(By.xpath("//*[starts-with(@class, 'panel-body-list')]/li[1]"));

        String waterCheckboxLogStatus = waterCheckboxLog.getText();
        String windCheckboxLogStatus = windCheckboxLog.getText();


        softAssert.assertTrue(waterCheckboxLog.isDisplayed());
        softAssert.assertTrue(waterCheckboxLogStatus.contains("Water"));
        softAssert.assertTrue(waterCheckboxLogStatus.contains("false"));

        softAssert.assertTrue(windCheckboxLog.isDisplayed());
        softAssert.assertTrue(windCheckboxLogStatus.contains("Wind"));
        softAssert.assertTrue(windCheckboxLogStatus.contains("false"));

        softAssert.assertAll();
    }

}
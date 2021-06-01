package HW3.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DifPageElements {

    @FindBy(xpath = "//*[contains(@type,'checkbox')]")
    List<WebElement> checkboxes;
    @FindBy(xpath = "//*[contains(@type,'radio')]")
    List<WebElement> radioButtons;
    @FindBy(xpath = "//*[@class='colors']")
    WebElement colorsDropdown;
    @FindBy(xpath = "//*[@class='uui-button']")
    List<WebElement> buttons;
    @FindBy(xpath = "//*[@name='log-sidebar']")
    WebElement logSideBar;
    @FindBy(xpath = "//*[@class='panel-body-list logs']/li")
    List<WebElement> logs;
    @FindBy(xpath = "/html/body/div/div[2]/main/div[2]/div/div[4]/select/option[4]")
    WebElement yellow;

    public DifPageElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getCheckboxes() {
        return checkboxes;
    }

    public List<WebElement> getRadioButtons() {
        return radioButtons;
    }
    public WebElement getDropdown() {
        return colorsDropdown;
    }

    public List<WebElement> getButtons() {
        return buttons;
    }

    public WebElement getLogSideBar() {
        return logSideBar;
    }

    public List<WebElement> getLogs() {
        return logs;
    }

    public WebElement getYellow() {
        return yellow;
    }
}
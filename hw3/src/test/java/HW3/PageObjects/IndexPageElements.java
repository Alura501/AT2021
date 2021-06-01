package HW3.PageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class IndexPageElements {
    @FindBy(xpath = "//*[@id='user-icon']")
    WebElement userIcon;
    @FindBy(xpath = "//*[@id='name']")
    WebElement userName;
    @FindBy(xpath = "/html/body/header/div/nav/ul[2]/li/a/div/span")
    WebElement gotUsername;
    @FindBy(xpath = "//*[@id='password']")
    WebElement userPassword;
    @FindBy(xpath = "//*[@id='login-button']")
    WebElement loginButton;
    @FindBy(xpath = "/html/body/div/div[2]/main/div[2]/h3[1]")
    WebElement text1;
    @FindBy(xpath = "/html/body/div/div[2]/main/div[2]/p")
    WebElement text2;
    @FindBy(xpath = "/html/body/div/div[2]/main/div[2]/h3[2]")
    WebElement textCenter;
    @FindBy(xpath = "//*[@class='footer-bg']")
    WebElement footer;
    @FindBy(xpath = "//*[contains(@class,'benefit-icon')]")
    List<WebElement> imagesList;
    @FindBy(xpath = "//*[contains(@class,'benefit-txt')]")
    List<WebElement> textList;
    @FindBy(xpath = "//*[@id='epam-logo']")
    List<WebElement> logo;


    public IndexPageElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login(String name, String password) {
        userIcon.click();
        userName.sendKeys(name);
        userPassword.sendKeys(password);
        loginButton.click();
    }

    public String getUsername() {
        return gotUsername.getText();
    }

    public String getText1() {
        return text1.getText();
    }

    public String getText2() {
        return text2.getText();
    }

    public WebElement getTextCenter() {
        return textCenter.findElement(By.tagName("a"));
    }

    public WebElement getFooter() {
        return footer;
    }

    public List<WebElement> getImagesList() {
        return imagesList;
    }

    public List<WebElement> getLogo() {
        return logo;
    }

    public List<WebElement> getTextList() {
        return textList;
    }
}
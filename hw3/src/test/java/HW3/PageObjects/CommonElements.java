package HW3.PageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class CommonElements {

    @FindBy(xpath = "//*[@name='navigation-sidebar']")
    WebElement leftSection;
    @FindBy(className = "menu-title")
    WebElement leftSectionDropdown;
    @FindBy(className = "dropdown")
    WebElement headerDropdown;
    @FindBy(xpath = "/html/body/header/div/nav/ul[1]/li[3]/ul/li[8]")
    WebElement differentElementsPage;
    @FindBy(xpath = "//*[starts-with(@class,'uui-header')]/nav/ul[1]/li/a")
    List<WebElement> headerElementsList;

    public CommonElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getLeftSection() {
        return leftSection;
    }

    public WebElement getLeftSectionDropdown() {
        return leftSectionDropdown;
    }

    public ArrayList<String> getLeftSectionDropdownElements() {
        ArrayList<String> menu = new ArrayList<>();
        List<WebElement> options = leftSectionDropdown.findElements(By.tagName("li"));
        for(WebElement we : options)
        {
            menu.add(we.getText());
        }
        return menu;
    }

    public WebElement getDropdown() {
        return headerDropdown;
    }

    public ArrayList<String> getHeaderDropdownElements() {
        ArrayList<String> menu = new ArrayList<>();
        List<WebElement> options = headerDropdown.findElements(By.tagName("li"));
        for(WebElement we : options)
        {
            menu.add(we.getText());
        }
        return menu;
    }

    public WebElement getDifferentElementsPage() {
        return differentElementsPage;
    }

    public ArrayList<String> getHeaderElementsTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for(WebElement we : headerElementsList)
        {
            titles.add(we.getText());
        }
        return titles;
    }


}
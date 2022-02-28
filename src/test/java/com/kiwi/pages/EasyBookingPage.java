package com.kiwi.pages;

import com.kiwi.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EasyBookingPage extends BasePage{

    @FindBy(xpath = "//span[text()='Contact details']")
    public WebElement contactDetailsText;

    @FindBy(xpath = "//div[text()='Continue']")
    public WebElement continueButton;


    public void waitTripsPageToLoad(){
        WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
        wait.until(ExpectedConditions.visibilityOf(contactDetailsText));
    }

}

package com.kiwi.pages;

import com.kiwi.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TripsPage extends BasePage{

    @FindBy(xpath = "//div[text()='Stops']")
    public WebElement stopsDropdownMenuButton;

    @FindBy(xpath = "//span[text()='Nonstop (direct)']")
    public WebElement nonStopFlightRadioButton;

    @FindBy(xpath = "(//span[text()='Select'])[1]")
    public WebElement selectButtonForFirstFilghtOptionOnThePage;

    @FindBy(xpath = "//a[text()='Continue as a guest']")
    public WebElement continueAsAGuestLink;

    @FindBy(xpath = "//div[@id='1-22val-slideID']")
    public WebElement stopsDropdownSlide;

    public boolean stopsDropdownSlideOpen(){
        return stopsDropdownSlide.getAttribute("aria-hidden").equals("false");
    }

    public void waitTripsPageToLoad(){
        WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
        wait.until(ExpectedConditions.visibilityOf(stopsDropdownMenuButton));
    }

}

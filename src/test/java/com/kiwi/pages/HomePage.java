package com.kiwi.pages;

import com.kiwi.utilities.BrowserUtils;
import com.kiwi.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class HomePage extends BasePage{

    @FindBy(xpath = "//div[contains(text(),'Accept')]")
            public WebElement acceptButton;

    @FindBy(xpath = "//div[text()='From']")
    public WebElement departureAirportInputForm;

   @FindBy(xpath = ("//div[@data-test='PlacePickerInput-origin']/input"))
    public WebElement departureAirportInputBox;

    @FindBy(xpath = ("//div[@data-test='PlacePickerRow-wrapper']"))
    public List<WebElement> departureAirportOptionsList;

    @FindBy(xpath = ("//div[text()='To']"))
    public WebElement arrivalAirportInputForm;

    @FindBy(xpath = ("//div[@data-test='PlacePickerInput-destination']/input"))
    public WebElement arrivalAirportInputBox;

    @FindBy(xpath = "//div[@data-test='PlacePickerRow-wrapper']")
    public List<WebElement> arrivalAirportOptionsList;

    @FindBy(xpath = ("//input[@name='search-outboundDate']"))
    public WebElement departureDayCalendarForm;

    @FindBy(xpath = "//button[@data-test='CalendarMoveNextButton']")
    public WebElement nextMonthArrowInCalendarButton;

    @FindBy(xpath = "//button[@data-test='SearchFormDoneButton']")
    public WebElement setDatesButtonInCalendarButton;

    @FindBy(xpath = "//div[@data-test='PassengersField']")
    public WebElement travellersAndBagsDwopdown;

    @FindBy(xpath = "//div[@data-test='PassengersRow-adults']//button[@aria-label='increment']")
    public WebElement adultPassengersIncrementButton;

    @FindBy(xpath = "//div[@data-test='PassengersRow-children']//button[@aria-label='increment']")
    public WebElement childPassengersIncrementButton;

    @FindBy(xpath = "//div[@data-test='PassengersRow-infants']//button[@aria-label='increment']")
    public WebElement infantPassengersIncrementButton;

    @FindBy(xpath = "//div[text()='Done']")
    public WebElement doneInTravellersAndBagsDropdownButton;

    @FindBy(xpath = "//div[@data-test='BagsPopup-cabin']//button[@aria-label='increment']")
    public WebElement cabinBagIncrementButton;

    @FindBy(xpath = "//div[@data-test='BagsPopup-checked']//button[@aria-label='increment']")
    public WebElement checkedBagIncrementButton;

    @FindBy(xpath = "//div[text()='Search']")
    public WebElement searchButton;


   public void selectDepartureAirport(String departure) {
       List<WebElement> departures = departureAirportOptionsList;
       for (WebElement city : departures) {
           if (city.getText().contains(departure)) {
               city.click();
               break;
           }
       }
   }

   public void selectArrivalAirport(String arrival){
       List<WebElement> arrivals = arrivalAirportOptionsList;
       for (WebElement city : arrivals) {
           if (city.getText().contains(arrival)) {
               city.click();
               break;
           }
       }
   }

   public WebElement getDepartureDay(String departDate){
       String xpath = "//div[@data-value='" + departDate + "']";
       return Driver.get().findElement(By.xpath(xpath));
   }

    public WebElement getArrivalDay(String arriveDate){
        String xpath = "//div[@data-value='" + arriveDate + "']";
        return Driver.get().findElement(By.xpath(xpath));
    }

    public void addAdultTravellers(int adults){
        if (adults>1) {
            for (int i = 1; i < adults; i++){
                adultPassengersIncrementButton.click();
            }
        }
    }

    public void addChildTravellers(int children){
        for (int i = 0; i < children; i++){
            childPassengersIncrementButton.click();
        }
    }

    public void addInfantTravellers(int infant){
        for (int i = 0; i < infant; i++){
            infantPassengersIncrementButton.click();
        }
    }

    public void addCabinBag(int cabinBag){
        for (int i = 0; i < cabinBag; i++){
            cabinBagIncrementButton.click();
        }
    }

    public void addCheckedBag(int checkedBag){
        for (int i = 0; i < checkedBag; i++){
            checkedBagIncrementButton.click();
        }
    }

    public void changeWindow(){
        BrowserUtils.waitFor(2);
        Set<String> windowHandles = Driver.get().getWindowHandles();
        String currentWindow = Driver.get().getWindowHandle();
        for (String handle : windowHandles ){
            if (! handle.equals(currentWindow)){
                Driver.get().switchTo().window(handle);
            }
        }

      //  WebElement stopsButton = driver.findElement(By.xpath("//div[text()='Stops']"));
        //explicit wait
        WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
        //wait.until(ExpectedConditions.elementToBeClickable(stopsButton));
    }


    /* @FindBy(xpath = ())
    public WebElement ;
*/



}

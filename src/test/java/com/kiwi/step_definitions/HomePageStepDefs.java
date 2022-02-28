package com.kiwi.step_definitions;

import com.kiwi.utilities.BrowserUtils;
import com.kiwi.utilities.ConfigurationReader;
import com.kiwi.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class HomePageStepDefs {

    WebDriver driver = Driver.get();

    @When("User is on the kiwi.com homepage")
    public void user_is_on_the_kiwi_com_homepage() {
        driver.get(ConfigurationReader.get("url"));
    }

    @When("user selects flight from {string} to {string} airport")
    public void user_selects_flight_from_to_airport(String departure, String arrival) {

        //accept the cookies
        driver.findElement(By.xpath("//p[text()='Accept']")).click();

        //select departure airport
        driver.findElement(By.xpath("//div[text()='From']")).click();
        driver.findElement(By.xpath("//div[@data-test='PlacePickerInput-origin']/input")).sendKeys(departure);
        BrowserUtils.waitFor(2);

        List<WebElement> departures = driver.findElements(By.xpath("//div[@data-test='PlacePickerRow-wrapper']"));
        for (WebElement city : departures) {
            if (city.getText().contains(departure)) {
                city.click();
                break;
            }
        }

        //select arriving airport
        driver.findElement(By.xpath("//div[text()='To']")).click();
        driver.findElement(By.xpath("//div[@data-test='PlacePickerInput-destination']/input")).sendKeys(arrival);
        BrowserUtils.waitFor(2);

        List<WebElement> arrivals = driver.findElements(By.xpath("//div[@data-test='PlacePickerRow-wrapper']"));
        for (WebElement city : arrivals) {
            if (city.getText().contains(arrival)) {
                city.click();
                break;
            }
        }
    }


    @When("selects departure {string} and return {string} date")
    public void selects_departure_and_return_date(String departDate, String arriveDate) {

        //select departure day
        driver.findElement(By.xpath("//input[@name='search-outboundDate']")).click();
        driver.findElement(By.xpath("//div[@data-value='" + departDate + "']")).click();
        BrowserUtils.waitFor(2);

        //select arrival day
        driver.findElement(By.xpath("//button[@data-test='CalendarMoveNextButton']")).click();
        driver.findElement(By.xpath("//div[@data-value='" + arriveDate + "']")).click();
        BrowserUtils.waitFor(2);
        //click on SET Dates
        driver.findElement(By.xpath("//button[@data-test='SearchFormDoneButton']")).click();
    }

    @When("select passengers: {int} adults, {int} children, {int} infant")
    public void select_passengers_adults_children_infant(Integer adults, Integer children, Integer infant) {

        //adding passengers
        //adding adults
        driver.findElement(By.xpath("//div[@data-test='PassengersField']")).click();
        if (adults>1) {
            for (int i = 1; i < adults; i++){
                driver.findElement(By.xpath("//div[@data-test='PassengersRow-adults']//button[@aria-label='increment']")).click();
            }
        }
        //adding children
        if (children>0) {
            for (int i = 0; i < children; i++){
                driver.findElement(By.xpath("//div[@data-test='PassengersRow-children']//button[@aria-label='increment']")).click();
            }
        }
        //adding infants
        if (infant>0) {
            for (int i = 0; i < infant; i++){
                driver.findElement(By.xpath("//div[@data-test='PassengersRow-infants']//button[@aria-label='increment']")).click();
            }
        }

        driver.findElement(By.xpath("//div[text()='Done']")).click();
    }

    @When("add {int} cabin bag and {int} checked in bag")
    public void add_cabin_bag_and_checked_in_bag(Integer cabinBag, Integer checkedBag) {

        driver.findElement(By.xpath("//div[@data-test='PassengersField']")).click();
        //adding cabin bag
        if (cabinBag>0) {
            for (int i = 0; i <cabinBag; i++){
                driver.findElement(By.xpath("//div[@data-test='BagsPopup-cabin']//button[@aria-label='increment']")).click();
            }
        }
        //adding checked bag
        if (checkedBag>0) {
            for (int i = 0; i <checkedBag; i++){
                driver.findElement(By.xpath("//div[@data-test='BagsPopup-checked']//button[@aria-label='increment']")).click();
            }
        }
        driver.findElement(By.xpath("//div[text()='Done']")).click();
    }

    @Then("user clicks on Search button")
    public void user_clicks_on_Search_button() {
        //click on Search
        driver.findElement(By.xpath("//div[text()='Search']")).click();
    }

    @Then("user selects non-stop flight option")
    public void user_selects_non_stop_flight_option() {
        //change window
        Set <String> windowHandles = driver.getWindowHandles();
        String currentWindow = driver.getWindowHandle();
        for (String handle : windowHandles ){
            if (! handle.equals(currentWindow)){
                Driver.get().switchTo().window(handle);
            }
        }

        WebElement stopsButton = driver.findElement(By.xpath("//div[text()='Stops']"));
        //explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(stopsButton));
        stopsButton.click();

        BrowserUtils.waitFor(2);
        //select non-stop flight
        WebElement nonStop = driver.findElement(By.xpath("//span[text()='Nonstop (direct)']"));
        nonStop.click();

    }

    @Then("user select first flight on the page")
    public void user_select_first_flight_on_the_page() {
        BrowserUtils.waitFor(2);
        driver.findElement(By.xpath("(//span[text()='Select'])[1]")).click();
    }

    @Then("user chooses to continue as a guest")
    public void user_chooses_to_continue_as_a_guest() {
        BrowserUtils.waitFor(2);
        //continue as a guest
        driver.findElement(By.xpath("//a[text()='Continue as a guest']")).click();

    }

    @Then("scroll down to continue button and click to submit")
    public void scroll_down_to_continue_button_and_click_to_submit() {

        BrowserUtils.waitFor(2);
        WebElement continueButton = driver.findElement(By.xpath("//div[text()='Continue']"));
        //scroll down to Continue button and click
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueButton);
        continueButton.click();
        BrowserUtils.waitFor(2);

    }
    @Then("take a screenshot")
    public static void take_a_screenshot() throws IOException {
        // name the screenshot with the current date time to avoid duplicate name
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot ts = (TakesScreenshot) Driver.get();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        //return target;
    }


}

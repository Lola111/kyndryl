package com.kiwi.step_definitions;

import com.kiwi.pages.EasyBookingPage;
import com.kiwi.pages.HomePage;
import com.kiwi.pages.TripsPage;
import com.kiwi.utilities.BrowserUtils;
import com.kiwi.utilities.ConfigurationReader;
import com.kiwi.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;

import java.io.IOException;

public class HomePageStepDefs {

    WebDriver driver = Driver.get();
    HomePage homePage = new HomePage();
    TripsPage tripsPage = new TripsPage();
    EasyBookingPage easyBookingPage = new EasyBookingPage();

    @When("User is on the kiwi.com homepage")
    public void user_is_on_the_kiwi_com_homepage() {

        homePage.openKiwi();
    }

    @When("user selects flight from {string} to {string} airport")
    public void user_selects_flight_from_to_airport(String departure, String arrival) {

        //accept the cookies
        BrowserUtils.waitFor(3);
        homePage.acceptButton.click();

        //select departure airport
        homePage.departureAirportInputForm.click();
        homePage.departureAirportInputBox.sendKeys(departure);

        BrowserUtils.waitFor(2);
        homePage.selectDepartureAirport(departure);

        //select arriving airport
        homePage.arrivalAirportInputForm.click();
        homePage.arrivalAirportInputBox.sendKeys(arrival);

        BrowserUtils.waitFor(2);
        homePage.selectArrivalAirport(arrival);
    }


    @When("selects departure {string} and return {string} date")
    public void selects_departure_and_return_date(String departDate, String arriveDate) {

        //select departure day
        homePage.departureDayCalendarForm.click();
        homePage.getDepartureDay(departDate).click();

        //select arrival day
        homePage.nextMonthArrowInCalendarButton.click();
        homePage.getArrivalDay(arriveDate).click();
        BrowserUtils.waitFor(2);

        //click on SET Dates
        BrowserUtils.waitFor(2);
        homePage.setDatesButtonInCalendarButton.click();
    }

    @When("select passengers: {int} adults, {int} children, {int} infant")
    public void select_passengers_adults_children_infant(int adults, int children, int infant) {

        //adding passengers
        homePage.travellersAndBagsDwopdown.click();

        //adding adults
        homePage.addAdultTravellers(adults);
        //adding children
        if (children>0) { homePage.addChildTravellers(children); }
        //adding infants
        if (infant>0) { homePage.addInfantTravellers(infant); }

        homePage.doneInTravellersAndBagsDropdownButton.click();
    }

    @When("add {int} cabin bag and {int} checked in bag")
    public void add_cabin_bag_and_checked_in_bag(Integer cabinBag, Integer checkedBag) {

        homePage.travellersAndBagsDwopdown.click();
        //add cabin bag
        if (cabinBag>0) { homePage.addCabinBag(cabinBag); }
        //add checked bag
        if (checkedBag>0) { homePage.addCheckedBag(checkedBag); }

        homePage.doneInTravellersAndBagsDropdownButton.click();
    }

    @Then("user clicks on Search button")
    public void user_clicks_on_Search_button() {

        //click on Search
        homePage.searchButton.click();

        //change window
        homePage.changeWindow();
        tripsPage.waitTripsPageToLoad();
    }

    @Then("user selects non-stop flight option")
    public void user_selects_non_stop_flight_option() {

        if (!tripsPage.stopsDropdownSlideOpen()) { tripsPage.stopsDropdownMenuButton.click();}
        BrowserUtils.waitFor(2);
        //select non-stop flight
        tripsPage.nonStopFlightRadioButton.click();
    }

    @Then("user select first flight on the page")
    public void user_select_first_flight_on_the_page() {
        BrowserUtils.waitFor(4);
        tripsPage.selectButtonForFirstFilghtOptionOnThePage.click();
    }

    @Then("user chooses to continue as a guest")
    public void user_chooses_to_continue_as_a_guest() {
        BrowserUtils.waitFor(2);
        //continue as a guest
        tripsPage.continueAsAGuestLink.click();
    }

    @Then("scroll down to continue button and click to submit")
    public void scroll_down_to_continue_button_and_click_to_submit() {
        //scroll down to Continue button and click
        easyBookingPage.scrollToElement(easyBookingPage.continueButton);
        BrowserUtils.waitFor(2);
        easyBookingPage.continueButton.click();
    }

    @Then("take a screenshot")
    public void take_a_screenshot() throws IOException {
        BrowserUtils.waitFor(2);
        easyBookingPage.take_screenshot();
    }

}

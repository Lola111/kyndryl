--project title--

kyndrl

--project description--

This is a Maven project in Cucumber BDD for automating the flight ticket booking through kiwi.com.
I used Selenium WebDriver for automating the web-browser, coding language is Java. 
Added needed dependencies and plugins to pom.xml. 

The code can be run for different flight destinations (airport code should be 
provided as an argument) on different dates (yyyy-mm-dd pattern should be followed, until end of May) with different number of
travellers and bags. To change the travel info details check the feature files.

The code takes screenshot after each run in test-output folder.

Detailed Cucumber report can be generated inside Targets folder when it is run through Maven verify.

--how to use the project--

Project can be cloned and run in your IDE. 
All the dependencies and plugins are included. 


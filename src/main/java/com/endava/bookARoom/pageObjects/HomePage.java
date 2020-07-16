package com.endava.bookARoom.pageObjects;

import com.endava.bookARoom.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private static final String baseUrl = "https://automationintesting.online/#/";

    private WebDriver webDriver;

    private By secondBookThisRoomButton = By.xpath("//*[@id=\"root\"]/div[2]/div/div[5]/div/div/div[3]/button");    // "Book this room" button
    private By nextButton = By.xpath("//*[@id=\"root\"]/div[2]/div/div[5]/div/div[2]/div[2]/div/div[1]/span[1]/button[3]");    // "Next" button for switching to next month while booking
    private By startOfBookingHardCoded = By.xpath("//*[@id=\"root\"]/div[2]/div/div[5]/div/div[2]/div[2]/div/div[2]/div[3]/div[1]/div[1]");  // HardCoded Start date for booking
    private By endOfBookingHardCoded = By.xpath("//*[@id=\"root\"]/div[2]/div/div[5]/div/div[2]/div[2]/div/div[2]/div[5]/div[1]/div[2]");  // HardCoded End date for booking

    private By firstNameInput = By.name("firstname");
    private By lastNameInput = By.name("lastname");
    private By emailInput = By.name("email");
    private By phoneInput = By.name("phone");

    private By bookButton = By.cssSelector("button.btn.btn-outline-primary.float-right.book-room");

    private By bookingSuccessfulPopup = By.xpath("//h3[contains(.,'Booking Successful')]");

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void load() {
        webDriver.get(baseUrl);
    }

    public String bookARoomWithHardCodedDates(User user) {
        //Clicking on second "Book this room" button
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement bookARoomButton = wait.until(ExpectedConditions.elementToBeClickable(secondBookThisRoomButton));
        bookARoomButton.click();

        //Clicking on "Next" button until the month is empty from bookings
        while(webDriver.findElements(By.xpath("//div[contains(@class, \"rbc-event-content\") and contains(.,'Unavailable')]")).size() != 0){
            WebElement nextbtn = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
            nextbtn.click();
        }

        //doing a "select" of start-to-end date span for booking, by performing "drag-and-drop" from start to end date
        // TODO: this part needs refactoring to be more accurate when selecting dates for booking
        WebElement fromDate = wait.until(ExpectedConditions.visibilityOfElementLocated(startOfBookingHardCoded));
        WebElement toDate = webDriver.findElement(endOfBookingHardCoded);

        Actions action = new Actions(webDriver);
        action.dragAndDrop(fromDate,toDate).build().perform();

        //populate the User info for booking
        webDriver.findElement(firstNameInput).sendKeys(user.getFirstName());
        webDriver.findElement(lastNameInput).sendKeys(user.getLastName());
        webDriver.findElement(emailInput).sendKeys(user.getEmail());
        webDriver.findElement(phoneInput).sendKeys(user.getPhone());

        //click the "Book" button
        webDriver.findElement(bookButton).click();

        //return the result
        return webDriver.findElement(bookingSuccessfulPopup).getText();   //result should also contain the start and endDate to be verified, but here is omitted


    }

    public void bookARoomWithCustomDates(){
        // add the code here

        // it necessitates to have mapping between any startDate and endDate for booking , with the calendar table ,
        // along with flawless drag-and-drop function
    }

}

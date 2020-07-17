package com.endava.bookARoom;

import com.endava.bookARoom.models.User;
import com.endava.bookARoom.pageObjects.HomePage;
import com.endava.bookARoom.utilities.DateToLocator;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;

public class BookARoomTest extends TestBaseClass{

    @Test
    public void shouldBookARoom(){
        //Given
        HomePage homePage = new HomePage(driver);
        homePage.load();

        User user = dataGeneration.generateRandomUser();

        //When
        String result = homePage.bookARoomWithHardCodedDates(user);

        //Then
        assertThat(result).isEqualTo("Booking Successful!");


    }

    @Test
    public void selectorTest() throws ParseException {

        // prints the workings of dateToLocator utility, displays all three relevant locators
        DateToLocator dateToLocator = new DateToLocator("09/09/2020");

        System.out.println(dateToLocator.getLocatorOne());
        System.out.println(dateToLocator.getLocatorTwo());
        System.out.println(dateToLocator.getLocatorThree());
        System.out.println(dateToLocator.getNumberOfNextButtonClicks());

    }

    @Test
    public void shouldBookARoomForCustomDates() throws ParseException {
        //Given
        HomePage homePage = new HomePage(driver);
        homePage.load();

        User user = dataGeneration.generateRandomUser();

        //When
        // DRAG AND DROP WILL NOT WORK AS EXPECTED, EVEN THOUGH THE SELECTORS ARE GOOD.
        String result = homePage.bookARoomWithCustomDates(user, "09/09/2020", "11/09/2020");

        //Then
        assertThat(result).isEqualTo("Booking Successful!");


    }

}

package com.endava.bookARoom;

import com.endava.bookARoom.models.User;
import com.endava.bookARoom.pageObjects.HomePage;
import org.junit.jupiter.api.Test;

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

}

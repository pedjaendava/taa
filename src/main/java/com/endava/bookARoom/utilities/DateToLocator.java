package com.endava.bookARoom.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import lombok.Data;

@Data
public class DateToLocator {

    private String locatorOne;
    private String locatorTwo;
    private String locatorThree;  // Three different locators for the same single day field in the website calendar
    private int numberOfNextButtonClicks;

    public DateToLocator(String date) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();

        int day = Integer.parseInt(date.substring(0,2));
        calendar.setTime(dateFormat.parse(date));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);  //this is necessary for proper locatorThree selector selection

        this.locatorOne = "//div[contains(@class, \"rbc-date-cell\") and not(contains(@class, \"rbc-off-range\"))]/a[contains(text(), \"" + day +"\")]";
        this.locatorTwo = "//div[contains(@class, \"rbc-date-cell\") and not(contains(@class, \"rbc-off-range\"))]/a[contains(text(), \"" + day +"\")]/..";
        this.locatorThree = "//div[contains(@class, \"rbc-date-cell\") and not(contains(@class, \"rbc-off-range\"))]/a[contains(text(), \"" + day +"\")]/../../../../div/div[contains(@class, \"rbc-day-bg\")]["+ dayOfWeek +"]";

        String today = dateFormat.format(Calendar.getInstance().getTime()).toString();
        this.numberOfNextButtonClicks = calculateNextClicks(date.substring(3), today.substring(3));

    }

    //calculate the number of times Next button needs to be clicked in order to land on a proper month on website
    private int calculateNextClicks(String date, String today){

        String[] dateSub = date.split("/");
        String[] todaySub = today.split("/");

        int m_date = Integer.parseInt(dateSub[0]);
        int y_date = Integer.parseInt(dateSub[1]);

        int m_today = Integer.parseInt(todaySub[0]);
        int y_today = Integer.parseInt(todaySub[1]);

        return 12*(y_date - y_today) + m_date - m_today;  //number of clicks for Next button
    }

}

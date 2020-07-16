package com.endava.bookARoom;

import com.endava.bookARoom.dataGeneration.DataGeneration;
import com.endava.bookARoom.managers.DriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class TestBaseClass {

	protected WebDriver driver;
	protected DataGeneration dataGeneration = new DataGeneration();

	@BeforeEach
	public void setUpDriver() {
	driver = DriverManager.getWebDriver();
	}

	@AfterEach
	public void tearDownDriver() throws Exception {
		Thread.sleep( 4000 );
		//takeSnapShot( driver, "C://Alati/temp2/ss.jpeg" );
		//driver.quit();
	}

	public void takeSnapShot( WebDriver webdriver, String fileWithPath ) throws Exception {
		TakesScreenshot scrShot = ( (TakesScreenshot) webdriver );
		File SrcFile = scrShot.getScreenshotAs( OutputType.FILE );
		File DestFile = new File( fileWithPath );
		FileUtils.copyFile( SrcFile, DestFile );
	}

}

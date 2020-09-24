package boh.qa.automation.ofactkt.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import boh.qa.automation.ofactkt.pages.AlertActionsPage;
import boh.qa.automation.ofactkt.pages.CreateAlertPage;

public class BaseTest {

	public WebDriver driver;
	public BasePage basePage;
	public Properties properties;
	
	public CreateAlertPage createAlertPage;
	public AlertActionsPage alertActionsPage;
	
	@BeforeTest
	public void setup() {
		basePage = new BasePage();
		properties = basePage.init_Properties();
		driver = basePage.init_Driver(properties);
	}
	
	@AfterTest
	public void tearDown() {
		//driver.quit();
	}
}

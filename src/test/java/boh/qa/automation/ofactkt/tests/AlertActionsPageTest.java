package boh.qa.automation.ofactkt.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import boh.qa.automation.ofactkt.base.BaseTest;
import boh.qa.automation.ofactkt.listeners.ExtentReportListener;
import boh.qa.automation.ofactkt.pages.AlertActionsPage;

//Below annotation is used for adding report listeners
@Listeners(ExtentReportListener.class)
public class AlertActionsPageTest extends BaseTest {

	AlertActionsPage alertActionsPage;
	
	@BeforeClass
	public void AlertActionsPageSetup() {
		alertActionsPage = new AlertActionsPage(driver);
	}
	
	@Test
	public void verifyAlertActionsTest() {
		alertActionsPage.NavigateToAlertAssignDisposition();
//		alertActionsPage.AssignAlert();
//		alertActionsPage.AlertDisposition();
	}

}

package boh.qa.automation.ofactkt.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import boh.qa.automation.framework.utils.ExcelUtil;
import boh.qa.automation.ofactkt.base.BaseTest;
import boh.qa.automation.ofactkt.constants.Constants;
import boh.qa.automation.ofactkt.listeners.ExtentReportListener;
import boh.qa.automation.ofactkt.pages.CreateAlertPage;

//Below annotation is used for adding report listeners
@Listeners(ExtentReportListener.class)
public class CreateAlertPageTest extends BaseTest {
	CreateAlertPage createAlertPage;
	int iRow = 1;
	
	@BeforeClass
	public void CreateAlertPageSetup() {
		createAlertPage = new CreateAlertPage(driver);
	}
	
	@DataProvider
	public Object[][] getCreateAlertTestData() {
		Object createAlertTestData[][] = ExcelUtil.getTestData(Constants.TEST_DATA_FILE_PATH, "CreateAlertTestData");
		return createAlertTestData;
	}
	
	@Test (dataProvider = "getCreateAlertTestData")
	public void verifyCreateAlertTest(String clientName, String clientType, String DOB, String TIN_SSN, String address, String city, String state, String country, String zip, String services, String otherTxt, String resultID, String summary, String addAttachment) {
		//int iRow = 1;
		int iCol = 14;
		//createAlertPage = new CreateAlertPage(driver);
		createAlertPage.inputAlertData(clientName, clientType, DOB, TIN_SSN, address, city, state, country, zip, services, otherTxt, resultID, summary, addAttachment);
		createAlertPage.clickCreateAlertBtn();

		//***** This method will get the OFAC ID and write to excel
		String alertNumber = createAlertPage.getOFACTktID();
		//System.out.println(alertNumber);
		ExcelUtil.putTestData(Constants.TEST_DATA_FILE_PATH, "CreateAlertTestData", iRow, iCol, alertNumber);
		iRow = iRow + 1;
		//System.out.println("Row Number.................." + iRow);
	}
	
}

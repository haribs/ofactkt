package boh.qa.automation.ofactkt.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import boh.qa.automation.framework.utils.ElementsUtil;
import boh.qa.automation.framework.utils.ExcelUtil;
import boh.qa.automation.framework.utils.JavaScriptUtil;
import boh.qa.automation.framework.utils.WaitUtil;
import boh.qa.automation.ofactkt.constants.Constants;

public class AlertActionsPage {
	
	private WebDriver driver;
	ElementsUtil elementsUtil;
	WaitUtil waitUtil;
	JavaScriptUtil jsUtil;
	
	//String assignAlertTo = "100";
	//String assingAlertToName = "Sushma Vejendla";
	//String assingAlertToName = "Hari Sridharala";
	//String matchTypeOption = "False Positive";
	
	By homeLink = By.id("home");
	By searchAlert = By.id("input-global-search");
	By clickAlertNumber = By.xpath("/html/body/app-root/section/div/div/app-dashboard/div[1]/p-table/div/div[2]/table/tbody/tr/td[2]");
	
	By rowLocator = By.xpath("/html/body/app-root/section/div/div/app-dashboard/div[1]/p-table/div/div[2]/table/tbody/tr");
	String beforeTRXpath ="/html/body/app-root/section/div/div/app-dashboard/div[1]/p-table/div/div[2]/table/tbody/tr[";
	String afterTRXpath = "]/td[2]";
	
	By actions = By.xpath("(//span[@class='ui-button-text ui-clickable'])[1]");
	By clkAssignTo = By.linkText("Assign to");
	By listOfAssignTos = By.xpath("(//ul[@class='ui-submenu-list ng-trigger ng-trigger-submenu'])[3]/li");
	
	By listOfMatchTypes = By.xpath("(//ul[@class='ui-submenu-list ng-trigger ng-trigger-submenu'])[1]/li");
	By clkMatchType = By.linkText("MatchType");
	By requestorNotes = By.id("input-notes-to-requestor");
	By internalTeamNotes = By.id("input-notes-for-internal");
	By clkSubmit = By.xpath("//button[@class='btn btn-primary px-5 py-2']");
	
	
	public AlertActionsPage(WebDriver driver) {
		this.driver = driver;
		elementsUtil = new ElementsUtil(this.driver);
		waitUtil = new WaitUtil(this.driver);
		jsUtil = new JavaScriptUtil(this.driver);
	}
	
	public void NavigateToAlertAssignDisposition() {
//		ArrayList<String> listOfAlertNumbers = ExcelUtil.getAlertNumber(Constants.TEST_DATA_FILE_PATH, "AlertNumbers", 0);
//		for(int i=0; i<listOfAlertNumbers.size(); i++) {
//			elementsUtil.doSendKeys(searchAlert, listOfAlertNumbers.get(i));
//			waitUtil.staticWait(5000);
//			elementsUtil.doClick(clickAlertNumber);
//			AssignAlert();
//			AlertDisposition();
//		}
		
		Object alertActionsTestData[][] = ExcelUtil.getAlertActionsTestData(Constants.TEST_DATA_FILE_PATH, "AlertActions");
		String alertNumber;
		String assignAlertTo;
		String alertDisposition;
		
		for(int i=0; i<alertActionsTestData.length; i++) {
			for(int j=0; j<alertActionsTestData[i].length; j++) {
				if(j == 0) {
					//search alert and click on alert number
					alertNumber = (String) alertActionsTestData[i][j];
					System.out.println("Alert Number..." + alertNumber);
					elementsUtil.doSendKeys(searchAlert, alertNumber);
					waitUtil.staticWait(5000);
					elementsUtil.doClick(clickAlertNumber);
				} else if (j == 1) {
					//assign alert
					assignAlertTo = (String) alertActionsTestData[i][j];
					System.out.println("Assign Alert To..." + assignAlertTo);
					AssignAlert(assignAlertTo);
				} else if (j == 2) {
					//alert disposition
					alertDisposition = (String) alertActionsTestData[i][j];
					System.out.println("Alert Match Type..." + alertDisposition);
					AlertDisposition(alertDisposition);
				}
			}
		}	
	}
	
	private void AssignAlert(String assignAlertTo) {
			
		waitUtil.staticWait(1500);
		elementsUtil.doClick(actions);	
		elementsUtil.doClick(clkAssignTo);
		waitUtil.staticWait(2000);
		List<WebElement> names = elementsUtil.getElements(listOfAssignTos);
		int nameCount = names.size();
		System.out.println("Assign To Name Count...." + nameCount);	
		
		for (int i = 0; i < nameCount; i++) {
			String text = names.get(i).getText();
			System.out.println("text..."+ i + "..." + text);
			if (names.get(i).getText().equalsIgnoreCase(assignAlertTo)) {
				names.get(i).click();
				System.out.println("assignAlertToName is Clicked...");
				break;
			}
		}
//		waitUtil.staticWait(10000);
//		elementsUtil.doClick(homeLink);
	}
	
	private void AlertDisposition(String matchTypeOption) {
		//elementsUtil.doClick(matchType);
		elementsUtil.doClick(actions);	
		elementsUtil.doClick(clkMatchType);
		waitUtil.staticWait(2000);
		List<WebElement> matchTypes = elementsUtil.getElements(listOfMatchTypes);
		int matchTypesCount = matchTypes.size();
		System.out.println("Match Type Count...." + matchTypesCount);
		
		for (int i = 0; i < matchTypesCount; i++) {
			String text = matchTypes.get(i).getText();
			System.out.println("text..."+ i + "..." + text);
			if (matchTypes.get(i).getText().equalsIgnoreCase(matchTypeOption)) {
				matchTypes.get(i).click();
				System.out.println("Match Types is Clicked...");
				elementsUtil.doSendKeys(requestorNotes, "Notes to Requestor...");
				elementsUtil.doSendKeys(internalTeamNotes, "Notes to Internal Team...");
				elementsUtil.doClick(clkSubmit);
				break;
			}
		}
		waitUtil.staticWait(6000);
		elementsUtil.doClick(homeLink);
	}

}

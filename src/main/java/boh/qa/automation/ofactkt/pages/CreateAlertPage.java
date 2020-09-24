package boh.qa.automation.ofactkt.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import boh.qa.automation.framework.utils.ElementsUtil;
import boh.qa.automation.framework.utils.JavaScriptUtil;
import boh.qa.automation.framework.utils.WaitUtil;
import boh.qa.automation.ofactkt.base.BasePage;

public class CreateAlertPage extends BasePage {

	private WebDriver driver;
	ElementsUtil elementsUtil;
	WaitUtil waitUtil;
	JavaScriptUtil jsUtil;
	
	By clientName = By.id("input-client");
	By clntTypeCounterParty = By.id("input_client_type_1");
	By clntTypeCustomer = By.id("input_client_type_2");
	By clkDOB = By.id("input-input_dob");
	By monthDOB = By.xpath("//select[starts-with(@class,'ui-datepicker-month')]");
	By yearDOB = By.xpath("//select[starts-with(@class,'ui-datepicker-year')]");
	By dateDOB = By.xpath("//div[starts-with(@class, 'ui-datepicker-calendar-container')]/table/tbody/tr/td/a");
	By TIN_SSN = By.id("input-ssn");
	By address = By.id("autoAddress");
	By city = By.id("input-city");
	By state = By.id("input-state");
	By country = By.id("input-country");
	By zip = By.id("input-zip");
	By services = By.id("input-services");
	By otherTxt = By.id("input-other");
	By resultID = By.id("input-result-id");
	By summary = By.id("input-summary");
	By addAttachment = By.id("file");
	By createAlertBtn = By.id("create-tkt");
	
	//By rowLocator = By.xpath("/html/body/app-root/section/div/div/app-dashboard/div[1]/p-table/div/div[2]/table/tbody/tr");
	By rowLocator = By.xpath("/html/body/app-root/section/div/div/app-ticket/main/app-dashboard/div[1]/p-table/div/div[2]/table/tbody/tr");
	String beforeTRXpath ="/html/body/app-root/section/div/div/app-ticket/main/app-dashboard/div[1]/p-table/div/div[2]/table/tbody/tr[";
	String afterTRXpath = "]/td[1]";
	
	public CreateAlertPage(WebDriver driver) {
		this.driver = driver;
		elementsUtil = new ElementsUtil(this.driver);
		waitUtil = new WaitUtil(this.driver);
		jsUtil = new JavaScriptUtil(this.driver);
	}
	
	
	public void inputAlertData(String clientName, String clientType, String DOB, String TIN_SSN, String address, String city, String state, String country, String zip, String services, String otherTxt, String resultID, String summary, String addAttachment) {
	
		waitUtil.staticWait(800);
		elementsUtil.doSendKeys(this.clientName, clientName);
		
		if (clientType.equalsIgnoreCase("Counter Party")){
			elementsUtil.doClick(clntTypeCounterParty);
		}
		else {
			elementsUtil.doClick(clntTypeCustomer);
		}
		//elementsUtil.doSendKeys(this.DOB, DOB);
		elementsUtil.doClick(clkDOB);
		selectDOB(DOB);
				
		elementsUtil.doSendKeys(this.TIN_SSN, TIN_SSN);
		elementsUtil.doSendKeys(this.address, address);
		elementsUtil.doSendKeys(this.city, city);
		elementsUtil.doSendKeys(this.state, state);
		elementsUtil.doSendKeys(this.country, country);
		elementsUtil.doSendKeys(this.zip, zip);
		elementsUtil.doSelectByVisibleText(this.services, services);
		if (services.equalsIgnoreCase("Other")) {
			elementsUtil.doSendKeys(this.otherTxt, otherTxt);
		}
		elementsUtil.doSendKeys(this.resultID, resultID);
		elementsUtil.doSendKeys(this.summary, summary);
		elementsUtil.doSendKeys(this.addAttachment, addAttachment);
		
	}
	
	public void clickCreateAlertBtn() {
		elementsUtil.doClick(createAlertBtn);
		waitUtil.staticWait(15000);
		driver.navigate().refresh();
	}
	
	public String getOFACTktID() {
		waitUtil.staticWait(5000);
		String fullTRXpath = beforeTRXpath + "1" + afterTRXpath;
		WebElement alertNumber = elementsUtil.getElement(By.xpath(fullTRXpath));
		//System.out.println("Full Alert Number....." + alertNumber.getText());
		
		String aa = alertNumber.getText();
		System.out.println("Alert Number..." + aa.substring(aa.indexOf(" ")+1));

		String tktID = aa.substring(aa.indexOf(" ")+1);
		return tktID;
	}
	
	private void selectDOB(String dob) {
	
		String[] lDOB = dob.split("/");
//		System.out.println("Date Array Length..." + lDOB.length);
//		System.out.println("Month..." + lDOB[0]);
//		System.out.println("Year..." + lDOB[1]);
//		System.out.println("Date..." + lDOB[2]);
		elementsUtil.doSelectByValue(monthDOB, lDOB[0]);
		elementsUtil.doSelectByValue(yearDOB, lDOB[1]);
		List<WebElement> dateList = elementsUtil.getElements(dateDOB);
		System.out.println("Date List Size...." + dateList.size());
		for(int i=0; i<dateList.size();i++) {
			System.out.println("Date..." + dateList.get(i).getText());
			if(dateList.get(i).getText().equals(lDOB[2])) {
				elementsUtil.doClick(By.xpath("(//div[starts-with(@class, 'ui-datepicker-calendar-container')]/table/tbody/tr/td/a)[" + (i+1) + "]"));
				break;
			}
		}
		
	}
}

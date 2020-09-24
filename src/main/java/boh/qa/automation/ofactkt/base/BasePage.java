package boh.qa.automation.ofactkt.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import boh.qa.automation.ofactkt.constants.Constants;

public class BasePage {

	WebDriver driver;
	Properties properties;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public WebDriver init_Driver(Properties properties) {
		
		String browserName = properties.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", Constants.EDGE_DRIVER_PATH);
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		} else {
			System.out.println(browserName + " browser is not found");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		getDriver().get(properties.getProperty("siturl"));
		return getDriver();
	}

	public Properties init_Properties() {
		properties = new Properties();
		FileInputStream fileStream;
		try {
			fileStream = new FileInputStream(".\\src\\main\\java\\boh\\qa\\automation\\ofactkt\\config\\qa.ofactkt.config.properties");
			properties.load(fileStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public String getScreenshot() {
		File srcScreenshot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String ssPath = System.getProperty("user.dir")+"\\screenshots\\" + System.currentTimeMillis() + ".png";
		File dest = new File(ssPath);
		try {
			FileUtils.copyFile(srcScreenshot,dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ssPath;
	}
}

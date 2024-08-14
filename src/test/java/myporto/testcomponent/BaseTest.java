package myporto.testcomponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import myporto.pageobjectmodel.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	

	public WebDriver initializeDriver() throws IOException {
		//get a information about what browser that we use
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\myporto\\resources\\GlobalData.properties");
		properties.load(fileInputStream);
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") : properties.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1920, 1080));
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}
	

	
	public List<HashMap<String, String>> getJsonDataToMap (String filePath) throws IOException {
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
	
		//String to HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		
		return data;
		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeDriver() {
		driver.close();
	}

}

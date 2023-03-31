package chaitanya.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import chaitanya.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	public String url;

	public WebDriver initializeDriver() throws IOException {
//		mvn test -PRegression -Dbrowser=Firefox
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src\\test\\java\\chaitanya\\Resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		url = prop.getProperty("url");

		if (browserName.contains("chrome")) {
			// System.setProperty("WebDriver.Chrome.Driver",
			// ".\\Drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new org.openqa.selenium.Dimension(1440, 900));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			// System.setProperty("WebDriver.Edge.Driver", ".\\Drivers\\msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		WebDriver driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo(url);
		return landingPage;

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(".\\Screenshots\\"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		String path= System.getProperty("user.dir")+"\\Screenshots\\"+testCaseName+".png";
		return path;
	}
}
//
//<dependency>
//<groupId> org.apache.cassandra</groupId>
//<artifactId>cassandra-all</artifactId>
//<version>0.8.1</version>
//
//<exclusions>
//	<exclusion>
//		<groupId>org.slf4j</groupId>
//		<artifactId>slf4j-log4j12</artifactId>
//	</exclusion>
//	<exclusion>
//		<groupId>log4j</groupId>
//		<artifactId>log4j</artifactId>
//	</exclusion>
//</exclusions>
//
//</dependency>

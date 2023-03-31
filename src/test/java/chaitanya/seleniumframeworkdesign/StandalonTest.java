package chaitanya.seleniumframeworkdesign;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import chaitanya.pageobjects.LandingPage;
import chaitanya.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

//div[class='card-body'] h5 b
public class StandalonTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ADIDAS ORIGINAL";
		String url = "https://rahulshettyacademy.com/client";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo(url);
		landingPage.loginApplication("email@chaitanya.com", "Chaitanya@075");
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		productcatalogue.addProductToCart(productName);
		
//		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
//		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
//		boolean verify = cartProducts.stream().anyMatch(p->p.getText().equals("ADIDAS ORIGINAL"));
//		Assert.assertTrue(verify);
//		driver.findElement(By.cssSelector("li[class='totalRow'] button")).click();
//		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
//		driver.findElement(By.cssSelector(".btnn.action__submit")).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
//		Assert.assertEquals("THANKYOU FOR THE ORDER.", driver.findElement(By.cssSelector(".hero-primary")).getText());
//		driver.quit();
	}

}

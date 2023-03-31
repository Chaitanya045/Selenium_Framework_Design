package chaitanya.seleniumframeworkdesign;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import chaitanya.TestComponents.BaseTest;
import chaitanya.TestComponents.Retry;
import chaitanya.pageobjects.CartPage;
import chaitanya.pageobjects.CheckoutPage;
import chaitanya.pageobjects.ConfirmationPage;
import chaitanya.pageobjects.LandingPage;
import chaitanya.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class ErrorValidation extends BaseTest{

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginError() throws IOException {
		// TODO Auto-generated method stub
//		String productName = "ADIDAS ORIGINAL";
//		String country = "ind";
		ProductCatalogue productcatalogue = landingPage.loginApplication("email@chaitanyacom", "Chaitanya@075");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productError() throws InterruptedException {
		String productName = "ADIDAS ORIGINAL";
		String country = "ind";
		ProductCatalogue productcatalogue = landingPage.loginApplication("email@chaitanya.com", "Chaitanya@075");
		productcatalogue.addProductToCart(productName);
		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay("ADIDAS ORGINAL");
		Assert.assertFalse(match);
	}
}

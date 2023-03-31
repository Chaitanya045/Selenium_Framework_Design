package chaitanya.seleniumframeworkdesign;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import chaitanya.pageobjects.CartPage;
import chaitanya.pageobjects.CheckoutPage;
import chaitanya.pageobjects.ConfirmationPage;
import chaitanya.pageobjects.LandingPage;
import chaitanya.pageobjects.OrdersPage;
import chaitanya.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class SubmitOrderTest extends BaseTest {
	String productName = "zara coat 3";

	@Test(dataProvider = "getData", groups = "purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String country = "ind";
		ProductCatalogue productcatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productcatalogue.addProductToCart(input.get("product"));
		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckout();
		checkoutPage.selectCountry(country);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String message = confirmationPage.getConfirmationMessage();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", message);

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("email@chaitanya.com", "Chaitanya@075");
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Boolean match = ordersPage.verifyOrderDisplay(productName);
		Assert.assertTrue(match);
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(".\\src\\test\\java\\chaitanya\\data\\data.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}



//@DataProvider
//public Object[][] getData() {
//	return new Object[][] {{"email@chaitanya.com","Chaitanya@075","ZARA COAT 3"},
//		{"email@chaitanya.org","Chaitanya@075","ADIDAS ORIGINAL"}};
//}

//@DataProvider
//public Object[][] getData() {
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "email@chaitanya.com");
//	map1.put("password", "Chaitanya@075");
//	map1.put("product", "ZARA COAT 3");
//	HashMap<String,String> map2 = new HashMap<String,String>();
//	map2.put("email", "email@chaitanya.org");
//	map2.put("password", "Chaitanya@075");
//	map2.put("product", "ADIDAS ORIGINAL");
//	
//	return new Object[][] {{map1},{map2}};
//}

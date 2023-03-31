package chaitanya.StepDefinitions;

import java.io.IOException;

import chaitanya.TestComponents.BaseTest;
import chaitanya.pageobjects.CartPage;
import chaitanya.pageobjects.CheckoutPage;
import chaitanya.pageobjects.ConfirmationPage;
import chaitanya.pageobjects.LandingPage;
import chaitanya.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class StepDefinitonImpl extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartpage;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce page")
	public void i_landed_on_ecommerce_page() throws IOException {
	    landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to cart$")
	public void i_add_productcart(String productName) throws InterruptedException {
		productCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		cartpage = productCatalogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_confirmation_page(String string) {
		String message = confirmationPage.getConfirmationMessage();
		Assert.assertEquals(string, message);
	}
}

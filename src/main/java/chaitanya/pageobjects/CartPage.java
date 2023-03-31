package chaitanya.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import chaitanya.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "li[class='totalRow'] button")
	WebElement checkoutEle;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> productTitles;

	public Boolean verifyProductDisplay(String productName) {
		Boolean match = productTitles.stream().anyMatch(p->p.getText().equals(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutEle.click();
		return new CheckoutPage(driver);
	}

}

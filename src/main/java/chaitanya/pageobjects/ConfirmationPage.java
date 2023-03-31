package chaitanya.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import chaitanya.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By textVisibility = By.cssSelector(".hero-primary");
	
	@FindBy(css = ".hero-primary")
	WebElement text;
	
	public String getConfirmationMessage() {
		waitForElementToAppear(textVisibility);
		return text.getText();
	}
	
	

	
}

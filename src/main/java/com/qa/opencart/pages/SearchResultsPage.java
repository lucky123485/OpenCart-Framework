package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constante.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	ElementUtil ele;
	
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		ele=new ElementUtil(this.driver);
	}
	
	public ProductInfoPage selectProduct(String ProductName) {
		ele.waitforVisibilityOfElement(By.linkText(ProductName), AppConstants.MEDIUM_DEFAULT_WAIT).click();
		return new ProductInfoPage(driver);
	}

}

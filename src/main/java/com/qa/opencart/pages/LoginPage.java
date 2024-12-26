package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constante.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
    
	private WebDriver driver;
	
	ElementUtil eleUtil;
	//By locator
	private By userName=By.id("input-email");
	private By password=By.id("input-password");
	private By logBtn=By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input");
	private By logo=By.xpath("//img[@title='naveenopencart1']");
	private By forgottenpwd=By.linkText("Forgotten Password");
	  private By registerPage=By.linkText("Register");
	
	//page constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);
	}
	@Step("Getting Login page Title")
	//page action
	public String getLogInPageTitle() {
	String title=	eleUtil.WaitForTitleIs(AppConstants.LOGIN_PAGE_TITLE,AppConstants.SHORT_DEFAULT_WAIT);
		
//	String title=	driver.getTitle();
// 	System.out.println("Title is:  "  +title);
 	return title;
	}
	@Step("Getting Login page Url ")
	public String getLogInPageUrl() {
//	 String URL=eleUtil.WaitForURlContains("account/login", 5);	
	
    String URL=	driver.getCurrentUrl();
	System.out.println("log in Page url:"   +URL);
	return URL;
	}
	@Step("checking Login page logo is visible")
	public boolean logoIsVisible() {
	return	eleUtil.waitforVisibilityOfElement(logo,AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		
  //	return	driver.findElement(logo).isDisplayed();
	
	
	}
	@Step("verify  Login page forget password link Exist")
	public boolean isForgetPwdLinkExist() {
	return	eleUtil.waitforVisibilityOfElement(forgottenpwd,AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	
    //return driver.findElement(forgottenpwd).isDisplayed();
     
	}
	@Step("userName is: {0} , Password is :{1}")
	public AccountPage doLogIn(String username,String pwd) {
		eleUtil.waitforVisibilityOfElement(userName, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(username);
		eleUtil.dosendKeys(password, pwd);
	    eleUtil.getclick(logBtn);
		
//		driver.findElement(userName).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(logBtn).click();
	System.out.println("user logged in");
		return new AccountPage(driver);
	}
	
 
	@Step("navigating to register Page")
  public RegisterPage navigateToRegisterPage() {
	eleUtil.getclick(registerPage);
	return new RegisterPage(driver);
}    
  
  
	 
	}


  
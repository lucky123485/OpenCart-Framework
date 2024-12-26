package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constante.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	
		private WebDriver driver;
		ElementUtil ele;
		
		By UserName=By.id("input-firstname");
		By LastName=By.id("input-lastname");
		By Email=By.id("input-email");
		By Telephonne=By.id("input-telephone");
		By pwd=By.id("input-password");
		By ConfirmPwd=By.id("input-confirm");
		By SubcribeYes=By.xpath("(//label[@class='radio-inline'])[1]//input[@type='radio']");
		By SubcribeNo=By.xpath("(//label[@class='radio-inline'])[2]//input[@type='radio']");
		By AgreeCheckBox=By.name("agree");
		By ContinueButton=By.xpath("//input[@type='submit' and @value='Continue']");
		
		By sucessmessg=By.xpath("//div[@id='content']/h1");
		By logoutlink=By.linkText("Logout");
		By registerlink=By.linkText("Register");
		
		public RegisterPage(WebDriver driver) {
			this.driver=driver;
			ele=new ElementUtil(this.driver);
		}
		public boolean userRegistration(String userName, String lastName,String email,String telePhone,String Pwd,String confirmPwd, String subcribe) {
			ele.waitforVisibilityOfElement(UserName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(userName);
			ele.dosendKeys(LastName, lastName);
			ele.dosendKeys(Email, email);
			ele.dosendKeys(Telephonne, telePhone);
			ele.dosendKeys(pwd, Pwd);
			ele.dosendKeys(ConfirmPwd, confirmPwd);
			
			if(subcribe.equalsIgnoreCase("yes")) {
				ele.getclick(SubcribeYes);
			}else {
				ele.getclick(SubcribeNo);
			}
			ele.getclick(AgreeCheckBox);
			ele.getclick(ContinueButton);
			
	 String successMesg=		ele.waitforVisibilityOfElement(sucessmessg, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
	  System.out.println(successMesg);
	  if(successMesg.contains(AppConstants.USER_REGISTRATION_SUCESS_MESSAGE)) {
		  ele.getclick(logoutlink);
		  ele.getclick(registerlink);
		  return true;
	  }else {
	  return false;
	  }
		}
			
				
			
		
		
	}



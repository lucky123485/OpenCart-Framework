package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constante.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic100: Design Open Cart Login Page")
@Story("US101: Login page Feature")
@Feature("F50: Features Login Page")
public class LoginPageTest extends BaseTest {
	
	@Description("Login page title test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void logInPageTitleTest() {
    String title=	loginpage.getLogInPageTitle();
    Assert.assertEquals(title,AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Login page Url test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=2)
	 public void logInPageUrl() {
      String url=loginpage.getLogInPageUrl();
      Assert.assertTrue(url.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Description("Verify Forgot Pwd Link exist test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	 public void forgotPwdLinkExistTest() {
	Assert.assertTrue(loginpage.isForgetPwdLinkExist());
	 }
	@Description("Verify App logo exist test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	 public void appLogoExistTest() {
		 Assert.assertTrue(loginpage.logoIsVisible());
	 }
	
	@Description("Verify User is login with valid credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=5)
	 public void loginTest() {
	accPage=loginpage.doLogIn(prop.getProperty("username"),prop.getProperty("password"));
	Assert.assertTrue(accPage.accPageLogoutLinkExist());
	 }

}

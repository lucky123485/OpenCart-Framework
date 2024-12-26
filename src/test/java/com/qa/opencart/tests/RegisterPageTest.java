package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class RegisterPageTest extends BaseTest {
@BeforeClass

 public void registerPageSetup() {
registerPage=loginpage.navigateToRegisterPage();
}

public String getRandomEmailId() {
	return "Testautomation" + System.currentTimeMillis() +"@oencart.com"; 
}
 @DataProvider
 public Object [] [] getUserRegData() {
	 return new Object[][] {
		 {"Rahul","sharma","905666","904444","904444","yes"},
		 {"Pinu","jangra","89090","1234","1234","yes"},
		 {"Rahul","kaushik","56789","9090","9090","yes"}
		 
		 
	 };
 }
 
  
@Test(dataProvider="getUserRegData")

public void userRegTest(String userName, String lastName ,String telePhone,String Pwd,String confirmPwd, String subcribe ) {
boolean isRegDone=	registerPage.userRegistration(userName,lastName,getRandomEmailId(),telePhone,Pwd,confirmPwd,subcribe);
   Assert.assertTrue(isRegDone);
}

}

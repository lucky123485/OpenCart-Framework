package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constante.AppConstants;

public class AccountPageTest extends BaseTest {
	
 @BeforeClass
   public void accSetup() {
	 accPage=loginpage.doLogIn(prop.getProperty("username"),prop.getProperty("password"));
	 
 }
 @Test
 public void accPagetTitleTest() {
	 String title=accPage.getAccPageTitle();
	 Assert.assertEquals(title,AppConstants.ACCOUNT_Page_TITLE);
 }
 @Test
 public void accPageUrlTest() {
	 Assert.assertTrue(accPage.getAccPageUrl().contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION));
	 
 }
 @Test
 public void accPageLogoutLinkExistTest() {
	 Assert.assertTrue(accPage.accPageLogoutLinkExist());
 }
 @Test
 public void accPageSearchLinkTest() {
	 Assert.assertTrue(accPage.accPageSearch());
	 
 }
 @Test
 public void accountPageHeaderTest(){
	 List<String> accPageHeader=accPage.accPageHeader();
	 System.out.println(accPageHeader.size());
	 Assert.assertEquals(accPageHeader,AppConstants.ACCOUNT_PAGE_HEADER_LIST);
 }  
   @Test
    public void searchTest() {
    searchResultsPage=	accPage.doSerch("MacBook");
    productInfoPage= searchResultsPage.selectProduct("MacBook Pro");
     String actProductHeader= productInfoPage.getProductHeaderName();
     Assert.assertEquals(actProductHeader,"MacBook Pro");
    
    }
 
 
 

}

package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductResultPageTest extends BaseTest {
	 @BeforeClass
	 public void productInfoSetup() {
	 accPage=loginpage.doLogIn(prop.getProperty("username"),prop.getProperty("password")); 
		 }
	 @DataProvider
	 public  Object[] []getSearchData() {
		 return new Object[][] {
			 {"MacBook","MacBook Pro",4},
			 {"Samsung", "Samsung SyncMaster 941BW",1},
			 {"iMac","iMac",3},
			 
		 };
	 }
	 @Test(dataProvider="getSearchData")
	 public void productImagesTest(String search,String ProductName,int imgCount) {
    searchResultsPage= accPage.doSerch(search);
    productInfoPage=searchResultsPage.selectProduct(ProductName);
    Assert.assertEquals(productInfoPage.getProductImgCount(),imgCount);	 
		 
	 }
   @Test
	 public void productInfoTest() {
   searchResultsPage=accPage.doSerch("MacBook");
   productInfoPage= searchResultsPage.selectProduct("MacBook Pro");
 Map<String,String >productDetailMap= productInfoPage.getProductDeatailIs();
  softassert.assertEquals(productDetailMap.get("Brand"),"Apple");
  softassert.assertEquals(productDetailMap.get("Availability"),"In Stock");
  softassert.assertEquals(productDetailMap.get("Product Code"),"Product 18");
  softassert.assertEquals(productDetailMap.get("Reward Points"),"800");
  softassert.assertEquals(productDetailMap.get("price"),"$2,000.00");
  softassert.assertEquals(productDetailMap.get("ExTaxPrice"),"$2,000.00");
  softassert.assertAll();
  
	 }
	 

		
	
}

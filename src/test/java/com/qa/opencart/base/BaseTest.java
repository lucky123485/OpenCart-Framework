 package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.tests.AccountPageTest;

public class BaseTest {
    WebDriver driver; 
    protected Properties prop;
    DriverFactory df;
    protected LoginPage loginpage;
    protected AccountPage accPage;
    protected SearchResultsPage searchResultsPage;
    protected ProductInfoPage productInfoPage;
    protected RegisterPage registerPage;
    protected SoftAssert softassert;
    
    @Parameters({"Browser"})
    @BeforeTest
    public void setup(String BrowserName) {
     df=new DriverFactory();
     
     prop=df.initProp();
     
     if(BrowserName!=null) {
    	 prop.getProperty("Browser", BrowserName);
     }
     
   driver=df.initDriver(prop);
   
    loginpage=new LoginPage(driver);
 
    softassert =new SoftAssert();
    	  
    	
    	
    }
    @AfterTest
    public void tearDown() {
    	driver.quit();
    }
}

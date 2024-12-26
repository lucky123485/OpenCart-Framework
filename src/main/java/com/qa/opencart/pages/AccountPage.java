package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constante.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
   private WebDriver driver;
   
   ElementUtil ele;
   
   private By search=By.xpath("//input[@type='text']");
   private By logoutLink=By.linkText("Logout");
   private By textlink=By.xpath("//div[@id='content']/h2");
   private By searchIcon=By.xpath("//span[@class='input-group-btn']/button");
 
	
   public AccountPage(WebDriver driver){
	   this.driver=driver;
	   ele=new ElementUtil(this.driver);
   }
    
    public String getAccPageTitle() {
    String title=	ele.WaitForTitleIs(AppConstants.ACCOUNT_Page_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
    System.out.println(title);
    return title;
    }
    public String getAccPageUrl() {
   String url= 	driver.getCurrentUrl();
   System.out.println(url);
   return url;
    }
    
    public boolean accPageSearch() {
    return	ele.waitforVisibilityOfElement(search,AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
    }
    
    public boolean accPageLogoutLinkExist() {
    	return ele.waitforVisibilityOfElement(logoutLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
    }
    public void logout() {
    	if(accPageLogoutLinkExist()) {
    		ele.getclick(logoutLink);
    	}
    }
    
    
	public List<String> accPageHeader() {
	List <WebElement> header=	ele.waitforVisibilityOfElements(textlink, AppConstants.SHORT_DEFAULT_WAIT);
	List<String> headerList=new ArrayList<String>();
	
	for(WebElement e:header) {
	String text=	e.getText();
	headerList.add(text);
	}
	return headerList;
	
	}
	
	public SearchResultsPage doSerch(String searchKey) {
		ele.waitforVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		ele.waitforVisibilityOfElement(search,AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(searchKey);
		ele.getclick(searchIcon);
		return new SearchResultsPage(driver);
		
	
	}

}

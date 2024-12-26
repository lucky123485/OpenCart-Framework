 package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class ElementUtil {
	
	
	
private	WebDriver driver;
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
	}
	
	@Step("entering value {1} to element: {0}")
	public void dosendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	@Step("Clicking on element: {0}")
   public  void getclick(By locatore) {
	getElement(locatore).click();
}
   public  String doElementGetTextBy(By locatore) {
		return getElement(locatore).getText();
	}
   public String doGetElementAttributeval(By locator,String Attributeval) {
	  return getElement(locator).getAttribute(Attributeval);
   }
  
   
   
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	//Write the function:capture of the all the pages links and returm List<String>
	
	public  List<String>getElementsTextList(By locator) {
	List<WebElement>eleList=getElements(locator);
	List<String> eleTextList=new ArrayList<String>(); 
	for(WebElement e:eleList) {
	String text=e.getText();
	if (text.length()!=0) {
		eleTextList.add(text);
	}
	
	}
	return eleTextList;
	
		
	}
	// write a function capture attribute from the list
	public  List<String> getElementsAttributeList(By locator, String AttriName) {
	List<WebElement> AttributeList =getElements(locator);
	List <String> EleTextList=new ArrayList<String>();
	for(WebElement e:AttributeList) {
		String Text=  e.getAttribute(AttriName);
		  EleTextList.add(Text);
	}
	return EleTextList;	
	}
	public void clickonElement(By locator,String value) {
		List<WebElement> valuetext	= getElements(locator);
		System.out.println(valuetext.size());
		
		for(WebElement e:valuetext) {
			String text=e.getText();
			System.out.println(text);
			if(text.contains(value)) {
				e.click();
				break;
			}
		
		}
	}
	
	public  int getTotalcount(By locator) {
		return getElements(locator).size();
	}
	
	public  List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	//**********************************select dropdown utils**********************************************
	
	private Select createSelect(By locator) {
		Select select=new Select(getElement(locator));
		return select;
	}
	
    public  void DoSelectDropdownByIndex(By locator, int index) {
   	// Select select= new Select(getElement(locator));
    	createSelect(locator).selectByIndex(index);
   	 //select.selectByIndex(index);
 	 
    }
    public  void SelectByVisibleText(By locator, String visible) {
   //	 Select select= new Select(getElement(locator));
    	createSelect(locator).selectByVisibleText(visible);
   //	 select.selectByVisibleText(visible);
    }
    public  void SelectByValue(By locator,String value) {
  // 	 Select select=new Select(getElement(locator));
    	createSelect(locator).selectByVisibleText(value);
   //	 select.selectByValue(value);
    }
    
    public  int getDropDownOptionCount(By locator) {
	//	Select select=new Select(getElement(locator));
    	
	return createSelect(locator).getOptions().size();
		
	}
	public  List<String> getDropDownoptions(By locator) {
		Select select=new Select(getElement(locator));
	List<WebElement> optionList=	select.getOptions();
	List<String> optionTextList=new ArrayList<String>();
	for(WebElement e:optionList) {
	String text=e.getText();
	optionTextList.add(text);
	}
	return optionTextList;
	}
	 
	public  void SelectCountry(By locator, String value) {
		Select select=new Select(getElement(locator));
		List <WebElement>countryName=select.getOptions();
		for(WebElement e: countryName) {
		String name=	e.getText();
		System.out.println(name);
		if(name.equals(value)) {
			e.click();
			break;
		}
				}
		
	}


	public  void clickCountryOption(By locator , String value) {
		List<WebElement> option=	getElements(locator);
		for(WebElement e: option) {
		String ab=	e.getText();
		   if(ab.equals(value)) {
		    	 e.click();
		    	 break;
		   }
		}

}
	
	public  boolean isDropDownMultiple(By locator) {
		//Select select= new Select(getElement(locator));
	return	createSelect(locator).isMultiple() ? true  : false;
	}
	
	// This method is used the select value from the dropdown it can select
	//1 single selection
	//2. multiple selection
	//3 select all
	
	
	public  void SelectMultipleDropdownValue(By locator, By optionlocator,String...  values) {
	//	Select select=new Select(getElement(locator));
		
		if(isDropDownMultiple(locator)) {
			
			if(values[0].equalsIgnoreCase("all")) {
				List <WebElement> optionList= getElements(optionlocator);
				for(WebElement e:optionList) {
					e.click();
				}
			}
				else {
					for(String value: values) {
						createSelect(locator).selectByVisibleText(value);
					}
				}
			}
		}
	//******************************Actions class utility**************************************************************
	
	 
	 public  void twoLevelMenuHandle(By parentMenulocator,By childMenuLocator) throws InterruptedException {
		 Actions act =new Actions(driver);
		 act.moveToElement(getElement(parentMenulocator)).build().perform();
		 Thread.sleep(2000);
		 getclick(childMenuLocator);
	 }	
	 
	 public  void fourLevelMenuHandle(By parentShopMenu,By FirstchildMenuLocator ,By SecondChildMenuLocator,By ThirdChildMenuLocator) throws InterruptedException {
			Actions act= new Actions(driver);
			Thread.sleep(2000);
			getclick(parentShopMenu);
			Thread.sleep(2000);
			act.moveToElement(getElement(FirstchildMenuLocator)).build().perform();
			Thread.sleep(2000);
			act.moveToElement(getElement(SecondChildMenuLocator)).build().perform();
			Thread.sleep(2000);
			getclick(ThirdChildMenuLocator);
		}
	 
	  public  void doActionsSendKeys(By locator,String value) {
    	  Actions act=new Actions(driver);
    	  act.sendKeys(getElement(locator),value).build().perform();
      }
      public  void doActionsClick(By locator) {
    	  Actions act=new Actions(driver);
    	  act.click(getElement(locator)).build().perform();
      }
      
      
      //*******************************wait utils**************************************************************************//

      
    //**An expectation for checking that an element is present on the DOM of a page. 
  	//**This does not necessarily mean that the element is visible
      
      public  WebElement waitForPresenceOfElement(By locator,int Timeout) {
  		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Timeout));
  	return	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
  		
  	}
      //override
      public  WebElement waitForPresenceOfElement(By locator,int Timeout,int IntervalTime) {
    		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Timeout),Duration.ofSeconds(IntervalTime));
    	return	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    		
    	}
      //**An expectation for checking that there is at least one element present on a web page.//
      public  List<  WebElement> waitForPresenceOfElements(By locator,int Timeout) {
    		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Timeout));
    	return	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    		
    	}
        
      
      
      //***An expectation for checking that an element, known to be present on the DOM of a page, is visible. 
      //***Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
      @Step("waiting for the element: {0} , timeout: {1}")
  	public WebElement waitforVisibilityOfElement(By locator,int Timeout) {
  		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(Timeout));
  	return	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  	}
  	
	public WebElement waitforVisibilityOfElement(By locator,int Timeout,int IntervalTime) {
  		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(Timeout),Duration.ofSeconds(IntervalTime));
  	return	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  	}
  	
  	
  	//**An expectation for checking that all elements present on the web page that match the locator are visible.
  	//**Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
  	
  	 public List<WebElement> waitforVisibilityOfElements(By locator,int Timeout) {
  		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(Timeout));
  	 return	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
  	}
  	
  	
  	
  	
  	public  void DoclickWithWait(By locator ,int Timeout) {
  		waitforVisibilityOfElement(locator,Timeout).click();
  	}
  	public  void dosendKeysWithwait(By locator,String value,int Timeout) {
  		waitforVisibilityOfElement(locator,Timeout).sendKeys(value);
  		
  	}
	
    public  String WaitForTitleContains(String titleFraction,int Timeout) {
		   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(Timeout));
		   
      	try {		   
			if  (wait.until(ExpectedConditions.titleContains(titleFraction))){
				return driver.getTitle();
			}
      	}catch(TimeoutException e) {
      		System.out.println(titleFraction + "   title value is not present");
      		e.printStackTrace();
      	}
      	return null;
	}
    @Step("Waiting for the Page title: {0} and timeout:{1} ")
 	public   String WaitForTitleIs(String title, int Timeout) {
		 WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(Timeout));
		 try {
		 if(wait.until(ExpectedConditions.titleIs(title))) {
		 return	 driver.getTitle();
		 }
		 }catch(TimeoutException e) {
			 System.out.println(title+  "    title is not present");
			 e.printStackTrace();
		 }
		 return null;
	}
 	
 	public   String WaitForURlContains(String URLfraction, int Timeout) {
		 WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(Timeout));
		 try {
		 if(wait.until(ExpectedConditions.urlContains(URLfraction))) {
		 return	 driver.getTitle();
		 }
		 }catch(TimeoutException e) {
			 System.out.println(URLfraction+  "    title is not present");
			 e.printStackTrace();
		 }
		 return null;
	}
	
	public   String WaitForURlToBe(String URL, int Timeout) {
		 WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(Timeout));
		 try {
		 if(wait.until(ExpectedConditions.urlToBe(URL))) {
		 return	 driver.getTitle();
		 }
		 }catch(TimeoutException e) {
			 System.out.println(URL+  "    title is not present");
			 e.printStackTrace();
		 }
		 return null;
	}
	  public  void waitforFrameBylocator(By framelocator,int Timeout) {
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(Timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framelocator));
	}
	 public  void clickElementWhenReady(By locator,int Timeout) {
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Timeout));
		 
		 try {
		 wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		 }
		 catch(TimeoutException e) {
			 System.out.println("element is not clickable or enabled");
		 }
	 }
	  
	  //************Fluent Wait utility********************************************************************
	  
	 public  WebElement fluentWaitvisibility(By locator,String value,int Timeout,int IntervalTimeout) {
		 Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
	                .withTimeout(Duration.ofSeconds(Timeout))
	                .pollingEvery(Duration.ofSeconds(IntervalTimeout))
	                .withMessage(value)
	                .ignoring(NoSuchElementException.class);
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
    //******************
	 public  WebElement retringElement(By locator,int Timeout ) {
			WebElement element =null;
			
			int attempts=0;
			
			while(attempts<Timeout) {
				try {
			   element=getElement(locator);
			   System.out.println("element is found"    +locator+   "in attempts"   +attempts);
			   break;
			}
			
			catch(NoSuchElementException e) {
				
			System.out.println("element is not found"    +locator+ "in attempts"     +attempts);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			attempts++;	
		}
			 if(element==null) {
				 System.out.println("element is not found tried for"     +Timeout);
			 }
			 return element;
	}	

	
}
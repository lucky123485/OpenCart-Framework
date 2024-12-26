package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constante.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	
	private WebDriver driver;
	ElementUtil ele;
	
	private By proudctHeader= By.cssSelector("div#content h1");
	private By productImage=By.xpath("//ul[@class='thumbnails']/li");
	private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String,String> productMap=new HashMap<String,String>();
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		ele=new ElementUtil(this.driver);
	}
	
	public String getProductHeaderName() {
     String productHeaderVal=		ele.doElementGetTextBy(proudctHeader);
      System.out.println("product header: "    + productHeaderVal);
     return productHeaderVal; 
	}
	
	public int getProductImgCount() {
     int imgCount=ele.waitforVisibilityOfElements(productImage, AppConstants.MEDIUM_DEFAULT_WAIT).size();
      System.out.println("product:"   +getProductHeaderName() + "imagesCount :"     +imgCount);
      return imgCount;
		
	}
	   
//	 Brand: Apple
//	 Product Code: Product 18
//	 Reward Points: 800
//	 Availability: In Stock
	public void getProductMetaData() {
    List<WebElement> metaDataList=		ele.waitforVisibilityOfElements(productMetaData, AppConstants.MEDIUM_DEFAULT_WAIT);
     for(WebElement e:metaDataList) {
    String metaData= 	 e.getText(); //Brand:Apple
   String metaKey=    metaData.split(":")[0].trim();
   String metaVal=metaData.split(":")[1].trim();
   productMap.put(metaKey, metaVal);
   
     }
	}
	
	public void getProductPriceData() {
    List<WebElement> metaPriceList=		ele.waitforVisibilityOfElements(productPriceData, AppConstants.MEDIUM_DEFAULT_WAIT);
   String productPrice=metaPriceList.get(0).getText();
   String productExTaxPrice=metaPriceList.get(1).getText().split(":")[1].trim();
   
   productMap.put("price",productPrice);
   productMap.put("ExTaxPrice", productExTaxPrice);
	}
	
	public Map<String, String> getProductDeatailIs() {
		productMap.put("productName", getProductHeaderName());
		getProductMetaData();
		getProductPriceData();
	//	System.out.println(productHeader);
		return productMap;
		
	}
	
	
	
	
	
	
}

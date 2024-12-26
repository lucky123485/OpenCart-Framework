package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptUtil {
	
	private WebDriver driver;
	private JavascriptExecutor js;
	
	public JavaScriptUtil(WebDriver driver) {
		this.driver=driver;
		
		js=(JavascriptExecutor)this.driver;
		
	}
	
	public String getTitleByjs() {
		return js.executeScript("return document.title").toString();
	}
	
	public String getURLByjs() {
	return	js.executeScript("return document.URL").toString();
	}
	
	public void goBackwardWithJS() {
		js.executeScript("history.go(-1)");
	}
	
	public void goForwarWithJS() {
		js.executeScript("history.go(1)");
	}
	
	public void RefreshWithJs() {
		js.executeScript("history.go(0)");
	}
	
	public String getPageInnnerText() {
	return 	js.executeScript("return document.documentElement.innerText").toString();
	}
    
	public void ScrollMiddlePageDown() {
		js.executeScript( "window.scrollTo(0,document.body.scrollHeight/2)");
	}
	
	public void ScrollPageDown() {
		js.executeScript( "window.scrollTo(0,document.body.scrollHeight)");
		
		
	}
	
	public void ScrollPageDown(String Height) {
		js.executeScript( "window.scrollTo(0,'"+Height+"')");
	}
	
	public void  ScrollPageUp() {
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
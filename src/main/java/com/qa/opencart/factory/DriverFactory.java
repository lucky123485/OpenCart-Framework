package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.Exception.FrameWorkException;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;


  
public class DriverFactory {
    WebDriver driver;
    Properties prop;
    OptionsManager optionsManager;
    
    public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>(); 
    		
    public WebDriver initDriver(Properties prop) {
    	String BrowserName=prop.getProperty("browser");
    //	String BrowserName=System.getProperty("browser");
    	System.out.println("Browser Name:"   +BrowserName);
    	
    	optionsManager=new OptionsManager(prop);
    	
    	switch (BrowserName.toLowerCase().trim()) {
		case "chrome":
		//	driver=new ChromeDriver(optionsManager.getChromeOption());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOption()));
			break;
		case "firefox":
		//	driver=new FirefoxDriver(optionsManager.getFirefoxOption());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOption()));
			break;
		case "edge":
		//	driver=new EdgeDriver(optionsManager.getEdgeOption());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOption()));
			break;

		default:
			System.out.println("Please pass the right browser: "    +BrowserName);
			throw new FrameWorkException("Browser is incorrect");
		 
			
		}
    	getDriver().manage().window().maximize();
    	getDriver().manage().deleteAllCookies();
    	getDriver().get(prop.getProperty("url"));
    	return getDriver();
    }	
    public static WebDriver getDriver() {
    	return tlDriver.get();
    }
    	
    
    public Properties  initProp() {
    //maven clean install -Denv="qa"
    FileInputStream ip=null;
    prop=new Properties();
      
    String envName=   System.getProperty("env");
     System.out.println("env name is:"    +envName);
     try {
     if(envName == null) {
    	 System.out.println("env is null  test cases run on qa env"   );
    	ip=new FileInputStream("./src/test/resources/config/config.qa.properties") ;
     }else {
    	 
    	 switch (envName.toLowerCase().trim()) {
		case "qa":
			ip=new FileInputStream("./src/test/resources/config/config.dev.properties");
			
			break;
		case "dev":
			ip=new FileInputStream("/src/test/resources/config/config.dev.properties");
			
			break;
		case "stage":
			ip=new FileInputStream("./src/test/resources/config/config.stage.properties");
			
			break;
		case "uat":
			ip=new FileInputStream("./src/test/resources/config/config.uat.properties");
			
			break;
			
		case "prod":
			ip=new FileInputStream("./src/test/resources/config/config.properties");
			
			break;
			default:
			System.out.println("PLese Pass the riight env name:"    + envName);
             throw new FrameWorkException("Wrong envName:"     +envName);
			
		}
     }
    	 
    	 
     } catch(FileNotFoundException e){
    	 
     }
    	 
     try {
		prop.load(ip);
	} catch (IOException e) {
				e.printStackTrace();
	}
     return prop;
     
    	
    
    	
    }
}

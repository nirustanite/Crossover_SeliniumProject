package com.crossover.e2e;

import java.io.File;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Random;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class GMailTest extends TestCase {
    private WebDriver driver;
    private Properties properties = new Properties();
    private String randomString_Subject="",randomString_Body="";
    
    public void setUp() throws Exception {
    	
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Parthasarathy\\chrome_driver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);
        properties.load(new FileReader(new File("test.properties")));
    }

    public void tearDown() throws Exception {
    	driver.close();
    	
        driver.quit();
    }

    @Test
    public void testSendEmail() throws Exception {
        driver.get("https://mail.google.com/");
        WebElement userElement = driver.findElement(By.id("identifierId"));
        userElement.sendKeys(properties.getProperty("username"));

        driver.findElement(By.id("identifierNext")).click();

        Thread.sleep(1000);

        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(properties.getProperty("password"));
        driver.findElement(By.id("passwordNext")).click();

        Thread.sleep(1000);

        WebElement composeElement = driver.findElement(By.xpath("//*[@role='button' and (.)='COMPOSE']"));
        composeElement.click();

        driver.findElement(By.name("to")).clear();
        driver.findElement(By.name("to")).sendKeys(properties.getProperty("username")+"@gmail.com");
        randomString_Subject = RandomGenrator();
        driver.findElement(By.name("subjectbox")).clear();
        driver.findElement(By.name("subjectbox")).sendKeys("Random_Subject_"+randomString_Subject);
        randomString_Body = RandomGenrator();
        driver.findElement(By.xpath("(//*[@aria-label='Message Body'])[2]")).clear();
        driver.findElement(By.xpath("(//*[@aria-label='Message Body'])[2]")).sendKeys("Random_Subject_"+randomString_Body);
        driver.findElement(By.xpath("//*[@role='button' and text()='Send']")).click();
    }
    
   public String RandomGenrator()
   {
	   String alphabet= "abcdefghijklmnopqrstuvwxyz";
       String s = "";
       Random random = new Random();
       int randomLen = 1+random.nextInt(9);
       for (int i = 0; i < randomLen; i++) {
           char c = alphabet.charAt(random.nextInt(26));
           s+=c;
       }
	   
	   return s;   
   }
}

package com.crossover.e2e;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
        Thread.sleep(1000);
        driver.findElement(By.name("to")).clear();
        driver.findElement(By.name("to")).sendKeys(properties.getProperty("username")+"@gmail.com");
        randomString_Subject = RandomGenrator();
        driver.findElement(By.name("subjectbox")).clear();
        driver.findElement(By.name("subjectbox")).sendKeys("Random_Subject_"+randomString_Subject);
        randomString_Body = RandomGenrator();
        driver.findElement(By.xpath("(//*[@aria-label='Message Body'])[2]")).clear();
        driver.findElement(By.xpath("(//*[@aria-label='Message Body'])[2]")).sendKeys("Random_Body_"+randomString_Body);
        
        // Create a file in user.home/crossover_file_upload directory
        String path = System.getProperty("user.home") + File.separator + "cfileupload" + File.separator + "u.txt";
        System.out.println(path);
        File file = new File(path);
        file.getParentFile().mkdirs();
        if (file.createNewFile()){
        	System.out.println("File is created!");
        }else{
        	System.out.println("File already exists.");
        }
        FileWriter writer = new FileWriter(file);
        writer.write("Test data");
        writer.close();
        
        driver.findElement(By.xpath(("//*[@class='a1 aaA aMZ']"))).click();
        //attachmentElement.click();
        Thread.sleep(3000);
        Robot rb = new Robot();
        Thread.sleep(2000);
        rb.keyPress(KeyEvent.VK_C);
        rb.keyRelease(KeyEvent.VK_C);
        rb.keyPress(KeyEvent.VK_F);
        rb.keyRelease(KeyEvent.VK_F);
        rb.keyPress(KeyEvent.VK_I);
        rb.keyRelease(KeyEvent.VK_I);
        rb.keyPress(KeyEvent.VK_L);
        rb.keyRelease(KeyEvent.VK_L);
        rb.keyPress(KeyEvent.VK_E);
        rb.keyRelease(KeyEvent.VK_E);
        rb.keyPress(KeyEvent.VK_U);
        rb.keyRelease(KeyEvent.VK_U);
        rb.keyPress(KeyEvent.VK_P);
        rb.keyRelease(KeyEvent.VK_P);
        rb.keyPress(KeyEvent.VK_L);
        rb.keyRelease(KeyEvent.VK_L);
        rb.keyPress(KeyEvent.VK_O);
        rb.keyRelease(KeyEvent.VK_O);
        rb.keyPress(KeyEvent.VK_A);
        rb.keyRelease(KeyEvent.VK_A);
        rb.keyPress(KeyEvent.VK_D);
        rb.keyRelease(KeyEvent.VK_D);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        
        rb.keyPress(KeyEvent.VK_U);
        rb.keyRelease(KeyEvent.VK_U);
        rb.keyPress(KeyEvent.VK_PERIOD);
        rb.keyRelease(KeyEvent.VK_PERIOD);
        rb.keyPress(KeyEvent.VK_T);
        rb.keyRelease(KeyEvent.VK_T);
        rb.keyPress(KeyEvent.VK_X);
        rb.keyRelease(KeyEvent.VK_X);
        rb.keyPress(KeyEvent.VK_T);
        rb.keyRelease(KeyEvent.VK_T);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        
        
        Thread.sleep(3000);
        //attachmentElement.sendKeys("C:\\Users\\bakparthasarathy\\Desktop\\niruuuuuuu\\Crossover_SeliniumProject\\file_upload.txt");
        driver.findElement(By.xpath("//*[@role='button' and text()='Send']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@title='Refresh']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//*[contains(text(),'Random_Subject_"+randomString_Subject+"')])[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//*[contains(text(),'Random_Body_"+randomString_Body+"')])[3]"));
        driver.findElement(By.xpath("//*[@class='aYv']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(text(), 'Test data')]"));
      
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

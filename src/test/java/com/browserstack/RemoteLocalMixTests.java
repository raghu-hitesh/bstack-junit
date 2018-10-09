package com.browserstack;

import static org.junit.Assert.*;
import java.net.URL;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RemoteLocalMixTests{

  @Test
  public void localTest() throws Exception {
    WebDriver driver = setUp("localTest Pass");
    System.out.println("Executing local test");
    driver.get("https://www.google.com/ncr");
    WebElement element = driver.findElement(By.name("q"));
    element.sendKeys("BrowserStack");
    element.submit();
    Thread.sleep(5000);

    assertEquals("BrowserStack - Google Search", driver.getTitle());
     driver.quit();

  }

  @Test
  public void nonBrowserStackPassTest(){
    assertEquals("1","1");
  }

  @Test
  public void nonBrowserStackFailTest(){
    assertEquals("1","2");
  }

    public static WebDriver setUp(String name) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        WebDriver driver = null;
        capabilities.setCapability("browser","Chrome");
        capabilities.setCapability("name",name);
        capabilities.setCapability("project","hit_bstack");
        capabilities.setCapability("build","hit_bstack");

        String username = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        System.out.println("Shabbir Debug - BROWSERSTACK_USERNAME " + username);
        System.out.println("Shabbir Debug - BROWSERSTACK_ACCESS_KEY " + accessKey);
        System.out.println("Shabbir Debug - BROWSERSTACK_ACCESS_KEY " + accessKey);
        String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
        String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
        System.out.println("Shabbir Debug - BROWSERSTACK_LOCAL " + browserstackLocal);
        System.out.println("Shabbir Debug - BROWSERSTACK_LOCAL_IDENTIFIER " + browserstackLocalIdentifier);
        capabilities.setCapability("browserstack.local", browserstackLocal);
        capabilities.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);

        try{
            driver = new RemoteWebDriver(new URL("http://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), capabilities);
            System.out.println("Driver Created");
        }catch(Exception e){
            e.printStackTrace(System.out);
            System.out.println("RemoteWebDriver failed:" + e);
        }
        return driver;
        
    }
}

package BrowserStack;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver ;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class programUsingSeleniumJar {

	WebDriver driver;
	
	@BeforeMethod
	public void setUp() throws MalformedURLException, InterruptedException {
		
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("start-maximized");
		DesiredCapabilities caps = new DesiredCapabilities(); 
		caps.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new RemoteWebDriver(new URL("http://192.168.1.2:4444/wd/hub"), caps);
		driver.get("https://www.browserstack.com/users/sign_in");
		Thread.sleep(1000);
	}
	
	 @Test(priority=1)
	    public void LogintoPortal() throws InterruptedException {
	    	// driver.findElement(By.id("user_email_login")).sendKeys(Keys.CONTROL + "a");
	        //driver.findElement(By.id("user_password")).sendKeys(Keys.CONTROL + "a");
	    //	 driver.findElement(By.id("user_email_login")).clear();
	    //	 driver.findElement(By.id("user_password")).clear();
	    	Thread.sleep(1000);
	    	driver.findElement(By.xpath("//*[@id=\"accept-cookie-notification\"]")).click();
	        driver.findElement(By.id("user_email_login")).sendKeys(System.getenv("browserstack_username"));
	        driver.findElement(By.id("user_password")).sendKeys(System.getenv("browserstack_password")); 
	        driver.findElement(By.id("user_submit")).click();
	        Thread.sleep(1000);
	        String loginSuccessMsg = "You are already part of  team";
	        String checkLoginSuccess = driver.findElement(By.xpath("/html/body/main/div[2]/div[3]/section/h3")).getText();
	        assert loginSuccessMsg.equals(checkLoginSuccess);
	    }
	

	  @Test(priority=2) public void LaunchLive() throws InterruptedException {
		  Thread.sleep(1000); 
		  driver.get("https://live.browserstack.com/dashboard");
		  Thread.sleep(1000); 
	  }
	 
   
   @Test(priority=3)
   public void LaunchBrowser() throws InterruptedException {
   	   // 	driver.findElement(By.xpath("/html/body/main/div/div[21]/div/div[2]/div/div[2]/div[2]/div[1]/div[1]/div/div/span")).click() ;
   		driver.findElement(By.xpath("//*[@id=\"platform-list-react\"]/div/div[2]/div/div[2]/div[3]/div[1]/div[1]/div/div")).click();
   		Thread.sleep(5000);
   }
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.*;
import java.net.URL;
import java.sql.DriverPropertyInfo;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class InstaDriver {
	
	//instagram images loaded initally: 8x3 grid = 24 images
	//loads another 4x3 grid for each scroll down. (7 scrolls are needed)
	//needs tabs
	
	//Global Variables
	public static WebDriver driver;
	public ArrayList<BufferedImage> finalimages = new ArrayList<>();
	public int fiindex = 0;
	public String username;
	public int corpusSize;
	
	//initializes the 
	public InstaDriver(String susername, int scorpusSize){
		this.username = susername;
		this.corpusSize = scorpusSize;
		
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Frank Volk\\Documents\\Comp Sci Projects\\Letterbot\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\Frank Volk\\Documents\\Comp Sci Projects\\Letterbot\\chromedriver.exe");
		System.out.println("Attempting to launch WebDriver.");
		//FirefoxOptions options = new FirefoxOptions();
        //options.addPreference("log", "{level: severe}"); //.setLogLevel(Level.SEVERE); //
		driver = new FirefoxDriver();
		System.out.println("Driver Launched.");
		InstaScrape(username, corpusSize);
	}
	
	//scrapes the current page and builds image repository
	public void InstaScrape(String username, int corpusSize){
		String url;
		//finalimages = new BufferedImage[corpusSize];
		
		//navigate to page
		driver.get("https://www.Instagram.com/"+username+"/");
		
		//grab requisite information
		List<WebElement> e = driver.findElements(By.className("g47SY"));
		System.out.println("Number of Posts: "+e.get(0).getText());
		System.out.println("Number of Followers: "+e.get(1).getText());
		System.out.println("Number of Following: "+e.get(2).getText());
		
		//scroll down to open whole page
		scrollDown(5); //takes 7x3 seconds = 21 seconds
		
		//TODO needs to open in new tab
		
		//e = driver.findElements(By.className("eLAPa"));
		WebElement h;
		int j=1;
		int count =0;
		boolean breakflag = false;
		for (; !breakflag; j++){
			
			if (j==14){
				System.out.println("Scroll Down Triggered.");
				scrollDown(5);
				j=1;
				pause(1);
			}
			
			for (int i=1; i<=3; i++){
				//grabs the href URL attribute of each image in row j column i
				
				try{
					h = driver.findElement(By.xpath("/html[1]/body[1]/span[1]/section[1]/main[1]/div[1]/div[2]/article[1]/div[1]/div[1]/div["+j+"]/div["+i+"]/a[1]"));
				}
				catch(NoSuchElementException ee){
					h = driver.findElement(By.xpath("/html[1]/body[1]/span[1]/section[1]/main[1]/div[1]/div[3]/article[1]/div[1]/div[1]/div["+j+"]/div["+i+"]/a[1]"));
					                  // Looking for /html[1]/body[1]/span[1]/section[1]/main[1]/div[1]/div[3]/article[1]/div[1]/div[1]/div[18]/div[2]/a[1]
									     //Exists as /html[1]/body[1]/span[1]/section[1]/main[1]/div[1]/div[2]/article[1]/div[1]/div[1]/div[12]/div[2]/a[1]
				}
				url = sideGrabber(h); //h.getAttribute("href")
				
				finalimages.add(imgScrape(url));
				
				
			}
			
			count++;
			if (count == Math.ceil((corpusSize/3))){
				breakflag = true;
			}
		}
		
		
	}
	
	//quits the driver and makes sure everything is closed.
	public void close(){
		driver.quit();
		System.out.println("Driver closed.");
	}
	
	//pauses for n seconds
	public static void pause(int n){
		try {
			Thread.sleep(n*100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//appends the URL of the current page and navigates to new URL
	public static void appendAndGo(String a){
		String url = driver.getCurrentUrl();
		String newurl = url+a;
		driver.get(newurl);
	}
	
	//scrapes the image source URL from page
	public static String pgsrcScraper(String pgsrc){
		if (pgsrc == "0"){
			return pgsrc;
		}
		
		//TODO there is a way to get high quality photos, you have to look for the config field and then work backwards to image path associated with that config field.
		int index = pgsrc.indexOf("og:image\" content=");
		pgsrc = pgsrc.substring(index+19);
		index = pgsrc.indexOf(".jpg");
		pgsrc = pgsrc.substring(0, index+4);
		
		return pgsrc;
	}
	
	//scrolls down the page n number of times
	public static void scrollDown(int n){
		if (n==0){
			return;
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,10000)");
		pause(20);
		scrollDown(n-1);
	}
	
	//opens a new page and returns image source link
	public static String sideGrabber(WebElement url){
		String pgsrc;
		
		//open in new tab
		url.sendKeys(Keys.CONTROL, Keys.RETURN);
		pause(10);
		
		//store current tabs in list
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		//driver.get(url); .getAttribute("href")
		pause(10);
		
		//check if is video, set it to the error value zero if it is
		if (!notVideoCheck()){
			pgsrc = "0";
		}
		//scrape page source
		pgsrc = driver.getPageSource();
		
		//close tab
		driver.close();
		
		//return to original window
		driver.switchTo().window(tabs.get(0));
		
		return pgsrcScraper(pgsrc);
	}

	private static boolean notVideoCheck() {
		try{
			driver.findElement(By.className("B2xwy _3G0Ji PTIMp videoSpritePlayButton"));
		}
		catch(NoSuchElementException e){
			return true;
		}
		
		System.out.println("Video Encountered, skipping.");
		return false;
	}
	
	private BufferedImage imgScrape(String imgSRC){
		BufferedImage img = null;
		if (imgSRC == "0"){
			return null;
		}
		
		System.out.println(imgSRC);
		
		try {
			URL imgURL = new URL(imgSRC);
			pause(1);
			img = ImageIO.read(imgURL);
			
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return img;
	}
	
	public ArrayList<BufferedImage> returnImages(){
		return finalimages;
	}
}

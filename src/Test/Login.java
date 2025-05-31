package Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login {

	// TODO Auto-generated method stub

	ChromeOptions options = new ChromeOptions();
	WebDriver driver;

	@BeforeMethod
	public void wakeupbrowser() throws InterruptedException {
		System.setProperty("WebDriver.Chrome.options","D:\\Browser Drivers\\Chrome Drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Browser opened");
		System.out.println("Browser opened1");
		System.out.println("Browser opened2");
		System.out.println("Browser opened3");
		System.out.println("Browser opened4");
		System.out.println("Browser opened5");
		System.out.println("Browser opened6");
		
		driver.get("https://ultimateqa.com/automation");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='../complicated-page']")));
		element.click();
		
		String linkName = element.getText();
		@SuppressWarnings("deprecation")
		String linkTitle = element.getAttribute("href");

		System.out.println("Opened link is : " + linkName + " Open URL : " + linkTitle);

	}

	@Test
	public void links() {

		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total Links on Page are :" + links.size());

		System.out.println("Link Title and URL are as below :");

		for (WebElement link : links)

		{
			String linkText = link.getText();
			@SuppressWarnings("deprecation")
			String linkURL = link.getAttribute("href");

			System.out.println("Link Title is : " + linkText + "URL is : " + linkURL);
			
			if (linkURL != null && !linkURL.isEmpty()) {
				
				checkBrokenLink(linkURL);
			}
		}

	}

	@SuppressWarnings("unused")
	private By tagName (String string) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public static void checkBrokenLink(String linkURL) {
		try {
			// Create a URL object
			@SuppressWarnings({ "deprecation" })
			URL url = new URL(linkURL);

			// Open a connection to the URL
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set a timeout to prevent hanging
			connection.setConnectTimeout(5000); // 5 seconds
			connection.setReadTimeout(5000); // 5 seconds

			// Get the HTTP response code
			connection.connect();
			int statusCode = connection.getResponseCode();

			// If the status code is not 200, it's considered a broken link
			if (statusCode != 200) {
				System.out.println("Broken link: " + linkURL + " -> Status Code: " + statusCode);
			} else {
				System.out.println("Valid link: " + linkURL + " -> Status Code: " + statusCode);
			}

		} catch (IOException e) {
			// If an exception occurs, print the error message
			System.out.println("Error checking link: " + linkURL + " -> Error: " + e.getMessage());
		}
	}

	@AfterMethod
	public void closebrowser() {
		driver.quit();
		System.out.println("Browser Closed");
	}

}

package lionParcel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class Basic_Functions {
	public WebDriver driver;
	WebElement element = null;

	@BeforeMethod
	@Parameters({"url"})
	public void URL(String url) {
//	public void URL(){
		String exePath = "D:\\Drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);
//		driver.get("http://liondtdtest.cargoflash.com/Index.aspx");
//		driver.get("http://elexyslite.cargoflash.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void TextBox_Name(String Name, String Text) {
		driver.findElement(By.name(Name)).sendKeys(Text);
	}

	public void TextBox_ID(String ID, String Text) {
		driver.findElement(By.id(ID)).sendKeys(Text);
	}

	public void TextBox_XPath(String ID, String Text) {
		driver.findElement(By.xpath(ID)).sendKeys(Text);
	}

	public void TextBox_Clear(String ID) {
		driver.findElement(By.id(ID)).clear();
	}

	public void Click_Xpath(String ID) {
		driver.findElement(By.xpath(ID)).click();
	}
	public void Click_ID(String ID) {
		driver.findElement(By.id(ID)).click();
	}

	public void Button_Xpath(String ID) {
		driver.findElement(By.xpath(ID)).click();
	}

	public void Button_ID(String ID) {
		driver.findElement(By.id(ID)).click();
	}

	public void DD_Xpath(String SearchKey, String City, String Code) {

	}

	public void DD_Xpath(String Search_Path, String SearchKey) {
		Click_Xpath(Search_Path);
		TextBox_XPath("//body/span[1]/span/span[1]/input", SearchKey);
		String Xpath = "//li[contains(text(),'" + SearchKey + "')]";
		Button_Xpath(Xpath);

	}

	// public void Get_id(String ID){
	// String Text=driver.findElement(By.id(ID)).getText();
	// }
	public void GetText_Xpath(String ID, int no) {
		String Text = driver.findElement(By.xpath(ID)).getText();
		if (no == 1) {
			System.out.println("STT No." + Text);
		}
	}

	public static void sleep(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// @AfterMethod
	public void Logout() {
		driver.quit();
	}
}

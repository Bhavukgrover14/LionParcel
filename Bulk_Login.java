package lionParcel;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Bulk_Login extends MasterClass{
	
//	//@BeforeMethod
//		public void URL(){
//
//			String exePath = "D:\\Drivers\\chromedriver.exe";
//			System.setProperty("webdriver.chrome.driver", exePath);
//			driver = new ChromeDriver();
//			driver.manage().deleteAllCookies();
//			driver.manage().window().maximize();
//			driver.get("http://liondtdStaging.cargoflash.com/Login.aspx");
//			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
//	}


	
	@DataProvider
	public Object[][] GetLoginData(){
		Object data[][]=TestUtil.GetTestData("Login");
		return data;
	}
	@Test(dataProvider="GetLoginData")
	public void LoginData(String UID, String PWD){
		driver.findElement(By.id("txtUser")).sendKeys(UID);
		driver.findElement(By.id("txtPassword")).sendKeys(PWD);
		driver.findElement(By.id("login_btn")).click();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		String URL=driver.getCurrentUrl().toString();
		assertEquals(URL,"http://liondtdtest.cargoflash.com/Index.aspx","Login Successfully");
	}
	@AfterMethod
	public void Logout()
	{
		driver.quit();
	}
	
	
}

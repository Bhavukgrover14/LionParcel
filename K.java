package lionParcel;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class K extends MasterClass{

	
	
	@DataProvider
	public Object[][] GetLoginData(){
		Object data[][]=TestUtil.GetTestData("K");
		return data;
	}
	@Test(dataProvider="GetLoginData")
	public void KD(String B_Name, String B_Day, String B_Month, String B_Year, String B_Hours,
			String B_MINT, String B_SEC, String B_PB, String G_Name, String G_Day, String G_Month,
			String G_Year, String G_Hours, String G_MINT, String G_SEC, String G_PB){
		sleep(5);
		Alert alert=driver.switchTo().alert();
		alert.dismiss();	
		TextBox_ID("name1", B_Name);
		sleep(1);

		Select oSelect = new Select(driver.findElement(By.id("day1")));
		oSelect.selectByValue("15");
//		DropD("day1", B_Day);
		
		sleep(1);
		DropD("month1", B_Month);
		sleep(1);
		TextBox_ID("year1", B_Year);
		sleep(1);
		DropD("hrs1", B_Hours);
		sleep(1);
		DropD("min1", B_MINT);
		sleep(1);
		DropD("sec1", B_SEC);
		sleep(1);
		TextBox_ID("place1", B_PB);
	}
	public void DropD(String ID, String Text){
		Select oSelect = new Select(driver.findElement(By.id(ID)));
		 
		oSelect.selectByValue(Text);
	}
}

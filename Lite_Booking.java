package lionParcel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Lite_Booking extends MasterClass {
	@DataProvider
	public Object[][] GetLoginData(){
		Object data[][]=TestUtil.GetTestData("NewBooking");
		return data;
	}
	@Test(dataProvider="GetLoginData")
	public void New_Booking_Excel(String Booking_SNO,String UID, String PWD,String Dest_City_Code,String Dest_Name,
			String Shipper_Name,String Shipper_Address,String Shipper_Mob,String Cons_Name,
			String Cons_Address,String Cons_Mob,String External_STT,String Manual_STT_No,String Pcs,String GrossWt,
			String ValueOfGoods,String IsCOD,String COD_Amount,String Commodity,String Product,
			String Is_Regpack,String Is_Insurance,String Type, String Is_WoodenCharges){
		System.out.println("Lite Booking SNO> "+Booking_SNO+"	");
		TextBox_Name("Username", UID);
		TextBox_Name("Password", PWD);
		Button_Xpath("//button[text()='Submit']");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		sleep(2);
		//Dest City
		Dest_Lite(Dest_Name);
		ShipperDetails_Lite(Shipper_Name,Shipper_Address,Shipper_Mob);
		Consigneedetail_Lite(Cons_Name,Cons_Address,Cons_Mob);
		External_STT("STT_Eksternal", External_STT);
		Manual_STT_Number_Lite(Manual_STT_No);	
		Product_Lite(Product);
		Commodity(Commodity);		
		//Insurance
		String INS_Print=Insurance_Lite(Is_Insurance,ValueOfGoods);
		//COD AMount
		String CODPrint=ISCOD_Amount_Lite(IsCOD, COD_Amount);
		WoodenCharges_Lite(Is_WoodenCharges);
//		System.out.print();
//		Pcs		
		STT_Pcs_Lite(Pcs, GrossWt);
		//		WriteExcel(STT);
		Button_ID("BtnGetRate");
		sleep(2);
		if(driver.findElement(By.id("snackbarWarning")).isDisplayed()){
			String WarningMsg=driver.findElement(By.id("snackbarWarning")).getText();
			System.out.println("Warning Message:-"+WarningMsg+"Shipment Not Booked");			
		}
		else{
			RateBreakUp_Lite();
			sleep(2);
//			((JavascriptExecutor)driver).executeScript("window.scrollBy(0,-1000)");
//			Button_ID("btnsaveBooking");
			Button_ID("btnSaveAndPrint");
//			Button_ID("btnSaveAndPrintLabel");
			sleep(2);
			String Alert_Msg=driver.switchTo().alert().getText();
			System.out.println(" Lite_Booking Done > "+Booking_SNO+INS_Print+"-"+CODPrint+" :- "+Alert_Msg);
			driver.switchTo().alert().accept();
//			Button_Xpath("//tr[@class='persist-header floatingHeader']//input[@value='Selesai'][@name='operation']");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
//		driver.switchTo().window(newTab.get(0));
//			String STT=driver.findElement(By.xpath("//form[@id='aspnetForm']/table/tbody/tr[2]/td/div/div[3]/table/tbody/tr[1]/td[2]/span")).getText();
//			System.out.print("	Booking Done ->	"+STT);
//			driver.switchTo().defaultContent();
//			driver.close();
		//	driver.quit();
	}
}

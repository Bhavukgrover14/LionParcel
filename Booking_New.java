package lionParcel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Booking_New extends MasterClass{
	
	@DataProvider
	public Object[][] GetBookingData(){
		Object data[][]=TestUtil.GetTestData("NewBooking");
		return data;
	}
	@Test(dataProvider="GetBookingData")	
	public void New_Booking_Excel(String Booking_SNO,String UID, String PWD,String Dest_City_Code,String Dest_Name,
			String Shipper_Name,String Shipper_Address,String Shipper_Mob,String Cons_Name,
			String Cons_Address,String Cons_Mob,String External_STT,String Manual_STT_No,String Pcs,String GrossWt,
			String ValueOfGoods,String IsCOD,String COD_Amount,String Commodity,String Product,
			String Is_Regpack,String Is_Insurance,String Type, String Is_WoodenCharges){
		System.out.println("New Booking SNO> "+Booking_SNO);
		driver.findElement(By.id("txtUser")).sendKeys(UID);
		driver.findElement(By.id("txtPassword")).sendKeys(PWD);
		driver.findElement(By.id("login_btn")).click();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		sleep(2);
		Booking_Link(1);
		//Booking Details
		driver.switchTo().frame("iMasterFrame");
		driver.findElement(By.xpath("//form[@id='aspnetForm']//input[@value='New Booking']")).click();
		//Dest City
		DestArea(Dest_Name, Dest_City_Code);
		// Shipper details
		ShipperDetails(Shipper_Name,Shipper_Address,Shipper_Mob);
		Consigneedetail_NewBooking(Cons_Name,Cons_Address,Cons_Mob);
		//External STT
		External_STT("ExternalSTTNumber",External_STT);
		Manual_STT_Number(Manual_STT_No);	
//		Pcs		
		STT_Pcs(Pcs, GrossWt);
		//Commodity
		Commodity_New(Commodity);
//		DD_Xpath("select2-Product-ip-container", Commodity);
		//ValueOfGoods
		if(ValueOfGoods!=null){
			sleep(1);
		TextBox_ID("_temptxtBoxinsurance", ValueOfGoods);
		}

		//COD AMount
		String COD_Print=ISCOD_Amount(IsCOD, COD_Amount);
		//Product
		Product_Name(Is_Regpack, Product);
		//Insurance
		String INS_Print=Insurance_Name(Is_Insurance, Type);
//		driver.findElement(By.id("")).click();
		sleep(1);
		Button_ID("lbgetRate");
		sleep(2);
		Button_Xpath("//tr[@class='persist-header floatingHeader']//input[@value='Selesai'][@name='operation']");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String STT=driver.findElement(By.xpath("//form[@id='aspnetForm']/table/tbody/tr[2]/td/div/div[3]/table/tbody/tr[1]/td[2]/span")).getText();
		System.out.println(" New_Booking Done > "+Booking_SNO+INS_Print+"-"+COD_Print+" :- "+STT);
		driver.switchTo().defaultContent();
		Logout();
//		driver.close();
//		AvailableLimit(LoginType);
//		System.out.println();
//		WriteExcel(STT);
	}

}

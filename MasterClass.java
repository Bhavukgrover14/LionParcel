package lionParcel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MasterClass extends Basic_Functions{
	
	public void Booking_Link(int z)
	{
		WebElement element = driver.findElement(By.linkText("Shipment"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		sleep(2);
		// Click on Booking
		if(z==1){
			driver.findElement(By.linkText("Booking")).click();			
		}
		else if(z==2){
			driver.findElement(By.linkText("STT Listing")).click();			
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.switchTo().frame("iMasterFrame");
	}
	public void DestArea(String Dest_Name,String Dest_City_Code){
		try{
			driver.findElement(By.id("Text_ToCitySNo")).sendKeys(Dest_Name);
			sleep(2);
//			System.out.println(Dest_Name +" "+ Dest_City_Code);
			driver.findElement(By.xpath("//span[text()='"+Dest_Name+" ["+Dest_City_Code+"] ']")).click();			
		}
		catch(Exception e){
		System.out.println("	City Not Found	");	
		}
	}
	
	public void ShipperDetails(String Shipper_Name,String Shipper_Address,String Shipper_Mob){
		try{
			WebElement sCheckBox = driver.findElement(By.cssSelector("input[id='CBShipperOrigin']"));
			sCheckBox.click();
			TextBox_ID("ShipperName", Shipper_Name);
			TextBox_ID("ShipperAddress", Shipper_Address);
			TextBox_ID("ShipperPhone", Shipper_Mob);	
		}catch(Exception e){
			
		}
	}
	
	public void Consigneedetail_NewBooking(String Cons_Name,String Cons_Address,String Cons_Mob) {
		try{
			WebElement cCheckBox = driver.findElement(By.cssSelector("input[id='CBConsigneeName']"));
			cCheckBox.click();
			TextBox_ID("ConsigneeName", Cons_Name);
			TextBox_ID("ConsigneeAddress", Cons_Address);
			TextBox_ID("ConsigneePhone", Cons_Mob);	
		}catch(Exception e){
			
		}
	}
	public String Manual_STT_Number(String Manual_STT_No){
//		Manual STT
		String Print;
		try{
			if(!Manual_STT_No.equals(null)&& !Manual_STT_No.equals("NULL")&&
					!Manual_STT_No.equals("0")&&!Manual_STT_No.isEmpty()){
//				TextBox_ID("ExternalSTTNumber", Manual_STT_No);
				TextBox_ID("Text_POSWaybillNo", Manual_STT_No);
				sleep(2);
				driver.findElement(By.xpath("")).click();
				Print="Manual STT";
			}else{
				Print="Auto Generated STT";
			}
		}catch(Exception e){
			Print="STT Not Found";
		}
		return Print;
	}
	public void STT_Pcs(String Pcs , String GrossWt ){
		
		try{
			driver.findElement(By.xpath("//input[@id='Pieces']")).clear();
			TextBox_ID("Pieces", Pcs);
			int TotalPcs=Integer.parseInt(Pcs);
			int j=2;
			for(int i=1;i<=TotalPcs;i++){
				driver.findElement(By.xpath("//table[@id='tblDimUnit']/tbody/tr["+j+"]/td[6]/input[@id='PPGWt']")).sendKeys(GrossWt);
				if(j<=TotalPcs){
					j++;				
				}
			}			
		}catch(Exception e){
			
		}
 	}
	public String ISCOD_Amount(String IsCOD, String COD_Amount){
		String COD=null;
		try{
			Boolean ab=driver.findElement(By.xpath("//input[@id='IsCOD'][@value='0']")).isDisplayed();
//			Assert.assertEquals(true, ab.isDisplayed());
			if(ab==true){
				if(COD_Amount== null&& (IsCOD=="1"||IsCOD=="YES"||IsCOD=="Y")){
					driver.findElement(By.xpath("//input[@id='IsCOD'][@value='0']")).click();
					COD="Value of Goods applied in COD";
					System.out.println("Value of Goods applied in COD"+"	");	
				}
				else if(IsCOD.equals("1")||IsCOD.equals("YES")||IsCOD.equals("Y")){
					driver.findElement(By.xpath("//input[@id='IsCOD'][@value='0']")).click();
					driver.findElement(By.id("_tempCODAmount")).click();
					sleep(1);
					driver.findElement(By.id("CODAmount")).clear();
//					TextBox_Clear("_tempCODAmount");
					TextBox_ID("CODAmount", COD_Amount);
					COD="COD Applied";
					System.out.println("COD applied	");	
				}
				else if(IsCOD==null|| IsCOD=="N"||IsCOD=="NO"||IsCOD=="0"){
					COD="COD not Applied";
					System.out.println("COD not Applied	");			
				}
				else{
					COD="Invalid Value > "+IsCOD;
					System.out.println("Invalid Value > "+IsCOD+"	");
				}
			}
			else
			{
				COD="COD not Shown";
				System.out.println("COD not Shown	");			
			}			
		}catch(Exception e){
			COD="COD Amount Exception > " +e;
			System.out.println("COD Amount Exception > " +e);
		}
		return COD;
	}
	public void Commodity_New(String Commodity){
	//	driver.findElement(By.id("")).sendKeys(Commodity);
		try{
			TextBox_ID("Text_CommoditySNo", Commodity);
			sleep(2);
			driver.findElement(By.xpath("//span[contains(text(),'"+Commodity+"')]")).click();			
		}catch(Exception e){
			
			
		}
	}
	public void Product_Name(String Is_Regpack, String Product){
		driver.findElement(By.xpath("//input[@value='"+Product+"']")).click();
		if(Product.contains("REGPACK")){
			try{
				Boolean RPlus=driver.findElement(By.id("idRegpackplus")).isDisplayed();			
				if(RPlus==true){
					if(Is_Regpack.equals("Y") || Is_Regpack.equals("YES") || Is_Regpack.equals("1")){			
						driver.findElement(By.xpath("//input[@value='YES'][@id='idRegpackplus']")).click();
						System.out.println("RegPackPlus Applied	");
					}			
					else if(Is_Regpack.equals("N") || Is_Regpack.equals("NO") || Is_Regpack.equals("0") || Is_Regpack.equals(null)){			
						driver.findElement(By.xpath("//input[@value='NO'][@id='idRegpackplus']")).click();
						System.out.println("RegPackPlus Not Applied	");
					}			
					else{
						System.out.println("Invalid Value"+Is_Regpack+"	");
					}
					
				}		
				else{
					System.out.println("RegPackPlus Not Shown	"); 					
				}

			}catch(Exception e){
				System.out.println("Exception: "+e);
			}			
		}
	}
	public String Insurance_Name(String Is_Insurance, String Type){
		String Print;
		try{
			if(Is_Insurance.contains("1")||Is_Insurance.contains("YES")||Is_Insurance.contains("Y")){
				driver.findElement(By.id("checkboxinsurance")).click();			
				driver.findElement(By.id("Text_SNo")).click();
				driver.findElement(By.id("Text_SNo")).clear();
			//	driver.findElement(By.id("")).sendKeys(Type);
				TextBox_ID("Text_SNo", Type);
				sleep(2);
				driver.findElement(By.xpath("//span[contains(text(),'"+Type+"')]")).click();
				Print="Insurance Applied	";
			}
			else if(Is_Insurance.contains("0")||Is_Insurance.contains("NO")||Is_Insurance.contains("N")){
				//driver.findElement(By.id("checkboxinsurance")).isSelected();
//				System.out.printlnln("Insurance Not Applied"+Is_Insurance);
				Print="Insurance Not Applied	";
			}
			else{
				Print="Invalid Value ";
//				System.out.println("Invalid Value"+Is_Insurance );
			}
		}catch(Exception e){
			Print="Exception"+e;
//			System.out.println("Exception"+e);
		}
		return Print;
	}
	
	public void Dest_Lite(String CityName){
	Click_Xpath("//span[text()='Select Tujuan']");
	DD_Xpath("//body/span[1]/span/span[1]/input",CityName);	
		
	}
	public void ShipperDetails_Lite(String Shipper_Name,String Shipper_Address,String Shipper_Mob){
		try{
			TextBox_ID("AsalNamaFinal", Shipper_Name);
			TextBox_ID("AsalAlamatFinal", Shipper_Address);
			TextBox_ID("AsalPhoneFinal", Shipper_Mob);			
		}catch(Exception e){
			
		}
	}
	
	public void Consigneedetail_Lite(String Cons_Name,String Cons_Address,String Cons_Mob) {
		try{
			TextBox_ID("TujuanNamaFinal", Cons_Name);
			TextBox_ID("TujuanAlamatFinal", Cons_Address);
			TextBox_ID("TujuanPhoneFinal", Cons_Mob);			
		}catch(Exception e){
			
		}
	}
	public String External_STT(String ID, String External_STT){
		String Print="";
		try{
			if(!External_STT.equals(null)&& !External_STT.equals("NULL")&&
					!External_STT.equals("0")&&!External_STT.isEmpty()){
				TextBox_ID(ID, External_STT);
				Print="External STT";
			}
		}
		catch(Exception e){
			Print="Exception > "+e;
		}
		return Print;
	}
	
	public String Manual_STT_Number_Lite(String STT_no){
		String Print="";
		try{
			if(!STT_no.equals(null)&& !STT_no.equals("NULL")&&!STT_no.equals("0")&&!STT_no.isEmpty()){
				Click_Xpath("//span[text()='Select STT']");
				DD_Xpath("//input[@type='search']", STT_no);
				Print="Manual STT";	
			}
			else{
//				System.out.print("Auto Generated STT	");
			Print="Auto Generated STT";
			}
		}catch(Exception e){
			Print="Error:- Manual STT Not Found";
		}
		return Print;
	}
	public void Product_Lite(String Product){
		try{
			DD_Xpath("//body/div[3]/div[2]//form/div[6]/div[1]/span[1]/span/span/span[1]", Product);
			}catch(Exception e){
			
		}
	}
	public void Commodity(String Commodity){
		try{
			DD_Xpath("//body/div[3]/div[2]//form/div[6]/div[2]/span[1]/span/span/span[1]", Commodity);
		}catch(Exception e){
			
		}
	}
	public String ISCOD_Amount_Lite(String IsCOD, String COD_Amount){
		String Print;
		try{
			Boolean ab=driver.findElement(By.xpath("//label[text()='Nilai COD']")).isDisplayed();
//			Assert.assertEquals(true, ab.isDisplayed());
			if(ab==true){
				if(COD_Amount== null&& (IsCOD=="1"||IsCOD=="YES"||IsCOD=="Y")){
					Click_ID("//input[@id='same-cod']");
//					System.out.println("Value of Goods applied in COD	");	
					Print="Value of Goods applied in COD  ";
				}
				else if(IsCOD.equals("1")||IsCOD.equals("YES")||IsCOD.equals("Y")){
					Click_Xpath("//label[text()='Nilai COD']");
//					driver.findElement(By.id("txtClearValues")).click();
					driver.findElement(By.xpath("//input[@id='SameCOD']")).clear();
//					TextBox_Clear("_tempCODAmount");
					TextBox_ID("SameCOD", COD_Amount);
//					System.out.println("COD applied  ");	
					Print="COD applied	";
				}
				else if(IsCOD==null|| IsCOD=="N"||IsCOD=="NO"||IsCOD=="0"){
//					System.out.println("COD not Applied  ");			
					Print="COD not Applied	";
				}
				else{
//					System.out.println("Invalid Value > "+IsCOD+"	");
					Print="Invalid Value ";
				}
			}
			else
			{
				Print="COD not Shown	";
//				System.out.println("COD not Shown	");			
			}			
		}catch(Exception e){
			Print="COD Amount Exception > " +e;
//			System.out.println("COD Amount Exception > " +e);
		}
		return Print;
	}
	public String Insurance_Lite(String Is_Insurance, String ValueOfGoods){
		String Print;
		try{
			TextBox_ID("email", ValueOfGoods);
			if(Is_Insurance.contains("1")||Is_Insurance.contains("YES")||Is_Insurance.contains("Y")){
				Click_Xpath("//label[@for='same-asuransi']");
				//				System.out.println("Insurance Applied	");
				Print="Insurance Applied	";
			}
			else if(Is_Insurance.contains("0")||Is_Insurance.contains("NO")||Is_Insurance.contains("N")){
//				System.out.println("Insurance Not Applied"+Is_Insurance+"	");
				Print="Insurance Not Applied"+Is_Insurance+"	";
			}
			else{
//				System.out.println("Invalid Value"+Is_Insurance +"	");
				Print="Invalid Value"+Is_Insurance +"	";
			}
		}catch(Exception e){
//			System.out.println("Exception:>  "+e);
			Print="Exception:>  "+e;
		}
		return Print;
	}
	public void WoodenCharges_Lite(String Is_Insurance){
		try{
			if(Is_Insurance.contains("1")||Is_Insurance.contains("YES")||Is_Insurance.contains("Y")){
				Click_Xpath("//label[@for='same-packing']");
				System.out.println("WoodenCharges Applied	");
			}
			else if(Is_Insurance.contains("0")||Is_Insurance.contains("NO")||Is_Insurance.contains("N")){
				System.out.println("WoodenCharges Not Applied"+Is_Insurance+"	");
			}
			else{
				System.out.println("Invalid Value"+Is_Insurance +"	");
			}
		}catch(Exception e){
			System.out.println("Exception:>	"+e);
		}
	}
	public void STT_Pcs_Lite(String Pcs , String GrossWt ){
//		driver.findElement(By.xpath("//input[@id='Pieces']")).clear();
//		TextBox_ID("Pieces", Pcs);
//		int TotalPcs=Integer.parseInt(Pcs);
//		int j=2;
//		for(int i=1;i<=TotalPcs;i++){
//			driver.findElement(By.xpath("//table[@class='table table-bordered dimension']/tbody/tr[1]/td[4]/input")).sendKeys(GrossWt);
//			if(j<=TotalPcs){
//				j++;				
//			}	
//		}
		try{
			TextBox_XPath("//table[@class='table table-bordered dimension']/tbody/tr[1]/td[4]/input", Pcs);
			TextBox_XPath("//table[@class='table table-bordered dimension']/tbody/tr[1]/td[5]/input", GrossWt);	
		}catch(Exception e){
			
		}
 	}
	public void HargaBarang(String ValueOfGoods){
		try{
			if(ValueOfGoods!=null&&ValueOfGoods!="Null"&&ValueOfGoods!="0"){
				//TextBox_ID("_temptxtBoxinsurance", ValueOfGoods);
				TextBox_Name("Harga_Barang", ValueOfGoods);
				}			
		}catch(Exception e){
			
		}
	}

	public void RateBreakUp_Lite(){
		try{
			String Freight_Amount=driver.findElement(By.id("TotalFreightLabel")).getText();
			String FA_Amount=driver.findElement(By.id("DestinationForwardRateLabel")).getText();
			String Insurance=driver.findElement(By.id("InsuranceLabel")).getText();
			String WoodenPacking=driver.findElement(By.id("PackagingChargesLabel")).getText();
			String Commodity_sur=driver.findElement(By.id("CommoditySurchargeLabel")).getText();
			String HVWt_Sur=driver.findElement(By.id("HeavyWeightSurchargeLabel")).getText();
			String Total_Amount=driver.findElement(By.id("TotalAmountFinal")).getText();
			System.out.println("Freight_Amount> "+Freight_Amount+"	"+"FA_Amount> "+FA_Amount+"	"+
					"Insurance> "+Insurance+"	"+"WoodenPacking> "+WoodenPacking+"	"+"Commodity_sur> "+
					Commodity_sur+"	"+"HVWt_Sur> "+HVWt_Sur+"	"+"Total_Amount> "+Total_Amount+"	");	
		}catch(Exception e){
			
		}
	}
	public void PrintMsg(){
		
	}
	public void Logout() 
	{
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.close();
	}
}


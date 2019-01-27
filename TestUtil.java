package lionParcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestUtil {

	static XSSFWorkbook book1;
	static XSSFSheet sheet1;
	static String a= System.getProperty("user.dir");
	public static String DataExcelPath=System.getProperty("user.dir")+ "\\src\\lionParcel\\MasterExcel.xlsx";
	
	public static Object[][] GetTestData(String SheetName){
		
		FileInputStream file=null;
		try{
			file=new FileInputStream(DataExcelPath);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		try{
			 book1 = new XSSFWorkbook(file);
		}catch(IOException e){
			e.printStackTrace();			
		}
		sheet1=book1.getSheet(SheetName);
		Object[][] data1=new Object[sheet1.getLastRowNum()][sheet1.getRow(0).getLastCellNum()];
		for(int i=0; i<sheet1.getLastRowNum();i++){
			for(int k=0;k<sheet1.getRow(0).getLastCellNum();k++){
				System.out.println(sheet1.getRow(i+1).getCell(k).toString());
				if (sheet1.getRow(i+1).getCell(k).toString()==null){
					data1[i][k]="213";
				}
				else{
					data1[i][k]=(sheet1.getRow(i+1).getCell(k).toString()).toUpperCase();										
				}
			}
		}
		return data1;
	}

}

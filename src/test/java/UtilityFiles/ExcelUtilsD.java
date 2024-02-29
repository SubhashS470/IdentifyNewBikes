package UtilityFiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelUtilsD 
{

		public void writeDData(String sheetName,String[] a,int rowNo, int colNo) throws IOException {
			FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\ExcelBikes\\Bikes.xlsx");
			XSSFWorkbook workbook=new XSSFWorkbook(file); 
			
			//XSSFSheet sheet = workbook.getSheet(sheetName);
			try {
			
				XSSFSheet sheet= workbook.getSheet(sheetName);	
				for(int i = rowNo; i<a.length; i++)
				{
					
					if(sheet.getRow(i)==null)
					{
						sheet.createRow(i);
					}
					XSSFRow row =sheet.getRow(i);   
					
					row.createCell(colNo).setCellValue(a[i]);
								
				}
			}
			catch(Exception e)
			{
				XSSFSheet sheet =	workbook.createSheet(sheetName);
				for(int i = rowNo; i<a.length; i++) 
				{
					
					if(sheet.getRow(i)==null)
					{
						sheet.createRow(i);
					}
					XSSFRow row =sheet.getRow(i);   
					
					row.createCell(colNo).setCellValue(a[i]);
								
				}
			}
			
			 
			
			 
			FileOutputStream fo=new FileOutputStream(System.getProperty("user.dir")+"\\ExcelBikes\\Bikes.xlsx");
			workbook.write(fo);		
			file.close();
			fo.close();

		}


}

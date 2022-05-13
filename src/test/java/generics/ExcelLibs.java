package generics;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.testng.Reporter;

public class ExcelLibs {
	
	public HSSFSheet getSheetObject(String filepath,String shtName)
	  { 
		HSSFSheet sht = null;
		  
		try{
		  FileInputStream fis = new FileInputStream(filepath);
		  HSSFWorkbook wb = new HSSFWorkbook(fis);
		  sht = wb.getSheet(shtName);	
		}catch(Exception exp)
		{
		  Reporter.log("Error " + exp.getMessage());	
		}
		return sht;
	  }
	
	public String getCellData(HSSFSheet shtobj,int rowno,int cellno)
	  { 
		String out=null;
		  
		try{
		    out = shtobj.getRow(rowno).getCell(cellno).getStringCellValue();	
			
		}catch(Exception exp)
		{
			Reporter.log("Error " + exp.getMessage());	
		}
		return out;
	  }
	  
	public int getRowCount(HSSFSheet shtobj)
	  { 
		int out=0;
		  
		try{
		    out = shtobj.getLastRowNum();	
			
		}catch(Exception exp)
		{
			Reporter.log("Error " + exp.getMessage());	
		}
		return out;
	  }
	public int getCellNumData(HSSFSheet shtobj,int rowno,int cellno)
	  { 
		int out=0;
		  
		try{
		    out = (int) shtobj.getRow(rowno).getCell(cellno).getNumericCellValue();	
			
		}catch(Exception exp)
		{
			Reporter.log("Error " + exp.getMessage());	
		}
		return out;
	  }
	  
	
	public void setCellData(String filepath ,String sheetName,int rowno,int cellno,String input)
	  {
	    
		try
		{
			FileInputStream fis = new FileInputStream(filepath);
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet shtobj = wb.getSheet(sheetName);
	     	Cell c = shtobj.getRow(rowno).createCell(cellno);
	     	c.setCellType(Cell.CELL_TYPE_STRING);
			c.setCellValue(input);
			FileOutputStream fos = new FileOutputStream(filepath);
			wb.write(fos);
			fos.close();
	    }
		catch(Exception exp)
	    {
	      Reporter.log("Error " + exp.getMessage());	
	    }
		  
	  }

}

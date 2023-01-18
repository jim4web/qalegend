package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static XSSFWorkbook w;
	public static XSSFSheet sh;
	public static FileInputStream f;

	public ExcelUtility() throws IOException {
		// obtaining input bytes from a Excelfile
		f = new FileInputStream(
				"C:\\Users\\RS\\eclipse-workspace\\qalegend\\src\\test\\resources\\ExcelFile\\Excelpage.xlsx");
		// creating workbook instance that refers to .xls file
		w = new XSSFWorkbook(f);
		// creating a Sheet object to retrieve the object
		sh = w.getSheet("sheet1");
	}
	public String readData(int row, int column) {
		Row r = sh.getRow(row);
		Cell c = r.getCell(column);
		// get the cell type
		CellType type = c.getCellType();
		switch (type) {
		case STRING: {
			return c.getStringCellValue();
		}
		case NUMERIC: {
			int a = (int) c.getNumericCellValue();
			return String.valueOf(a);
		}
		}
		return null;
	}
}
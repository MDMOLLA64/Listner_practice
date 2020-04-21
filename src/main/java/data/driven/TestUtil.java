package data.driven;

import java.util.ArrayList;

public class TestUtil {
	


	static Xls_Reader reader;

	public static ArrayList<Object[]> getDataFromExel() {
		ArrayList<Object[]> myData = new ArrayList<Object[]>();

		reader = new Xls_Reader("/Users/mdmolla/Documents/DataStore.xlsx");
		for (int rowNum = 2; rowNum <= reader.getRowCount("Sheet1"); rowNum++) {

			String user = reader.getCellData("Sheet1", "user", rowNum);
			String pass = reader.getCellData("Sheet1", "pass", rowNum);

			Object ob[] = { user, pass };
			myData.add(ob);

		}
		return myData;

	}



}

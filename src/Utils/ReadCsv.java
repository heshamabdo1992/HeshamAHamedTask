package Utils;

//Calculator using DDT "Data Driven test" from CSV file.

/*The main steps:
* ==============
* 1- Get file path.
* 2- Create CSV reader.
* -------------------------
* 3- Create Array String.
* 4- Read file values via CSV reader and store it in Array String.
* -------------------------
* 5- Add list of Array String values to 2D array that it can match the DataProvider return type.*/

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;

public class ReadCsv {

	@SuppressWarnings("resource")
	public Object[][] dataProvider_ByCSVReader1(String FilePath) throws IOException {

		// 1&2- Create CSV file reader that use file path.
		CSVReader reader = new CSVReader(new FileReader(FilePath));

		// 3- Create list of Array String.
		// 4- Read file values via CSV reader and store it in list of Array String.
		List<String[]> list = reader.readAll();

// 5.1- Create 2D array with length = list of array length.
		String[][] array = new String[list.size()][];
		// 5.2- Add list of Array String values to 2D array that it can match the DataProvider return type.
		list.toArray(array);

		return array;
	}
	



	@SuppressWarnings("resource")
	public Object[][] dataProvider_ByCSVReader2(String FilePath) throws IOException {

		// 1&2- Create CSV file reader that use file path.
		CSVReader reader = new CSVReader(new FileReader(FilePath));

		// 3- Create list of Array String.
		// 4- Read file values via CSV reader and store it in list of Array String.
		List<String[]> list = reader.readAll();

		// 5.1- Create 2D array String with length = list of array length.
		String[][] array = new String[(list.size()-1)][];

		// 5.2- for loop to pass list(array value) to (2D array)of Strings (String [][]) that it can match the DataProvider return type.
		for (int i = 0; i < list.size()-1; i++) {
			array[i] = list.get(i+1);
		}

		return array;
	}

	public Object[][] dataProvider_ByScanner(String FilePath) throws IOException {

		// 1&2- Create an object Scanner file reader that use file path.
		Scanner scanner = new Scanner(new File(FilePath));

		String line = null;
		String data[] = null;
		// 3- Create list of Array String.
		ArrayList<Object> paramList = new ArrayList<Object>();

		// 5- Create 2D array String.
		String[][] parameters;

		// 4- Read file values via Scanner and store it in list of Array String.
		//a conditional procedure that will be executed only if the file has a next non-empty line
		while (scanner.hasNextLine()) {

			// 4.1- Read the file next line & put it into a string
			line = scanner.nextLine();

			// System.out.println(line);//this line is useful for as it prints every retrieved line for the file

			// 4.2- Convert the line from being a string into an array of strings(String[]) which can be detected by the "," character
			data = line.split(",");

			// 4.3- Add the retrieved array of strings(String[]) as an element (or Object) into an array list of objects (ArrayList<Object>)
			paramList.add(data);
		}
		// by the end of this conditional statement, all the lines will be added as objects into the (ArrayList<Object>)

		// 5- Add list of Array String values to 2D array.
		// Now we need to cast the (ArrayList<Object>) to a Multi-dimensional array of Strings (String [][]) so that it can match the DataProvider return type
		parameters = paramList.toArray(new String[paramList.size()][]);

		scanner.close(); // Closes the scanner (The file reader object)
		return parameters;
	}

}
package entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileManager {
	
	File inputFile;
	File outputFile;
	
	public File getInputFile() {
		return inputFile;
	}
	
	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}
	
	public void initInputFile() {
		try {
			this.inputFile = new File("C:\\Users\\benoni.d\\eclipse-workspace\\Sample\\src\\start_file");
			Scanner scanner = new Scanner(inputFile);
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
	
	public int extractData(char data) {
		return Character.getNumericValue(data);
	}
	

}

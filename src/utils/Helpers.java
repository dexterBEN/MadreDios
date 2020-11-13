package utils;

import java.util.Random;

public class Helpers {
	
	public static int randNumber(int min, int max) 
	{
		Random randInt = new Random();
		return randInt.nextInt((max - min) + 1) + min;
	}
	
	public static int chartToInt(char charValue) {
		int intValue = Character.getNumericValue(charValue);
		return intValue;
	}
}

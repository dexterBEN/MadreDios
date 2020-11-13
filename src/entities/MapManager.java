package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapManager {
	
	private  int width;
	private int height;
	private List<Coordinates> coordinates = new ArrayList<>();
	
	public List<Coordinates> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List<Coordinates> coordinates) {
		this.coordinates = coordinates;
	}
	
	public MapManager() {
		
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void initCoordinates(int width, int height) {
		
		for(int i = 0; i < width; i++) 
		{
			for(int j = 0; j < height; j++) 
			{
				
				coordinates.add(new Coordinates(j, i));
			}
		}
	}
	
	public static int randNumber(int min, int max) {
		Random randInt = new Random();
		return randInt.nextInt((max - min) + 1) + min;
	}
	
}

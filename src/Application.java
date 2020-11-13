
import java.io.BufferedWriter;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Adventurer;
import entities.Coordinates;
import entities.FileManager;
import entities.Item;
import entities.MapManager;
import entities.Mountain;
import entities.Treasure;
import utils.Helpers;

public class Application {
	
	public static boolean checkMountain(List<Mountain> mountains, Coordinates userCoordinates) {
		
		for(Mountain mountain: mountains) {
			if(
				mountain.coordinates.getX() == userCoordinates.getX() &&
				mountain.coordinates.getY() == userCoordinates.getY()
			) {
				return true;
			}
		}
		return false;
	}
	
	public static Treasure checkTreasure(List<Treasure> treasures, Coordinates userCoordinates) {
		
		for(Treasure treasure: treasures) {
			if(
					treasure.coordinates.getX() == userCoordinates.getX() &&
					treasure.coordinates.getY() == userCoordinates.getY()
			) {
				return treasure;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File startFile = null;
		Scanner myReader = null;
		MapManager mapManager = new MapManager();
		List <Mountain> mountainList = new ArrayList<>();
		List <Treasure> treasureList = new ArrayList<>();
		Mountain mountain;
		Treasure treasure;
		Adventurer adventurer = null;
		Coordinates adventurerNextPosition = null;
		int newX, newY;
		int treasureAmount;
		
		
		//File creation can throw exception so to prevent them:
		try {
			
			 startFile = new File("./src/start_file");
			 myReader = new Scanner(startFile);
		}catch (FileNotFoundException e){
			System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
		//Read and extract data from the file:
		if(startFile != null) {
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
		        System.out.println(data);
		        
		        //extract and create Object:
		        switch(data.charAt(0)) {
					case 'C':
						mapManager = new MapManager();
						mapManager.setWidth(Character.getNumericValue(data.charAt(2)));
				    	mapManager.setHeight(Character.getNumericValue(data.charAt(4)));
				    	mapManager.initCoordinates(mapManager.getWidth(), mapManager.getHeight());
				    	break;
					
					case 'M':
						mountain = new Mountain();
						mountain.coordinates = new Coordinates(Character.getNumericValue(data.charAt(2)), Character.getNumericValue(data.charAt(4)));
			        	mountain.setTag(data.charAt(0));
			        	mountainList.add(mountain);
						break;
					
					case 'T':
						treasure = new Treasure();
			        	treasure.setTag(data.charAt(0));
			        	treasure.setAmount(Character.getNumericValue(data.charAt(6)));
			        	treasure.coordinates = new Coordinates(Character.getNumericValue(data.charAt(2)), Character.getNumericValue(data.charAt(4)));
			        	treasureList.add(treasure);
						break;
					
					case 'A':
						adventurer = new Adventurer();
			        	adventurer.setTag(data.charAt(0));
			        	adventurer.coordinates = new Coordinates(Character.getNumericValue(data.charAt(2)), Character.getNumericValue(data.charAt(4)));
			        	break;
		        }
		      }
		     
		      
		      //simulation loop:
		      for(int i = 0; i < 10; i++) 
		      {
		    	  System.out.println("Tour: "+i);
		 
		    	  newX = Helpers.randNumber(0, mapManager.getWidth()-1);
		    	  newY = Helpers.randNumber(0, mapManager.getHeight()-1);
		    	  
		    	  adventurerNextPosition = new Coordinates(newX, newY);
		    	  
		    	  if(checkMountain(mountainList, adventurerNextPosition)) 
		    	  {
		    		  System.out.println("Can't go to this position there is a mountain");
		    		  newX = Helpers.randNumber(0, mapManager.getWidth()-1);
			    	  newY = Helpers.randNumber(0, mapManager.getHeight()-1);
		    	  }
		    	  
		    	  
		    	  adventurer.coordinates.setX(newX);
		    	  adventurer.coordinates.setY(newY);
		    	  
		    	  Treasure foundedTreasure =  checkTreasure(treasureList, adventurer.coordinates);
		    	  
		    	  if(foundedTreasure != null) {
		    		  System.out.println("Treasure founded");
		    		  treasureAmount =  foundedTreasure.getAmount() - 1;
		    		  foundedTreasure.setAmount(treasureAmount); 
		    		  adventurer.takeTreasure(foundedTreasure);
		    		  
		    		  if(foundedTreasure.getAmount() == 0) {
		    			  treasureList.remove(foundedTreasure);
		    		  }
		    		  
		    	  }
		      }
		      
		     //output file
		     try {
				File output = new File("./src/output_file");
				FileOutputStream fos = new FileOutputStream("./src/output_file");
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
				
				
				output.createNewFile();
				  
				bw.write("C-"+mapManager.getWidth()+"-"+mapManager.getHeight());
				bw.newLine();
				
				for(Mountain item: mountainList) 
				{
					bw.write(item.getTag()+"-"+item.coordinates.getX()+"-"+item.coordinates.getY());
					bw.newLine();
				}
				
				for(Treasure item: treasureList) 
				{
					bw.write(item.getTag()+"-"+item.coordinates.getX()+"-"+item.coordinates.getY()+"-"+item.getAmount());
					bw.newLine();
				}
				
				bw.write(adventurer.getTag()+"-"+adventurer.coordinates.getX()+"-"+adventurer.coordinates.getY()+"-"+adventurer.getCollectedTreasure().size());
				bw.newLine();
				
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

}

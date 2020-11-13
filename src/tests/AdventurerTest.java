package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import entities.Adventurer;
import entities.Coordinates;
import entities.Treasure;

class AdventurerTest {

	@Test
	void getCollectedTreasureTest() {
		
		Adventurer adventurer = new Adventurer();
		List<Treasure> collectedTreasure = new ArrayList<>();
		Treasure treasure = new Treasure();
		
		assertEquals(collectedTreasure, adventurer.getCollectedTreasure(), "should not be null");
		
		treasure.setTag('T');
		treasure.coordinates = new Coordinates(2, 3);
		
		collectedTreasure.add(treasure);
		assertEquals(1, collectedTreasure.size(),"should at contain one element at least");
	}

}

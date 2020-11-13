package entities;

import java.util.ArrayList;
import java.util.List;

public class Adventurer extends Item{
	
	private List<Treasure> collectedTreasure = new ArrayList<>();

	public List<Treasure> getCollectedTreasure() {
		return collectedTreasure;
	}

	public void setCollectedTreasure(List<Treasure> collectedTreasure) {
		this.collectedTreasure = collectedTreasure;
	}

	public void takeTreasure(Treasure treasure) {
		collectedTreasure.add(treasure);
	}
	
	
}

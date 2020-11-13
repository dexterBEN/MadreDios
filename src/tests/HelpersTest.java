package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utils.Helpers;

class HelpersTest {

	@Test
	void chartToIntTest() {
		
		assertEquals(5, Helpers.chartToInt('5'));
		assertEquals(-1, Helpers.chartToInt('-'));
	}
	
	@Test
	void randNumberTest() {
		int min = 5;
		int max= 10;
		int val = Helpers.randNumber(min, max);
		
		assertEquals(true, val >= min || val <= max, "should be bounded between min and max");
	}

}

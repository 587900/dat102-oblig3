package no.hvl.dat102.klient;

import java.util.Random;

import no.hvl.dat102.KjedetBSTre;

public class KlientBSTre {

	private static Random random;
	
	public static void main(String[] args) {
		KjedetBSTre<Integer> bstre = new KjedetBSTre<>();

		bstre.leggTil(7);
		bstre.leggTil(5);
		bstre.leggTil(6);
		bstre.leggTil(4);
		bstre.leggTil(9);
		bstre.leggTil(10);
		bstre.leggTil(8);
		bstre.leggTil(3);

		// Tester på sortert utskrift
		System.out.println("Skriver ut elementene sortert i bs-treet");
		bstre.visInorden();

		// Tester på om et bestemt element fins
		int element = 8;
		System.out.println("\nTester paa om elementet " + element + " fins");

		if (bstre.finn(element) != null) {
			System.out.println("Elementet " + element + " fins i bs-treet");
		} else {
			System.out.println("Elementet " + element + " fins ikke i bs-treet");
		}

		element = 1;
		System.out.println("\nTester paa om elementet " + element + " fins");

		if (bstre.finn(element) != null) {
			System.out.println("Elementet " + element + " fins i bs-treet");
		} else {
			System.out.println("Elementet " + element + " fins ikke i bs-treet");
		}

		System.out.println();
		
		System.out.println("Running program 1");
		random = new Random();
		runProgram(1000, 1023);
		System.out.println("Program finished");
		
		System.out.println();
		
		System.out.println("Running program 2");
		runProgram2(100, -5000, 5000, -15000, 15000, (int)Short.MIN_VALUE, (int)Short.MAX_VALUE);
		System.out.println("Program finished");
	}
	
	//3b)
	public static void runProgram(int iterations, int numElements) {
		
		int[] amounts = new int[iterations];
		int[] heights = new int[iterations];
		for (int i = 0; i < iterations; ++i) {
			KjedetBSTre<Integer> tree = randomTree(numElements, Short.MIN_VALUE, Short.MAX_VALUE);	//note: use of MIN_VALUE and MAX_VALUE causes overflow, so random.nextInt(negative) crashes
			amounts[i] = tree.antall();
			heights[i] = tree.hoyde();
		}

		int minTheoreticalHeight = (int)(Math.log(numElements) / Math.log(2));	// log_a(b) = ln(b) / ln(a)
		int maxTheoreticalHeight = numElements - 1;
		
		int minHeight = Integer.MAX_VALUE;
		int maxHeight = Integer.MIN_VALUE;
		float avgHeight = 0;
		
		for (int i = 0; i < iterations; ++i) {
			if (amounts[i] != numElements) 
				System.out.println("ERR: unexpected number of elements from an iteration. [expected, got, iteration]: [" + numElements + ", " + amounts[i] + ", " + i + "]");
			
			if (heights[i] < minTheoreticalHeight || heights[i] > maxTheoreticalHeight) 
				System.out.println("ERR: unexpected height from an iteration. [theoretical min, theoretical max, got, iteration]: [" + minTheoreticalHeight + ", " + maxTheoreticalHeight + ", " + heights[i] + ", " + i + "]");
			
			if (heights[i] < minHeight) minHeight = heights[i];
			if (heights[i] > maxHeight) maxHeight = heights[i];
			avgHeight += heights[i];
		}
		
		avgHeight /= iterations;
		
		System.out.println("Number of nodes: " + numElements);
		System.out.println("Minimum and maximum theoretical heights: " + minTheoreticalHeight + ", " + maxTheoreticalHeight);
		System.out.println("Minimum and maximum measured heights: " + minHeight + ", " + maxHeight);
		System.out.println("Average of all measured heights: " + avgHeight + " from numElements: " + numElements);
		
	}
	
	//3e) ii.
	public static void runProgram2(int numElements, Integer... testCases) {
		
		KjedetBSTre<Integer> tree = randomTree(numElements, Short.MIN_VALUE, Short.MAX_VALUE);
		
		for (int i = 0; i < testCases.length - 1; i+=2) {
			int lower = testCases[i];
			int upper = testCases[i+1];
			System.out.println("Printing tree elements in order with bounds [lower,upper]: [" + lower + "," + upper + "]");
			tree.skrivVerdier(lower, upper);
		}
		
	}
	
	public static KjedetBSTre<Integer> randomTree(int numElements, int minValue, int maxValue) {
		
		KjedetBSTre<Integer> tre = new KjedetBSTre<>();
		
		for (int i = 0; i < numElements; ++i) tre.leggTil(random.nextInt(maxValue-minValue+1)+minValue);
		
		return tre;
		
	}

}

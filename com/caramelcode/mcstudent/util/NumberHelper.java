package com.caramelcode.mcstudent.util;

import java.util.Random;

public class NumberHelper {

	/**
	 * Returns a random number in a range inclusive.
	 * 
	 * @param lowerLimit
	 *            - Inclusive lower limit
	 * @param upperLimit
	 *            - Inclusive upper limit
	 * @return
	 */
	public static int randRange(int lowerLimit, int upperLimit) {
		Random r = new Random();

		if (lowerLimit > upperLimit) {
			return 0;
		}

		return r.nextInt((upperLimit - lowerLimit) + 1) + lowerLimit;
	}

	/**
	 * Inclusive nextInt()
	 * 
	 * @param upperLimit
	 * @return
	 */
	public static int rand(int upperLimit) {
		Random r = new Random();
		return r.nextInt(upperLimit + 1);
	}

	/**
	 * returns true if number is a prime number
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isPrime(int n) {
		return !(new String(new char[n])).matches(".?|(..+?)\\1+");
	}
	
	public static boolean hasRemainder(int leftOperand, int rightOperand) {
		return !((leftOperand % rightOperand) == 0);
	}
}

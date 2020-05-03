package com.jaxer.jvm.memory;

/**
 * VM Args: -Xss160K
 */
public class StackOverflowError {
	private int stackLength = 1;

	private void stackLeak() {
		stackLength++;
		stackLeak();
	}

	public static void main(String[] args) {
		StackOverflowError sof = new StackOverflowError();
		try {
			sof.stackLeak();
		} catch (Throwable ex) {
			System.out.println("stack length: " + sof.stackLength);
			throw ex;
		}
	}
}

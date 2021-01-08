package com.hcl.longestIncreasingSubsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Enter the number of numbers:");
		Scanner scan = new Scanner(System.in);
		int number = scan.nextInt();
		System.out.println("Enter the number of numbers:");
		int[] numbers = new int[number];
		for (int j = 0; j < number; j++) {
			numbers[j] = scan.nextInt();
		}
		System.out.println("Non consecutive longest increasing subsequence/s:");
		// Method for finding Non consecutive LI sequences
		myMethod(numbers);
		System.out.println("Consecutive longest increasing subsequence/s:");
		// Method for finding consecutive LI sequences
		longestSubSequence(numbers);
	}

	private static void myMethod(int[] arr) {
		IncreasingSubsequence longestIncreasingSubsequence = new IncreasingSubsequence();
		List<Integer[]> list = new ArrayList<>();
		int previous = 0;
		// Get all non consecutive LIS in the form of stack with logic
		for (int i = 0; i < arr.length; i++) {
			Stack<Integer> stack = new Stack<>();
			stack.push(arr[i]);
			previous = stack.peek();
			for (int j = i + 1; j < arr.length; j++) {
				if (previous < arr[j]) {
					stack.push(arr[j]);
					previous = stack.peek();
				} else {
					continue;
				}
			}
			Integer[] integer = new Integer[stack.size()];
			// Convert them to array of integers
			integer = longestIncreasingSubsequence.toArray(stack);
			// Add them to the list of integer arrays
			list.add(integer);
			previous = 0;
		}
		// Get the longest LIS alone from this method
		List<Integer[]> result = longestIncreasingSubsequence.longest(list);
		// Print the result
		result.stream().forEach(r -> {
			List l = Arrays.asList(r);
			System.out.println(l);
			System.out.println("The length of the LIS is " + l.size());
		});
	}

	public static void longestSubSequence(int[] arr) {
		IncreasingSubsequence longestIncreasingSubsequence = new IncreasingSubsequence();
		// Get the longest LIS alone from this method
		List<Integer[]> result = longestIncreasingSubsequence.getLongestIncreasingSubsequence(arr);
		// Print the result
		result.stream().forEach(r -> {
			List l = Arrays.asList(r);
			System.out.println(l);
			System.out.println("The length of the LIS is " + l.size());
		});
	}
}
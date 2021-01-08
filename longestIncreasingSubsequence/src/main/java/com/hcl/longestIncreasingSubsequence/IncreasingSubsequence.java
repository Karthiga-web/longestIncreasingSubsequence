package com.hcl.longestIncreasingSubsequence;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IncreasingSubsequence {
	public List<Integer[]> getLongestIncreasingSubsequence(int[] numbers) {
		List<Integer[]> sequences = new ArrayList<>();
		Stack<Integer> entries = new Stack<>();
		// Get all consecutive LIS in the form of stack with logic
		for (int r = 0; r < numbers.length; r++) {
			Integer entry = numbers[r];
			if (entries.empty()) {
				entries.push(entry);
				continue;
			}
			Integer previous = entries.peek();
			if (previous < entry) {
				entries.push(entry);
			}
			boolean atTheEnd = (r == numbers.length - 1);
			if (previous >= entry || atTheEnd) {
				// Convert them to array of integers
				sequences.add(toArray(entries));
				entries.push(entry);
			}
		}
		// Get the longest LIS alone from this method
		return longest(sequences);
	}

	public List<Integer[]> longest(List<Integer[]> candidates) {
		// Get all lengths of all sequences we got from whole list and add the
		// lengths alone in the list
		List<Integer> list = new ArrayList<>();
		candidates.stream().forEach(a -> {
			list.add(a.length);
		});
		// Sort the list
		Collections.sort(list);
		List<Integer[]> resultList = new ArrayList<>();
		// Store the longest LIS alone in a seperate list and return them
		for (int i = 0; i < candidates.size(); i++) {
			if (candidates.get(i).length == list.get(list.size() - 1)) {
				resultList.add(candidates.get(i));
			}
		}
		return resultList;
	}

	public Integer[] toArray(Stack<Integer> entries) {
		// Converting the stack of integers to integer array
		Integer[] sequence = new Integer[entries.size()];
		int idx = entries.size() - 1;
		Integer sequenceEntry = entries.pop();
		while (sequenceEntry != null) {
			sequence[idx] = sequenceEntry;
			try {
				sequenceEntry = entries.pop();
			} catch (EmptyStackException e) {
				break;
			}
			idx--;
		}
		return sequence;
	}
}

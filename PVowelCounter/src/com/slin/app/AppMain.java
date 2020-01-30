package com.slin.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


class VowelCounter {
	public List<String> topWords (String sentence) {
		if (sentence == null || sentence.isEmpty())
			return null;
		else {
			List<String> topWords = new ArrayList<String>();
			List<String> words = getWords (sentence);
			int fCount=-1, sCount = -1, tCount = -1;
			int fIndex = -1, sIndex = -1, tIndex = -1;
			int i=-1;
			for (String word : words) {
				++i;
				int count = getVowelCount(word);
				if (count >= fCount) {
					tCount = sCount;
					tIndex = sIndex;
					
					sCount = fCount;
					sIndex = fIndex;
					
					fCount = count;
					fIndex = i;
				} else if (count >= sCount) {
					tCount = sCount;
					tIndex = sIndex;
					
					sCount = count;
					sIndex = i;
				} else if (count > tCount) {
					tCount = count;
					tIndex = i;
				}
				/*
				System.out.println(fIndex+","+sIndex+", "+tIndex);
				System.out.println(fCount+","+sCount+", "+tCount);
				System.out.println("===========================");
				*/
			}
			addWord (topWords, words, fIndex);
			addWord (topWords, words, sIndex);
			addWord (topWords, words, tIndex);
			
			return topWords;
			
		}
	}
	
	private List<String> getWords (String sentence) {
		
		String[] words = sentence.toLowerCase().split("\\ ");
		List<String> list = Arrays.asList(words);
		//remove duplicates.
		Set<String> set = new HashSet<String>(list);
		list = new ArrayList<String>(set);
		return list;
	}
	
	private void addWord (List<String> list, List<String> words, int index) {
		if (index >= 0 && index < words.size()) {
			list.add(words.get(index));
		}
	}
	
	
	private int getVowelCount (String word) {
		String vowels = "aeiou";
		int count =0;
		for (int i=0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (vowels.indexOf(ch) >= 0) {
				count++;
			}
		}
		//System.out.println("word:"+word+", count:"+count);
		return count;
	}
}
public class AppMain {

	public static void main(String[] args) {
		VowelCounter counter = new VowelCounter();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your sentence.");
		String sentence = scanner.nextLine();
		//System.out.println(sentence);
		List<String> topWordList = counter.topWords(sentence);
		
		if (topWordList == null || topWordList.isEmpty()) {
			System.out.println("Empty list of words");
		} else {
			System.out.println("top words");
			for (String w : topWordList) {
				System.out.println(w);
			}
		}
		scanner.close();
		//scanner.hasNext();
		
	}

}

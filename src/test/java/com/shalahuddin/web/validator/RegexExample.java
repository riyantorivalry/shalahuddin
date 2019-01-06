/**
 *
 */
package com.shalahuddin.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {

	public static void main(String[] args) {
		// using pattern with flags
		Pattern pattern = Pattern.compile("ab", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher("ABcabdAb");
		// using Matcher find(), group(), start() and end() methods
		while (matcher.find()) {
			System.out.println("Found the text \"" + matcher.group()
			+ "\" starting at " + matcher.start()
			+ " index and ending at index " + matcher.end());
		}

		// using Pattern split() method
		pattern = Pattern.compile("\\W");
		String[] words = pattern.split("one@two#three:four$five");
		for (String s : words) {
			System.out.println("Split using Pattern.split(): " + s);
		}

		// using Matcher.replaceFirst() and replaceAll() methods
		pattern = Pattern.compile("1*2");
		matcher = pattern.matcher("11234512678");
		System.out.println("Using replaceAll: " + matcher.replaceAll("_"));
		System.out.println("Using replaceFirst: " + matcher.replaceFirst("_"));

		System.out.println("does 303 is numbers : "+ Pattern.matches("\\d+", "303"));

		// Regular expression in Java to check if String is number or not
		pattern = Pattern.compile(".*[^0-9].*");
		//Pattern pattern = Pattern.compile(".*\\D.*");
		String [] inputs = {"123", "-123" , "123.12", "abcd123"};

		for(String input: inputs){
			System.out.println( "does " + input + " is number : "
					+ !pattern.matcher(input).matches());
		}

		// Regular expression in java to check if String is 6 digit number or not
		String [] numbers = {"123", "1234" , "123.12", "abcd123", "123456"};
		Pattern digitPattern = Pattern.compile("\\d{6}");
		//Pattern digitPattern = Pattern.compile("\\d\\d\\d\\d\\d\\d");


		for(String number: numbers){
			System.out.println( "does " + number + " is 6 digit number : "
					+ digitPattern.matcher(number).matches());
		}
	}

}

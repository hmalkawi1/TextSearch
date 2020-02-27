package com.journaldev.readfileslinebyline;


import java.io.InputStreamReader;
import java.net.URL;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.*;
import java.util.Scanner;

public class ReadFileLineByLineUsingBufferedReader {

	public static void main(String[] args) throws IOException {
		Scanner userIn = new Scanner(System.in); //create a scanner object
		System.out.println("Will you be using a 'Text File' or a 'URL'? please type the options as specified in quotes.");
		String method = userIn.nextLine();

		//TEST CASE FOR WRONG INPUT
		// while (!method.equals("URL") || !method.equals("Text File")){
		// 	//if user input was neither url or text file
		// 	System.out.println("Will you be using a 'Text File' or a 'URL'? please type the options as specified in quotes.");
		// 	method = userIn.nextLine();

		// }
		//IN CASE INPUT IS CORRECT:
		if(method.toLowerCase().equals("text file")){
			System.out.println("Where is the Text File you'd like to search located?");
			String path = userIn.nextLine();  // Read user input
			System.out.println("What word would you like to find the number of occurences for?");
			String word = userIn.nextLine();  // Read user input
			
			findInText(word, path);
		}
		else if(method.toLowerCase().equals("url")){
			System.out.println("Please enter URL you'd like to read through:");
			String urlToSearch = userIn.nextLine();  // Read user URL input
			URL url = new URL(urlToSearch);
			System.out.println("What word would you like to find the number of occurences for?");
			String word = userIn.nextLine();  // Read user word ro search
			findInURL(url, word);
		}
	}

	public static void findInText(String word, String path){
		BufferedReader reader;
		int counter = 0;
		try {
			reader = new BufferedReader(new FileReader(
					path));
			String line = reader.readLine();
			while (line != null) {
				String[] currLine = line.split(" "); //split words into an array
				for (String curr: currLine){
					if(curr.toLowerCase().equals(word.toLowerCase())){
						counter++;
					}
				}
                line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			} 
		System.out.println("Your word " + word + " has shown up: " + counter + " times.");
	}

	//FUNCTION TO SEARCH URLS
	public static void findInURL(URL url, String word){
		int counter = 0;
		try (var br = new BufferedReader(new InputStreamReader(url.openStream()))) {
      String line;
      while ((line = br.readLine()) != null) {
			  String[] currLine = line.split(" "); //split words into an array
				for(String curr: currLine){
				  if(curr.toLowerCase().equals(word.toLowerCase())){
					  counter++;
					}
				 }		
        }
      } catch (IOException e) {
			  e.printStackTrace();
			  } 
		System.out.println("Your word " + word + " has shown up: " + counter + " times.");
	}
}

package com.example.wordcounter;

import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.text.WordUtils;


public class App  {
	public static void main( String[] args ) throws IOException {	
		// create Options object
		Options options = new Options();

		// add t option
		options.addOption("f", true, "filename");
		options.addOption("cw", true, "column-width");
		options.addOption("help", true, "giving help with commands");
		
	    // create the parser
	    CommandLineParser parser = new DefaultParser();
	    
	    try {
	        // parse the command line arguments
	        CommandLine line = parser.parse(options, args);
	        
	        String fileText = "";
	        String charNumber = "";
	        String fullText = "";
	        String someText = "Aidar is a bad boy";
	        int number;
	        
	        if(line.hasOption("help")) {
	        	System.out.println("-f specifies writing a filename, -cw specifies writing a column-width. Please run program again, without calling help");
	        } else {
	        	
	        	if(line.hasOption("f")) {
		        	fileText = line.getOptionValue("f");
		        } else {
		        	System.out.println("Please write a filename");
		        }
		        
		        if(line.hasOption("cw")) {
		        	charNumber = line.getOptionValue("cw");
		        } else {
		        	System.out.println("Please write a column-width");
		        	number = 0;
		        }
		        
		        number = Integer.parseInt(charNumber);
		        
		        try {
		            FileReader reader = new FileReader(fileText);
		            int character;
		 
		            while ((character = reader.read()) != -1) {
		                fullText += (char) character;
		            }
		            reader.close();
		 
		        } catch (IOException exp) {
		            exp.printStackTrace();
		        }
		       
		        System.out.println(WordUtils.wrap(fullText, number));
		        System.out.println("");
		        
		        int countActually = wordcount(someText);
		        System.out.println(someText);
		        System.out.println("There are " + countActually + " words");
	        }
	    }
	    
	    catch(ParseException exp) {
	        // oops, something went wrong
	        System.err.println("Parsing failed. Reason: " + exp.getMessage());
	    }
	}
	
	public static int wordcount(String someText) {
		
		String trim = someText.trim();
		
		if (trim.isEmpty())
		    return 0;
		
		return trim.split("\\s+").length;
	}
}

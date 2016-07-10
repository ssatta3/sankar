package com.BO;
import org.w3c.dom.*;

import javax.xml.parsers.*;

import java.io.*;

public class DataReader 
{
	public void addDate(String date)
	{

    try {
        // Assume default encoding.
        BufferedWriter bufferedWriter =   new BufferedWriter(new FileWriter("Data/date.txt"));

        // Note that write() does not automatically
        // append a newline character.
        bufferedWriter.write(date);
       

        // Always close files.
        bufferedWriter.close();
    }
    catch(IOException ex) {
        
        // Or we could just do this:
        // ex.printStackTrace();
    }
    }
	public String  readDate()
	{
		// The name of the file to open.
        String fileName = "C:\\Users\\sankar sattari\\workspace\\EmployeeBiddingSystem14\\Date\\date.txt";

        // This will reference one line at a time
        String line = null;
        String date = null;

        try {
            // FileReader reads text files in the default encoding.
        	BufferedReader in = new BufferedReader(new FileReader(fileName));

            while((line =in.readLine()) != null) {
            	date = line;
            	System.out.println(line);
            }   

            // Always close files.
            in.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        return date;
    }
	}


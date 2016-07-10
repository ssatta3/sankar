package Server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

public class CodeJam_Pancakes {

	public static int minSwaps(char[] c){
		int x=0;
		for(int i=0;i<c.length-1;i++){
			if(c[i]==c[i+1]){
				continue;
			}else if(c[i]!=c[i+1]){
				x++;
			}
		}
		if(c[c.length-1]=='-'){ return x+1;}
		else{return x;}
		
	}
	public static void main(String[] args) {
		char[] c = {'-','-','+','-'};
		FileReader f=null;
		BufferedReader b=null;
		try {
			f = new FileReader("C:\\Users\\sankar sattari\\workspace\\New folder (2)\\InfoInfra\\CodeJam");
			b = new BufferedReader(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			int n = Integer.parseInt(b.readLine());
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
		int i=1;
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
		              new FileOutputStream("C:\\Users\\sankar sattari\\workspace\\New folder (2)\\InfoInfra\\CodeJamSol2"), "utf-8")); 
			while((line=b.readLine()) != null){
			c = line.toCharArray();
			int p = minSwaps(c);
			
		   writer.write("Case #"+i+": " +p);
		   writer.write('\n');
			//PrintWriter w = new PrintWriter("C:\\Users\\sankar sattari\\workspace\\New folder (2)\\InfoInfra\\CodeJamSol.txt","UTF-8");
			//w.println("Case #1: "+p);
		   
			System.out.println(p);
			i++;
		}} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			   try {writer.close();} catch (Exception ex) {/*ignore*/}
			}
	}
}

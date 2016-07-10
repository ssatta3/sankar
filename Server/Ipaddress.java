package Server;

import java.util.ArrayList;
import java.util.List;

public class Ipaddress {
	 static List<String> l  = new ArrayList<String>();

	 public static void restoreIpAddresses(String s,int j,int index,String res){
		 if(j>4){return;}
		if( j==4 && res.length()==s.length()+4){l.add(res);}
		 
		 for(int i=1;i<=3;i++){
			 if (index+i > s.length()) break;
			 if(Integer.parseInt(s.substring(index,index+i))>255){continue;}
			// res= res+ s.substring(index,index+i)+".";
			 restoreIpAddresses(s, j+1,index+i,res+ s.substring(index,index+i)+".");
		 }
	 }
	public static void main(String[] args) {
		restoreIpAddresses("2525511135",0,0,"");
         for(String s:l){
        	 System.out.println(s);
         }
	}

}

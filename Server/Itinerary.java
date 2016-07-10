package Server;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
class Lexicomparator implements Comparator<String>{

	@Override
	public int compare(String arg0, String arg1) {		
		return arg0.compareTo(arg1);
	}
	
}

public class Itinerary {
	public static List<String> itinerary(String[][] it){
		Comparator<String> comparator = new Lexicomparator();
		//PriorityQueue<String> queue = new PriorityQueue<String>(comparator);
		HashMap<String,PriorityQueue<String>> hm = new HashMap<String,PriorityQueue<String>>();
		for(int i=0;i<it.length;i++){
			if(hm.containsKey(it[i][0])){
				hm.get(it[i][0]).add(it[i][1]);
			}else{
				PriorityQueue<String> queue = new PriorityQueue<String>(comparator);
				queue.add(it[i][1]);
				hm.put(it[i][0],queue);
			}
		}
		List<String> itinerary = new ArrayList<String>();
		String s = hm.get("JFK").poll();
		itinerary.add(s);
	    for(int i=1;i<it.length;i++){
	    	s = hm.get(s).poll();
	    	itinerary.add(s);
	    }
	    return itinerary;
	}
	public static void main(String[] args) {
	
		String[][] tickets = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
		List<String> it = itinerary(tickets);
         for(int i=0;i<it.size();i++){
        	 System.out.println(it.get(i));
         }
	}

}

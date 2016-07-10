package Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class Edge{
	int start;
	int end;
	int weight;
}
class Graph{
	HashMap<Integer,ArrayList<Edge>> hm = new HashMap<Integer,ArrayList<Edge>>();
	
	public void addEdge(Graph g,int start,int end,int weight){
		Edge e1 = new Edge();
		e1.start = start;e1.end =end; e1.weight = weight;
		Edge e2 = new Edge();
		e2.start = end; e2.end = start; e2.weight = weight;
		if(!hm.containsKey(start)){
			hm.put(start,new ArrayList<Edge>());
			hm.get(start).add(e1);
		}else{
			hm.get(start).add(e1);
		}
		if(!hm.containsKey(end)){
			hm.put(end,new ArrayList<Edge>());
			hm.get(end).add(e2);
		}else{
			hm.get(end).add(e2);
		}
	}
	
}

public class Prims {
	
	public List<Edge> prims(Graph g){
		PriorityQueue<Edge> p = new PriorityQueue<Edge>();
		
		median1 = (((end1-start1+1)/2) + ((end1-start1+1)/2) + 1)/2;
		
	}
	
	

	public static void main(String[] args) {
		Graph graph = new Graph();

        graph.addEdge(graph,1, 2, 3);
        graph.addEdge(graph,2, 3, 1);
        graph.addEdge(graph,3, 1, 1);
        graph.addEdge(graph,1, 4, 1);
        graph.addEdge(graph,2, 4, 3);
        graph.addEdge(graph,4, 5, 6);
        graph.addEdge(graph,5, 6, 2);
        graph.addEdge(graph,3, 5, 5);
        graph.addEdge(graph,3, 6, 4);

	}

}

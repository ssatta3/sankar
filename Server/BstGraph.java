package Server;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


 public class BstGraph
{
    private static   Map<Integer, List<Integer>> Adjacency_List;
   
    public BstGraph(int number_of_vertices)
    {
        Adjacency_List = new HashMap<Integer, List<Integer>>();
        for (int i = 1 ; i <= number_of_vertices ; i++)
        {
            Adjacency_List.put(i, new LinkedList<Integer>());
        }
    }


   
    public void setEdge(int source , int destination)
    {
        if (source > Adjacency_List.size() || destination > Adjacency_List.size())
        {
            System.out.println("the vertex entered in not present ");
            return;
        }
        List<Integer> slist = Adjacency_List.get(source);
        slist.add(destination);
        List<Integer> dlist = Adjacency_List.get(destination);
        dlist.add(source);
    }
   static boolean[] visited = new boolean[100];
    
    public static void dfsTraversal(Map<Integer,List<Integer>> adjList, int source){
    	 System.out.print(source +" ");
    	adjList.get(source);
    	Iterator i = adjList.get(source).iterator();
    	visited[source]=true;
    	while(i.hasNext()){
    		int x = (int)i.next();
    		if(!visited[x]){
    			dfsTraversal(adjList, x);
    		}
    	}
    	
    	
    	
    	
    }
    
    public static void bstTraversal(Map<Integer, List<Integer>> adjList, int source){
    	boolean[] visited = new boolean[100];
    	LinkedList<Integer> q = new LinkedList<Integer>();
    	for(int i=0;i<100;i++){
    		visited[i] = false;
    	}
    	adjList.get(source);
    	visited[source]=true;
        q.add(source);
        while(!q.isEmpty()){
        	
        	int s = q.poll();
        	 System.out.print(s+" ");
        	List<Integer> l = adjList.get(s);
            for(int i=0;i<l.size();i++){
            	if(!visited[l.get(i)]){
            		q.add(l.get(i));
            		visited[l.get(i)]=true;
            	}
            }
        }
    }
    
    
    public List<Integer> getEdge(int source)
    {
        if (source > Adjacency_List.size())
        {
            System.out.println("the vertex entered is not present");
            return null;
        }
        return Adjacency_List.get(source);
    }

  
    public static void main(String...arg)
    {
        int source , destination;
        int number_of_edges,number_of_vertices;
        int count = 1;
        Scanner scan = new Scanner(System.in);
        try
        {
      
            System.out.println("Enter the number of vertices and edges in graph");
            number_of_vertices = scan.nextInt();
            number_of_edges = scan.nextInt();
            BstGraph adjacencyList = new BstGraph(number_of_vertices);
 
            
            System.out.println("Enter the edges in graph Format : <source index> <destination index>");
            while (count <= number_of_edges)
            {
                source = scan.nextInt();
                destination = scan.nextInt();
                adjacencyList.setEdge(source, destination);
                count++;
            }
 
            
            System.out.println ("the given Adjacency List for the graph \n");
            for (int i = 1 ; i <= number_of_vertices ; i++)
            {
                System.out.print(i+"->");
                List<Integer> edgeList = adjacencyList.getEdge(i);
                for (int j = 1 ; ; j++ )
                {
                    if (j != edgeList.size())
                    {
                        System.out.print(edgeList.get(j - 1 )+"->");
                    }else
                    {
                        System.out.print(edgeList.get(j - 1 ));
                        break;
                    }
                }
                System.out.println();
            }
        }
        catch(InputMismatchException inputMismatch)
        {
            System.out.println("Error in Input Format. \nFormat : <source index> <destination index>");
        }
        scan.close();
       bstTraversal(Adjacency_List, 1);
       dfsTraversal(Adjacency_List, 1);
    }
    
   /* 4 1
    4 2
    1 2 
    2 4
    2 3
    3 3
    */
    
    
}

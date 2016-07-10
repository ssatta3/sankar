package Server;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

	public static List<List<Integer>> genratePerm(int[] array){
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		generate(array,new ArrayList<Integer>(),result,0);
		return result;
	}
	public static void generate(int[] input,ArrayList<Integer> al,List<List<Integer>> result,int start){
		if(al.size()==input.length){
			result.add(al);
			return;
			}
		for(int i=start;i<input.length;i++){
			al.add(input[i]);
			generate(input, al, result,start+1);
			al.remove(al.size()-1);
		}
	}
	public static void main(String[] args) {
		
		int[] x = {2,3,4};
		List<List<Integer>> y = genratePerm(x);
		System.out.println(y.size());
	}

}

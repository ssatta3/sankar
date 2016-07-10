package Server;

import java.util.HashMap;

public class EditDistance {
	static int d;
	public static int editDst(String s1, String s2){
		if(s1.equals("")){
			return s2.length();
		}
		if(s2.equals("")){
			return s1.length();
		}
		d = Integer.min(Integer.min(editDst(s1.substring(1), s2)+1,editDst(s1,s2.substring(1))+1),editDst(s1.substring(1), s2.substring(1))+(s1.charAt(0)==s2.charAt(0)?0:1));
	    return d;
	}
public static void main(String[] args) {
	String s1 = "wkjefkjewfn";
	String s2 = "wejnfwejnfwenf";
	Tuple t = new Tuple(s1,s2);	
	EditDistanceDP edp = new EditDistanceDP(); 
    int d1 = edp.editDP(t);
	System.out.println(d1);
		int d = editDst("hwrfgbkfdvwef","kjwefbkjwefwejf");
		System.out.println(d);	
	}
}

	 class EditDistanceDP{
		 int d;
		 HashMap<Tuple,Integer> hm = new HashMap<Tuple, Integer>();
		public int editDP(Tuple t){
			if(hm.containsKey(t)){
				return hm.get(t);
			}
			if(t.s1.equals("")){
				return t.s2.length();
			}
			if(t.s2.equals("")){
				return t.s1.length();
			}
			d = Integer.min(Integer.min(editDP(new Tuple(t.s1.substring(1), t.s2))+1,editDP(new Tuple(t.s1,t.s2.substring(1)))+1),editDP(new Tuple(t.s1.substring(1), t.s2.substring(1)))+(t.s1.charAt(0)==t.s2.charAt(0)?0:1));
			hm.put(t,d);
			return d;
		} 
	}
	 class Tuple{
		 String s1;
		 String s2;
		public Tuple(String s1,String s2){
			this.s1 =s1;
			this.s2 =s2;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((s1 == null) ? 0 : s1.hashCode());
			result = prime * result + ((s2 == null) ? 0 : s2.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Tuple other = (Tuple) obj;
			if (s1 == null) {
				if (other.s1 != null)
					return false;
			} else if (!s1.equals(other.s1))
				return false;
			if (s2 == null) {
				if (other.s2 != null)
					return false;
			} else if (!s2.equals(other.s2))
				return false;
			return true;
		} 
	 }
	

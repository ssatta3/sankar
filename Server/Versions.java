package Server;

public class Versions {
	public static int compareVersion(String a, String b) {
	    String[] a1 = a.split("\\.");
	    String[] a2 = b.split("\\.");
	    int min = Math.min(a1.length,a2.length);
	    for(int i=0;i<min;i++){
	      if(Integer.parseInt(a1[i]) > Integer.parseInt(a2[i])){
	          return 1;
	      }else if (Integer.parseInt(a1[i]) == Integer.parseInt(a2[i])){
	          continue;
	      }else{
	          return -1;
	      
	    }
	}
	if(a1.length>a2.length) return 1;
	else if (a2.length>a1.length) return -1;
	else 
	return 0;
}

	public static void main(String[] args) {
		int x = compareVersion("13.0", "13.0.8");
		System.out.println(Integer.parseInt("+8989"));

	}

}

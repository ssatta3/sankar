package Server;

public class WordLenghts {

	public static int  createMasks(String[] words){
		int[] mask = new int[(words.length)];
		for(int i=0;i<words.length;i++){
			String s = words[i];
			for(int j=0;j<s.length();j++){
				 mask[i]= mask[i] | 1 << (s.charAt(j)-'a');
			}
			System.out.println(mask[i]);
		}
		int max = maxProduct(words,mask);
		return max;
		
	}
	public static int maxProduct(String[] words, int[] masks){
		int maxProduct=0;
		int currentProduct;
		for(int i=0;i<words.length-1;i++){
			for(int j=i+1;j<words.length;j++){
				if(((masks[i])&(masks[j]))==0){
					currentProduct = words[i].length()*words[j].length();
					maxProduct = Integer.max(currentProduct,maxProduct);
				}
			}
		}
		return maxProduct;
	}
	public static void main(String args[]){
		String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
		int max = createMasks(words);
		 
		 System.out.println(max);
	}
}

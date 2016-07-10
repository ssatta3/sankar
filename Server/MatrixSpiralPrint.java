package Server;

public class MatrixSpiralPrint {
	  static int rowStart;
	  static int columnStart;
	  static int numColumns;
	  static int numRows;
    public static void lefttoRightPrint(int[][] a,int rowStart,int columnStart,int numColumns,int numRows)
    {
       for(int i=0;i<numColumns;i++){
    	   System.out.println(a[rowStart][columnStart]);
    	   columnStart++;
       }
      columnStart--;
      rowStart++;
      numRows--;
      uptoDownPrint( a,rowStart,columnStart,numColumns,numRows);
    }
    public static void uptoDownPrint(int[][] a,int rowStart,int columnStart,int numColumns,int numRows)
    {
    	for(int i=0;i<numRows;i++){
    		System.out.println(a[rowStart][columnStart]);
    		rowStart++;
    	}
      columnStart++;
      rowStart--;
      numColumns--;
    }
 	public static void main(String[] args) {
	     
	     int[][] a = {{1,2,3},{4,5,6},{7,8,9}};
	     lefttoRightPrint(a,0,0,3,3);
	}

}

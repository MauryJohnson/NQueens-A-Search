import java.util.Hashtable;

public class NQueens {
	
	int[][] nQueens(int n) {
	    
	    if(n==1){
	        return new int[][]{{1}};
	    }
	    /*
	    if(n%2==1){
	        return new int[][]{{}};
	    }
	    */
	    
	    //Create grid so queens could actually fit
	    String[][] GRID = new String[n][n];
	    for(int i=0; i<n; i+=1)
	        for(int j=0; j<n; j+=1)
	            GRID[i][j] = "0";
	    
	    StateSpace S = new StateSpace();
	    //The area each queen cover MUST be 2*(n-1), OR else cannot place n queens!
	    //That's first heuristic, second heuristic is how many are on board!
	    //first heiristic, H1 will be a huge penalty if fail this, -1, 1 if succeed 
	    //second Value, will be sum of current queens on board, essentially G value
	    // F = G + H
	    //EX for example there, heuristic of that board would be 4
	    S.Root= new State(GRID);
	    
	    S.Root.NQueens(S,S.Root,-9999,n);
	    
	    System.out.println("ALL SOLUTIONS");
	    System.out.println(S);
	    
		System.out.println("Number of States generated:"+S.StateCount);
		
	    return S.toInt(n);
	    
	}
	
	public static void main(String[] args) {
		
		//This takes about 10 seconds using A*
		new NQueens().nQueens(11);
		
	}

}

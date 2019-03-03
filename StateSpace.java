import java.util.Comparator;
import java.util.Hashtable;
import java.util.SortedMap;
import java.util.TreeMap;

//import EightQueens.StateSpace;

//public class StateSpace {
	//State Space of ALL grids
		public class StateSpace{
			
			public int StateCount = 0;
			
		    public State Root;
		    
		    //Contains all solutions to queens problem!
		    SortedMap<String,Integer[]> Solutions;
		    
		    Hashtable<Integer,Integer> VisitedF = new Hashtable<Integer,Integer>();
		    
		    //Hashtable<String,I[]> Visited = new Hashtable<String,int[]>();
		    
		    public StateSpace() {
		    	Comparator<String> C = new Comparator<String>() {
		            @Override public int compare(String s1, String s2) {
		            	
		            	String[] S1 = s1.split(",");
		            	String[] S2 = s2.split(",");
		            	
		            	if(S1==null || S2==null) {
		            		System.err.println("BAD STRINGS");
		            		System.exit(-1);
		            	}
		            	
		            	int Diff = 0;
		            	
		            	for(int i=0;i<S1.length && i<S2.length;i+=1) {
		            		Integer I1 = Integer.parseInt(S1[i]);
		            		Integer I2 = Integer.parseInt(S2[i]);
		            		
		            		Diff = I1.compareTo(I2);
		            		if(Diff!=0) {
		            			return Diff;
		            		}
		            	}
		            	
		                return Diff;
		            }           
		        };

		    	Solutions = new TreeMap<String,Integer[]>(C);
		    }
		    
		    public void AddSolution(StateSpace SS, State S){
		        
		        String s = Stringify(S.GetIndices(S.GRID));
		        String r = Reverse(s);
		        Integer[] I = Solutions.get(s);
		        Integer[] I2 = Solutions.get(r);
		        //boolean T = (Visited.get(s)==null); /*&& (Visited.get(r)==null);*/
		            if(I==null && I2==null){
		                Integer[] II = Intify(S.GetIndices(S.GRID));
		                Integer[] II2 = IReverse(II);
		                //System.out.println("PUTTING:"+s);
		                this.Solutions.put(s,II);
		                //Visited.put(s,II);
		                //System.out.println("PUTTING:"+r);
		                this.Solutions.put(r,II2);
		                
		                StateCount+=2;
		            }
		            else{
		                //System.out.println("NOT PUTTING:"+s+" AND:"+r);
		            }
		    }
		    
		    public Integer[] Intify(String[] R){
		        Integer[] I = new Integer[R.length];
		        
		        for(int i=0; i<R.length;i+=1)
		            I[i] = Integer.parseInt(R[i]);
		        
		        return I;
		    }
		    
		    public String Stringify(String[] R){
		        String s = "";
		        for(int i=0; i<R.length;i+=1)
		        	if(i<R.length-1)
		            s+=R[i]+",";
		        	else
		        	s+=R[i];
		        
		        return s;
		    }
		    
		    public String toString(){
		           
		        String s = "";
		        
		        for(Integer[] k:Solutions.values()){
		        	System.out.print("{");
		            for(int i=0; i<k.length;i+=1)
		            	if(i<k.length-1)
		                System.out.print(""+k[i] + ", ");
		            	else
			                System.out.print(""+k[i] + "},");
		            
		            System.out.println();
		        }
		        
		        return s;

		    }
		    
		    //All results
		    public int[][] toInt(int N){
		        int[][] Res = new int[this.Solutions.size()][N];
		        int i=0;
		        for(Integer[] v:Solutions.values()){
		            Res[i] = ToIInt(v);
		            i+=1;
		        }
		        return Res;
		    }
		    
		    public int[] ToIInt(Integer[] I){
		        int[] Res = new int[I.length];
		        int k=0;
		        for(Integer i:I){
		            Res[k]=i;
		        k+=1;
		        }
		        return Res;
		    }
		    
		    public Integer[] IReverse(Integer[] I){
		        
		    	Integer[] I2 = new Integer[I.length];
		        
		        for(int i=I.length-1;i>=0;i--) {
		        	/*String s = ""+I[i];
		        	for(int k=0; k<s.length();k+=1) {
		        		
		        	}
		        	*/
		            I2[(I.length-1) - i] = I[i];   
		        }
		        
		        return I2;
		    }
		    
		    public String Reverse(String s){
		        
		    	String r = "";
		        String[] S = s.split(",");
				        
		        for(int k = S.length-1;k>=0;k-=1)
		            if(k>0)
		                r+=S[k] + ",";
		            else
		                r+=S[k];
		        
		        return r;
		    }
		}
//}

import java.util.ArrayList;
import java.util.Hashtable;

//import EightQueens.State;
//mport EightQueens.StateSpace;

//public class State {
	//Space of grid to be discovered
		public class State{

		    //1 Means occupied by queen
		    //0 means occupied by no one
		    //-1 means cannot go here, attacked place
		    public String[][]GRID;
		    
		    //Area left of grid
		    public int AREA;
		    
		    //Since every queen position is unique, store into HT
		    //Map to total area queen takes
		    Hashtable<String,Integer> GAM = new Hashtable<String,Integer>();
		    
		    //Area queen takes up mapping
		    Hashtable<String,Integer> QAM = new Hashtable<String,Integer>();
		    
		    //Number of queens on board
		    int QOB = 0;
		    
		    // H = 0, G = 0
		    int F = 0;
		    
		    //States per
		    ArrayList<State> States = new ArrayList<State>();
		    
		    //If a board is filled, this becomes true
		    //If a board is not filled, this becomes false!
		    boolean ABF = false;
		    
		    //Creation of a new state space grid
		    public State(String[][] GRID){
		        this.GRID=GRID;
		        this.AREA=GRID.length*GRID.length;
		    }
		    
		    //Create Child state
		    public State(String[][]GRID, Hashtable<String,Integer> QAM, Hashtable<String,Integer> GAM, int QOB,int i, int j){
		           
		           this.GRID=GRID;
		           //this.AREA=GRID.length*GRID.length;
		           this.QOB = QOB+1;
		           this.QAM.put(i+""+j,QAM.get(i+""+j)); 
		           this.GAM.put(i+""+j,GAM.get(i+""+j));
		        
		    }     
		    
		    //Compute area queen takes up on grid. Get area left on grid.
		    //Maximize area left on grid
		    public int GHeuristic(int i, int j){
		        return this.GAM.get(""+i+""+j);
		    }
		    
		    //Compute area queen takes up on grid, make this negative as a penalty for 
		    //queen taking up area
		    
		    public int QHeuristic(int i, int j){
		        return -this.QAM.get(""+i+""+j);
		    }
		    
		    
		    //Compute functional value given position of queen
		    public int FValue(int i, int j){
		        return (/*(int)Math.pow*/(this.QOB*GRID.length) + GHeuristic(i,j) + QHeuristic(i,j));
		    }
		    
		    //Start Tree from root
		    public void NQueens(StateSpace SS, State S,int MaxUtility,int N){

				//System.out.println("Number of States generated:"+SS.StateCount);
				
		        if(S==null){
		            return;
		        }
		        
		       
		       boolean ABF = false;
		        
		       //System.out.println("------NEW LEVEL------");
		       for(int i=0; i<N;i+=1){
		           for(int j=0;j<N;j+=1){
		               
		           //If grid spot is not attacked
		           if(S.GRID[i][j].compareTo("0")==0){
		               
		           //Choose N*N points on grid for queen to go
		           String[][] G = CopyGrid(S.GRID);
		        
		           ABF = S.CrissCross(G,i,j);
		           
		           State S2 = new State(G,S.QAM,S.GAM,S.QOB,i,j);
		           S2.AREA = S.AREA;
		               
		           int U = S2.FValue(i,j);    
		               
		           //System.out.println("Utility of this State:"+U);
		           
		           if(S2.QOB>=N){
		               
		               //System.out.println("---------------------------FOUND nQUEENS!--------------------------");
		               //System.out.println("\n"+S2+"\n");
		                  
		               SS.AddSolution(SS,S2);
		               
		               SS.VisitedF.put(U,U);
		               
		           }    
		               
		           else if(U>=MaxUtility && SS.VisitedF.get(U)==null){
		           
		           //SS.VisitedF.put(U,U);
		               
		           //System.out.println("NEW MAX UTILITY");   
		           MaxUtility = U;    
		           S.States.add(S2);
		           
		           //System.out.println("STATE ADDED:\n"+S.States.get(S.States.size()-1));
		                    
		           }
		           else{
		           Nullify(S2);
		           
		           }
		            
		                }
		           }
		       }
		        
		        if(/*AllBoardsFilled(S.States)*/ABF
		        ){
		            return;
		        }
		        
		        for(int i=0; i<S.States.size();i+=1){
		            //if(S.States.get(i).FValue
		            NQueens(SS,S.States.get(i),MaxUtility,N);
		            Nullify(S.States.get(i));
		        }
		        
		    }
		    
		    private void Nullify(State S2) {
		    	// TODO Auto-generated method stub
		    	   S2.GAM = null;
		           S2.QAM = null;
		           S2.GRID=null;
		           S2.States=null;
		           S2=null;
		    }

			public String[][] CopyGrid(String[][]G){
		        
		        String[][]GG = new String[G.length][G.length];
		        
		        for(int i=0; i<G.length;i+=1)
		            for(int j=0; j<G[i].length;j+=1)
		                GG[i][j] = G[i][j];
		    
		        return GG;
		    }
		    
		    
		    //Check if all children fill boards out
		    //public boolean AllBoardsFilled(ArrayList<State> States){
		        //boolean Filled = true;
		        
		        /*
		        for(int i=0; i<States.size();i+=1){
		            for(int j=0; j<States.get(i).GRID.length;j+=1){
		                for(int k=0; k<States.get(i).GRID[j].length;k+=1)
		                    if(States.get(i).GRID[j][k].compareTo("0")==0){
		                        System.out.println("ALL BOARDS NOT FILLED!");
		                        return false;
		                    }
		            }
		        }
		        */
		        
		        //Check to see if all GAM values ==0!
		    /*
		        for(int i=0; i<States.size();i+=1){
		            for(Integer I:States.get(i).GAM.values())
		                if(I>0){
		                    //System.out.println("ALL BOARDS NOT FILLED!");
		                    return false;
		                }
		        }
		    */  
		        //System.out.println("ALL BOARDS FILLED!");
		        
		      //  return true;
		    //}
		 
		    //Compute area taken for particular queen
		    public boolean CrissCross(String[][]G, int i, int j){
		        
		        int A = AREA;
		        int Area = 0;
		        
		        int I = i;
		        int J = j;
		        int [] V  = new int[2];
		        V[0] = -1;
		        V[1] = 0;
		        
		        i+=V[0];
		        //Up
		        for(;i>=0;i+=V[0]){
		             if(G[i][j].compareTo("0")==0){
		                 Area+=1; 
		                 this.AREA-=1;
		             }
		             else {
		                 continue; 
		             }
		             
		            G[i][j] = "-1"; 
		        }
		        
		        V[1] = 1;
		        //UpRight
		        for(i=I+V[0],j=J+V[1];i>=0 && j<G[i].length;i+=V[0],j+=V[1]){
		             if(G[i][j].compareTo("0")==0){
		                 Area+=1; 
		                 this.AREA-=1;
		             }
		             else {
		                 continue; 
		             }
		            G[i][j] = "-1";
		            //Area+=1;   
		        }
		        
		        V[1] = -1;
		        //UpLeft
		        for(i=I+V[0],j=J+V[1];i>=0 && j>=0;i+=V[0],j+=V[1]){
		             if(G[i][j].compareTo("0")==0){
		                 Area+=1; 
		                 this.AREA-=1;
		             }
		             else {
		                 continue; 
		             }
		            G[i][j] = "-1";
		            //Area+=1;   
		        }
		        
		        V[0] = 1;
		        V[1] = 0;
		        //Down
		        for(i=I+V[0],j=J+V[1];i<G.length;i+=V[0]){
		              if(G[i][j].compareTo("0")==0){
		                 Area+=1; 
		                 this.AREA-=1;
		              }
		              else {
		                 continue; 
		              }
		             G[i][j] = "-1";
		             //Area+=1;   
		        }
		        
		        V[1] = 1;
		        //DownRight
		        for(i=I+V[0],j=J+V[1];i<G.length && j<G.length;i+=V[0],j+=V[1]){
		             if(G[i][j].compareTo("0")==0){
		                 Area+=1; 
		                 this.AREA-=1;
		             }
		             else {
		                 continue; 
		             }
		            G[i][j] = "-1";
		            //Area+=1;   
		        }
		        
		        V[1] = -1;
		        //DownLeft
		        for(i=I+V[0],j=J+V[1];i<G.length && j>=0;i+=V[0],j+=V[1]){
		             if(G[i][j].compareTo("0")==0){
		                 Area+=1; 
		                this.AREA-=1;
		             }
		             else {
		                 continue; 
		             }
		            G[i][j] = "-1";
		            //Area+=1;   
		        }
		        
		        V[0] = 0;
		        V[1] = -1;
		        //Left
		        for(i=I+V[0],j=J+V[0];j>=0;j+=V[1]){
		             if(G[i][j].compareTo("0")==0){
		                 Area+=1; 
		                 this.AREA-=1;
		             }
		             else {
		                 continue; 
		             }
		            G[i][j] = "-1";
		            //Area+=1;   
		        }
		        
		        V[1] = 1;
		        //Right
		        for(i=I+V[0],j=J+V[0];j<G[i].length;j+=V[1]){
		             if(G[i][j].compareTo("0")==0){
		                 Area+=1; 
		                 this.AREA-=1;
		             }
		             else {
		                 continue; 
		             }
		             G[i][j] = "-1";  
		        }
		        
		        G[I][J] = "Q";
		        Area+=1;
		        
		        //System.out.println(" TOTAL AREA TAKEN BY QUEEN: "+Area);
		        int C = A;

		        //A = AreaLeft(G);
		        
		        //System.out.println(" AREA LEFT OF MAP:" + A + "VS AREA HACK:"+AREA);
		        boolean ABF = false;
		        
		        if(AREA==0)
		            this.ABF = true;
		        
		        //Hash position to heuristic value, MAXIMIZING AREA LEFT IDEALLY
		        this.GAM.put(""+I+""+J,AREA);
		    
		        AREA = C;
		        
		        //Area queen takes up on board
		        this.QAM.put(""+I+""+J,Area);
		        
		        return ABF;
		    }      
		    
		    /*
		    public int AreaLeft(String[][]G){
		        int count = 0;
		        for(int i=0; i<G.length;i+=1)
		            for(int j=0; j<G[i].length;j+=1)
		                if(G[i][j].compareTo("0")==0)
		                    count+=1;
		        return count;
		    }
		    */
		    
		    public String toString(){
		        String s ="";
		        for(int i=0;i<GRID.length;i+=1){
		            for(int j=0; j<GRID[i].length;j+=1){
		                s+=GRID[i][j] +" ";
		            }
		            s+="\n";
		        }
		        return s;
		    }
		    
		    public String[] GetIndices(String[][] GRID){
		        
		        String[] Indices = new String[GRID.length];
		        
		        for(int i=0; i<GRID.length;i+=1){
		            for(int j=0; j<GRID[i].length;j+=1)
		                if(GRID[i][j].compareTo("Q")==0)
		                    Indices[j] = (i+1)+"";
		        }
		        
		        return Indices;
		        
		    }
		    
		}
//}

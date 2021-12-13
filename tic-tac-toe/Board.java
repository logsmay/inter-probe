

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Board {
	
	// Constants.
	public static final String WINNER_O = "WINNER - O";
	public static final String WINNER_X = "WINNER - X";
	public static final String TIE = "GAME - TIE";
	public static final String IN_PROGRESS = "GAME - INPROGRESS";
	
    Map<String, BoardValue> map = new HashMap<String, BoardValue>();
    
    static int dimention;
    
    int totalMoves;
    
    int xMoves = 0;
    int oMoves = 0;
    
    // Assuming X starts the move
    boolean isXMove = true;
    
    // To maintain copy of successful moves of X and O
    Set<HashSet<String>> rWinSetO = new HashSet<HashSet<String>>();
    Set<HashSet<String>> cWinSetO = new HashSet<HashSet<String>>();
    Set<HashSet<String>> dWinSetO = new HashSet<HashSet<String>>();
    
    Set<HashSet<String>> rWinSetX = new HashSet<HashSet<String>>();
    Set<HashSet<String>> cWinSetX = new HashSet<HashSet<String>>();
    Set<HashSet<String>> dWinSetX = new HashSet<HashSet<String>>();
    
    
    /**
     * Constructor loads data in the background
     * @param dimention
     */
    public Board(int dimention) {
        if(dimention <=0) {
            throw new RuntimeException("Negative number or 0 is given in the parameter.");
        } else {
            Board.dimention = dimention;
            this.totalMoves = dimention*dimention;
            loadData();
        }
    }
    
    /**
     * Move, three validations in place - key already exists/invalid bound/make X and Y as an alternate play  
     * @param val
     * @param dim1
     * @param dim2
     */
	public void move(BoardValue val, int dim1, int dim2) {
		String key = null;
		if(dim1 >= 0 && dim2 >= 0 && dim1 < dimention && dim2 < dimention) {
			key = "[" + String.valueOf(dim1) + "," + String.valueOf(dim2) + "]";
			if(map.containsKey(key) /*O(1)*/) {
				throw new RuntimeException(key+" already exists");
			} else {
				if(isXMove && val.equals(BoardValue.X)) {
					isXMove = false;
					map.put(key, val);
				} else if(!isXMove && val.equals(BoardValue.O)) {
					isXMove = true;
					map.put(key, val); /*O(1)*/
				} else {
					if(isXMove) {
						throw new RuntimeException("X Turn");
					} else {
						throw new RuntimeException("O Turn");
					}
				}
				
			}
		} else {
			throw new RuntimeException("Invalid Bound");
		}
		
		removeElements(key, val);
		
	}
	
	/**
	 * Assuming the game will start with X, If X always wins ONE step higher than the steps made by O. 
	 * If O Wins Total number of steps played is Equal number of steps with that of X. 
	 * @return
	 */
	public String hasWinner() {
		if(xMoves == oMoves) {
			for(HashSet<String> winRSet : rWinSetO) {
				if(winRSet.isEmpty()) {
					return WINNER_O;
				}
			}
			for(HashSet<String> winCSet : cWinSetO) {
				if(winCSet.isEmpty()) {
					return WINNER_O;
				}
			}
			for(HashSet<String> winDSet : dWinSetO) {
				if(winDSet.isEmpty()) {
					return WINNER_O;
				}
			}
		}
		
		if(xMoves == oMoves+1) {
			for(HashSet<String> winRSet : rWinSetX) {
				if(winRSet.isEmpty()) {
					return WINNER_X;
				}
			}
			for(HashSet<String> winCSet : cWinSetX) {
				if(winCSet.isEmpty()) {
					return WINNER_X;
				}
			}
			for(HashSet<String> winDSet : dWinSetX) {
				if(winDSet.isEmpty()) {
					return WINNER_X;
				}
			}
		}
		
		if(totalMoves == (xMoves + oMoves)) {
			return TIE;
		} else {
			return IN_PROGRESS;
		}
		
	}
	
	/**
     * Static method to load successful moves prior to start the process
     */
    public void loadData() {
    	for(int i=0;i<dimention;i++) {
        	HashSet<String> temprSet = new HashSet<String>();
        	HashSet<String> tempcSet = new HashSet<String>();
        	for(int j=0;j<dimention;j++) {
            	temprSet.add("[" + i + "," + j + "]");
            	tempcSet.add("[" + j + "," + i + "]");
            }
        	rWinSetO.add(temprSet);
        	cWinSetO.add(tempcSet);
        	rWinSetX.add((HashSet<String>) temprSet.clone());
        	cWinSetX.add((HashSet<String>) tempcSet.clone());
        }
        
    	HashSet<String> temprSet = new HashSet<String>();
    	HashSet<String> tempcSet = new HashSet<String>();
        for(int x=0,y=dimention-1; x < dimention && y >= 0; x++,y--) {
        	temprSet.add("[" + x + "," + x + "]");
        	tempcSet.add("[" + x + "," + y + "]");
        }
        dWinSetX.add((HashSet<String>) temprSet.clone());
        dWinSetX.add((HashSet<String>) tempcSet.clone());
        dWinSetO.add(temprSet);
        dWinSetO.add(tempcSet);
        
    }
	
	/**
	 * Removes the elements moved in the respective (X and O) successful move set
	 * @param key
	 * @param val
	 */
	public void removeElements(String key, BoardValue val) {
		if(val.equals(BoardValue.O)) {
			oMoves++;
			for(HashSet<String> winRSet : rWinSetO) {
				 if(winRSet.contains(key)) {
					 winRSet.remove(key);
				 }
			}
			for(HashSet<String> winCSet : cWinSetO) {
				 if(winCSet.contains(key)) {
					 winCSet.remove(key);
				 }
			}
			for(HashSet<String> winDSet : dWinSetO) {
				 if(winDSet.contains(key)) {
					 winDSet.remove(key);
				 }
			}
		}
		else if(val.equals(BoardValue.X)) {
			xMoves++;
			for(HashSet<String> winRSet : rWinSetX) {
				 if(winRSet.contains(key)) {
					 winRSet.remove(key);
				 }
			}
			for(HashSet<String> winCSet : cWinSetX) {
				 if(winCSet.contains(key)) {
					 winCSet.remove(key);
				 }
			}
			for(HashSet<String> winDSet : dWinSetX) {
				 if(winDSet.contains(key)) {
					 winDSet.remove(key);
				 }
			}
		}
	}
}

package com.ct.tictactoe;
import java.util.HashMap;
import java.util.Map;


public class BoardConstantTime {
	
	// Constants.
	public static final String WINNER_O = "WINNER - O";
	public static final String WINNER_X = "WINNER - X";
	public static final String TIE = "GAME - TIE";
	public static final String IN_PROGRESS = "GAME - INPROGRESS";
	
    Map<String, BoardValue> map = new HashMap<String, BoardValue>();
    
    int dimention;
    
    int totalMoves;
    
    int xMoves = 0;
    int oMoves = 0;
    
    String result = null;
    
    // Assuming X starts the move
    boolean isXMove = true;
    
    Map<Integer, Integer> rXSumMap = new HashMap<Integer, Integer>();
    Map<Integer, Integer> cXSumMap = new HashMap<Integer, Integer>();
    Map<Integer, Integer> dXSumMap = new HashMap<Integer, Integer>();
    
    Map<Integer, Integer> rOSumMap = new HashMap<Integer, Integer>();
    Map<Integer, Integer> cOSumMap = new HashMap<Integer, Integer>();
    Map<Integer, Integer> dOSumMap = new HashMap<Integer, Integer>();
    
    /**
     * Constructor loads data in the background
     * @param dimention
     */
    public BoardConstantTime(int dimention) {
        if(dimention <=0) {
            throw new RuntimeException("Negative number or 0 is given in the parameter.");
        } else {
            this.dimention = dimention;
            this.totalMoves = dimention*dimention;
        }
    }
    
    /**
     * Move, four validations in place - key already exists/invalid bound/make X and Y as an alternate play/If a game is over, do not let play
     * @param val
     * @param dim1
     * @param dim2
     */
	public void move(BoardValue val, int dim1, int dim2) {
		String key = null;
		if(dim1 >= 0 && dim2 >= 0 && dim1 < dimention && dim2 < dimention) {
			key = "[" + String.valueOf(dim1) + "," + String.valueOf(dim2) + "]"; //E.g.: [2,4]
			if(map.containsKey(key) /*O(1)*/) {
				throw new RuntimeException(key+" already exists");
			} else {
				if(result == null || hasWinner().equals(IN_PROGRESS)) {
					if(isXMove && val.equals(BoardValue.X)) {
						map.put(key, val);
						rXSumMap.put(dim1, incrementalSum(rXSumMap.get(dim1), val));
						cXSumMap.put(dim2, incrementalSum(cXSumMap.get(dim2), val));
						if(dim1 == dim2 ) {
							dXSumMap.put(1, incrementalSum(dXSumMap.get(1), val));
						} else if(dim1+dim2 == dimention-1) {
							dXSumMap.put(2, incrementalSum(dXSumMap.get(2), val));
						}
						xMoves++;
						isXMove = false;
					} else if(!isXMove && val.equals(BoardValue.O)) {
						map.put(key, val); /*O(1)*/
						rOSumMap.put(dim1, incrementalSum(rOSumMap.get(dim1), val));
						cOSumMap.put(dim2, incrementalSum(cOSumMap.get(dim2), val));
						if(dim1 == dim2 ) {
							dOSumMap.put(1, incrementalSum(dOSumMap.get(1), val));
						} else if(dim1+dim2 == dimention-1) {
							dOSumMap.put(2, incrementalSum(dOSumMap.get(2), val));
						}
						oMoves++;
						isXMove = true;
					} else {
						if(isXMove) {
							throw new RuntimeException("X Turn");
						} else {
							throw new RuntimeException("O Turn");
						}
					}
				} else {
					throw new RuntimeException("Game is over:" + hasWinner());
				}
				
			}
		} else {
			throw new RuntimeException("Invalid Bound");
		}
		
	}
	
	/**
	 * Returns the game winner/tie/in progress 
	 * @return
	 */
	public String hasWinner() {
		if(result != null) {
			return result;
		}
		else if(totalMoves == (xMoves + oMoves)) {
			return TIE;
		} else {
			return IN_PROGRESS;
		}
	}
	
	/**
	 * Calculates the winner on constant time. 
	 * @param temp
	 * @param value
	 * @return
	 */
	public int incrementalSum(Integer temp, BoardValue value) {
		int count = temp==null?0:temp;
		++count;
		if(count == dimention) {
			if(BoardValue.X == value) {
				this.result = WINNER_X;
			} else if(BoardValue.O == value) {
				this.result = WINNER_O;
			}
		}
		return count;
	}
}

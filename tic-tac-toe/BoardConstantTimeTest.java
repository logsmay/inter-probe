package com.ct.tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;


public class BoardConstantTimeTest {

	@Test
	public void testInProgress() {
		BoardConstantTime olinear = new BoardConstantTime(3);
		olinear.move(BoardValue.X, 1, 1);
		olinear.move(BoardValue.O, 0, 2);
		olinear.move(BoardValue.X, 0, 1);
		olinear.move(BoardValue.O, 0, 0);
		olinear.move(BoardValue.X, 1, 2);
		olinear.move(BoardValue.O, 2, 2);
		assertEquals(BoardConstantTime.IN_PROGRESS, olinear.hasWinner());
	}
	
	@Test
	public void testXWin() {
		BoardConstantTime olinear = new BoardConstantTime(3);
		olinear.move(BoardValue.X, 1, 1);
		olinear.move(BoardValue.O, 0, 2);
		olinear.move(BoardValue.X, 0, 1);
		olinear.move(BoardValue.O, 0, 0);
		olinear.move(BoardValue.X, 1, 2);
		olinear.move(BoardValue.O, 2, 2);
		olinear.move(BoardValue.X, 2, 1);
		assertEquals(BoardConstantTime.WINNER_X, olinear.hasWinner());
	}
	
	@Test
	public void testOWin() {
		BoardConstantTime olinear = new BoardConstantTime(3);
		olinear.move(BoardValue.X, 0, 2);
		olinear.move(BoardValue.O, 1, 1);
		olinear.move(BoardValue.X, 0, 0);
		olinear.move(BoardValue.O, 0, 1);
		olinear.move(BoardValue.X, 2, 2);
		olinear.move(BoardValue.O, 2, 1);
		assertEquals(BoardConstantTime.WINNER_O, olinear.hasWinner());
	}
	
	@Test
	public void testTIE() {
		BoardConstantTime olinear = new BoardConstantTime(3);
		olinear.move(BoardValue.X, 0, 0);
		olinear.move(BoardValue.O, 0, 1);
		olinear.move(BoardValue.X, 0, 2);
		olinear.move(BoardValue.O, 1, 0);
		olinear.move(BoardValue.X, 1, 2);
		olinear.move(BoardValue.O, 1, 1);
		olinear.move(BoardValue.X, 2, 0);
		olinear.move(BoardValue.O, 2, 2);
		olinear.move(BoardValue.X, 2, 1);
		assertEquals(BoardConstantTime.TIE, olinear.hasWinner());
	}
	
	@Test
	public void test5X5TIE() {
		BoardConstantTime olinear = new BoardConstantTime(5);
		olinear.move(BoardValue.X, 0, 0);
		olinear.move(BoardValue.O, 1, 0);
		olinear.move(BoardValue.X, 2, 0);
		olinear.move(BoardValue.O, 3, 0);
		olinear.move(BoardValue.X, 4, 0);
		olinear.move(BoardValue.O, 2, 1);
		olinear.move(BoardValue.X, 0, 1);
		olinear.move(BoardValue.O, 0, 2);
		olinear.move(BoardValue.X, 1, 1);
		olinear.move(BoardValue.O, 1, 2);
		olinear.move(BoardValue.X, 3, 1);
		olinear.move(BoardValue.O, 3, 2);
		olinear.move(BoardValue.X, 4, 1);
		olinear.move(BoardValue.O, 4, 2);
		olinear.move(BoardValue.X, 2, 2);
		olinear.move(BoardValue.O, 2, 3);
		olinear.move(BoardValue.X, 0, 3);
		olinear.move(BoardValue.O, 3, 3);
		olinear.move(BoardValue.X, 1, 3);
		olinear.move(BoardValue.O, 0, 4);
		olinear.move(BoardValue.X, 4, 3);
		olinear.move(BoardValue.O, 1, 4);
		olinear.move(BoardValue.X, 2, 4);
		olinear.move(BoardValue.O, 4, 4);
		olinear.move(BoardValue.X, 3, 4);
		assertEquals(BoardConstantTime.TIE, olinear.hasWinner());
	}
	
	@Test
	public void test5X5Progress() {
		BoardConstantTime olinear = new BoardConstantTime(5);
		olinear.move(BoardValue.X, 0, 0);
		olinear.move(BoardValue.O, 1, 0);
		olinear.move(BoardValue.X, 2, 0);
		olinear.move(BoardValue.O, 3, 0);
		olinear.move(BoardValue.X, 4, 0);
		olinear.move(BoardValue.O, 2, 1);
		olinear.move(BoardValue.X, 0, 1);
		olinear.move(BoardValue.O, 0, 2);
		olinear.move(BoardValue.X, 1, 1);
		olinear.move(BoardValue.O, 1, 2);
		olinear.move(BoardValue.X, 3, 1);
		olinear.move(BoardValue.O, 3, 2);
		olinear.move(BoardValue.X, 4, 1);
		olinear.move(BoardValue.O, 4, 2);
		olinear.move(BoardValue.X, 2, 2);
		olinear.move(BoardValue.O, 2, 3);
		olinear.move(BoardValue.X, 0, 3);
		olinear.move(BoardValue.O, 3, 3);
		olinear.move(BoardValue.X, 1, 3);
		assertEquals(BoardConstantTime.IN_PROGRESS, olinear.hasWinner());
	}
	
	@Test
	public void test5X5WIN() {
		BoardConstantTime olinear = new BoardConstantTime(5);
		olinear.move(BoardValue.X, 0, 0);
		olinear.move(BoardValue.O, 1, 0);
		olinear.move(BoardValue.X, 2, 0);
		olinear.move(BoardValue.O, 3, 0);
		olinear.move(BoardValue.X, 4, 0);
		olinear.move(BoardValue.O, 2, 1);
		olinear.move(BoardValue.X, 0, 1);
		olinear.move(BoardValue.O, 0, 2);
		olinear.move(BoardValue.X, 1, 1);
		olinear.move(BoardValue.O, 1, 2);
		olinear.move(BoardValue.X, 3, 1);
		olinear.move(BoardValue.O, 3, 2);
		olinear.move(BoardValue.X, 4, 1);
		olinear.move(BoardValue.O, 4, 2);
		olinear.move(BoardValue.X, 2, 2);
		olinear.move(BoardValue.O, 2, 4);
		olinear.move(BoardValue.X, 0, 3);
		olinear.move(BoardValue.O, 3, 4);
		olinear.move(BoardValue.X, 1, 3);
		olinear.move(BoardValue.O, 0, 4);
		olinear.move(BoardValue.X, 4, 3);
		olinear.move(BoardValue.O, 1, 4);
		olinear.move(BoardValue.X, 2, 3);
		olinear.move(BoardValue.O, 4, 4);
		assertEquals(BoardConstantTime.WINNER_O, olinear.hasWinner());
	}
	
	@Test
	public void testValidations() {
		
		BoardConstantTime olinear;
		// No negative dimention
		try {
			olinear = new BoardConstantTime(3);
		} catch (RuntimeException e) {
			assertTrue("No negative dimention", true);
		}
		
		// Move Cannot be out of bound
		try {
			olinear = new BoardConstantTime(4);
			olinear.move(BoardValue.X, 0, 2);
			olinear.move(BoardValue.O, 4, 1);
		} catch (RuntimeException e) {
			assertTrue("Move Cannot be out of bound", true);
		}
		
		// No repeated Moves
		try {
			olinear = new BoardConstantTime(3);
			olinear.move(BoardValue.X, 0, 2);
			olinear.move(BoardValue.X, 1, 1);
		}
		catch(RuntimeException e) {
			assertTrue("No repeated Moves", true);
		}
		
		// Restrict the same index move
		try {
			olinear = new BoardConstantTime(3);
			olinear.move(BoardValue.X, 0, 2);
			olinear.move(BoardValue.O, 1, 1);
			olinear.move(BoardValue.X, 0, 0);
			olinear.move(BoardValue.O, 0, 1);
			olinear.move(BoardValue.X, 2, 2);
			olinear.move(BoardValue.X, 2, 2);
		}
		catch(RuntimeException e) {
			assertTrue("No repeated Moves", true);
		}	
		
		// O cannot start
		try {
			olinear = new BoardConstantTime(3);
			olinear.move(BoardValue.O, 0, 2);
			}
		catch(RuntimeException e) {
			assertTrue("O cannot start", true);
		}
		
		// Repeated X Moves in the middle of the game
		try {
			olinear = new BoardConstantTime(3);
			olinear.move(BoardValue.X, 0, 2);
			olinear.move(BoardValue.O, 1, 1);
			olinear.move(BoardValue.X, 0, 0);
			olinear.move(BoardValue.O, 0, 1);
			olinear.move(BoardValue.X, 2, 2);
			olinear.move(BoardValue.X, 2, 1);
		} catch(RuntimeException e) {
			assertTrue("Repeated X Moves in the middle of the game", true);
		}
		
		// Move after game over.
		try {
			olinear = new BoardConstantTime(5);
			olinear.move(BoardValue.X, 0, 0);
			olinear.move(BoardValue.O, 1, 0);
			olinear.move(BoardValue.X, 2, 0);
			olinear.move(BoardValue.O, 3, 0);
			olinear.move(BoardValue.X, 4, 0);
			olinear.move(BoardValue.O, 2, 1);
			olinear.move(BoardValue.X, 0, 1);
			olinear.move(BoardValue.O, 0, 2);
			olinear.move(BoardValue.X, 1, 1);
			olinear.move(BoardValue.O, 1, 2);
			olinear.move(BoardValue.X, 3, 1);
			olinear.move(BoardValue.O, 3, 2);
			olinear.move(BoardValue.X, 4, 1);
			olinear.move(BoardValue.O, 4, 2);
			olinear.move(BoardValue.X, 2, 2);
			olinear.move(BoardValue.O, 2, 4);
			olinear.move(BoardValue.X, 0, 3);
			olinear.move(BoardValue.O, 3, 4);
			olinear.move(BoardValue.X, 1, 3);
			olinear.move(BoardValue.O, 0, 4);
			olinear.move(BoardValue.X, 4, 3);
			olinear.move(BoardValue.O, 1, 4);
			olinear.move(BoardValue.X, 2, 3);
			olinear.move(BoardValue.O, 4, 4);
			olinear.move(BoardValue.X, 3, 3);
			} catch(RuntimeException e) {
				assertTrue("Move after the game won by a player", true);
		}
	}

}

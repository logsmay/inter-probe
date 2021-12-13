_This is code is written for Lazada Interview. _

**Problem**: 
Create a class for 2 Player Tic-Tac-Toe. The regular way to solve the problem is through O(N^2) but the interviewer asked me to optimize it better with O(1)

**Solution**: 
Board.java > O(N)
BoardConstantTime.java > O(1)

//constructor()
1. Dimentions cannot be negative.

//move()
2. Move cannot be of out of bound
3. Restrict when the move is already made in the same index. 
4. Validation to make alternative turns ie. when X played, he/she should not be allowed to play again and viceversa. 

Additionally, the TIE and IN-PROGRESS functionality by calculating total number of moves given the dimention and assuming X plays first 
(It is configurable with a little code modification) which implies X always plays one move more than O when he is winning 
and O will always be winning one move lesser than that of X. 

**Comments:**

The first trick to O(1) complexity is to realise that you don't need to scan the whole board every time a move is made: you only need to check the row, column, or diagonal affected by that particular move.

The second trick is to trade off memory for counting: instead of counting every time a move is made, keep a running total for each row, column, and diagonal. 

So other than a standard, say, 3x3 board:
 | |
-+-+-
 | |
-+-+-
 | |

You will keep a bunch of other counters:
       X O
  | |   |
 -+-+- -+-
  | |   |
 -+-+- -+-
  | |   |

X | |   |  (Diagonal)
 -+-+-
O | |

Every time a move is made, you will have to update the count for just that row, column, and possibly diagonal. This is O(1 + 1 + 1) (or each row, column, diagonal counter) which is O(1).
       X O
  | |   |
 -+-+- -+-
 X| |  1|
 -+-+- -+-
  | |O  |1

X1| |   |1
 -+-+-
O | |1

At each update, you will also check the value to see if it's == dimension. This is also O(1).

       X O
  | |O  |1
 -+-+- -+-
 X|X|X 3|
 -+-+- -+-
  | |O  |1

X1|1|1 1|1
 -+-+-
O | |2



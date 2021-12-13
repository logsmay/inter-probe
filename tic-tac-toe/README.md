_This is code is written for Lazada Interview in 2014._

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


![image](https://user-images.githubusercontent.com/1098839/145747930-7357b1bc-8e11-4c30-af37-61d02d0d98b0.png)



You will keep a bunch of other counters:


![image](https://user-images.githubusercontent.com/1098839/145747952-35e0f845-dc40-47cd-9fae-5bac21997b54.png)


![image](https://user-images.githubusercontent.com/1098839/145747973-55d3e382-a6fd-4ce1-9ef4-72d755754ef0.png)


Every time a move is made, you will have to update the count for just that row, column, and possibly diagonal. This is O(1 + 1 + 1) (or each row, column, diagonal counter) which is O(1).


![image](https://user-images.githubusercontent.com/1098839/145748037-c42c9ad7-4911-4531-a0e0-1ca94980863e.png)


At each update, you will also check the value to see if it's == dimension. This is also O(1).


![image](https://user-images.githubusercontent.com/1098839/145748064-f12e32c9-9960-40d8-ad3b-24a51629e32d.png)




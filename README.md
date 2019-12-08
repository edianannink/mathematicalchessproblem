# Mathematical Chess Problem
Mathematical chess problem description

This chess game computes the amount of Queens and Bishops that are needed to cover M x N squares on a chessboard. The program automatically adds bishops when no solutions are found. 

A possible solution with 1 Queen and 3 Bishops can be:

- 0&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;B&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;0
- 0&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;0
- 0&nbsp;&nbsp;&nbsp;B&nbsp;&nbsp;&nbsp;Q&nbsp;&nbsp;&nbsp;B&nbsp;&nbsp;&nbsp;0
- 0&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;0
- 0&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;0

A recursive algorithm is used to "brute-force" all the possible combinations on the board. The program displays the amount of solutions if the user defined enough pieces. If too few pieces are defined, the program automatically adds bishops and tries to find a solution with the incremented bishop amount. Try and see for yourself! 

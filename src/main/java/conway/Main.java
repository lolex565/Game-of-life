package conway;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        int[][] alive = {{0,1},{1,2},{2,0},{2,1},{2,2}};
        Board board = new Board(10,10, alive);

        for (int i = 0; i < 50; i++) {
            board.printBoard('0', ' ');
            board.updateBoard();
        }

    }
}
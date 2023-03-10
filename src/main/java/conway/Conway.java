package conway;

import java.util.*;

public class Conway {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void main(String[] args) {

        int[][] alive = {{0,1},{1,2},{2,0},{2,1},{2,2}};
        Board board = new Board(10,10, alive);
        clearScreen();
        for (int i = 0; i < 50; i++) {
            board.printBoard('0', ' ');
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearScreen();
            board.updateBoard();
        }

    }
}
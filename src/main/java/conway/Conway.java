package conway;

import java.util.*;

public class Conway {

    public static void clearScreen() {
        System.out.print("\033\143");
        System.out.flush();
    }
    public static void main(String[] args) {

        //int[][] alive = {{0,1},{1,2},{2,0},{2,1},{2,2}};
        //int[][] alive = {{1,1},{1,2},{1,3}};
        List<int[]> alive = new ArrayList<int[]>();

        Scanner scan = new Scanner(System.in);
        int turns = 0;
        while(turns < 1){
            System.out.println("How many turns of simulation");
            turns = scan.nextInt();
        }

        int frameTime = 0;
        while (frameTime < 17){
            System.out.println("How long to show frame in miliseconds(at least 17):");
            frameTime = scan.nextInt();
        }

        int rows = 0;
        int columns = 0;

        while (rows < 1 || columns < 1){
            System.out.println("How many rows of simulation");
            rows = scan.nextInt();
            System.out.println("How many columns of simulation");
            columns = scan.nextInt();
        }

        Board board = new Board(rows,columns, alive);

        while (true){
            clearScreen();
            board.printBoard('0', ' ');
            System.out.println("change another cell(y/n)");
            String ans = scan.nextLine();
            if (Objects.equals(ans, "y")) {
                System.out.println("enter alive cell row:");
                int row = scan.nextInt();
                System.out.println("enter alive cell column:");
                int column = scan.nextInt();
                board.flipCellState(row, column);
            } else if (Objects.equals(ans, "n")) {
                break;
            }
        }

        for (int i = 0; i < turns; i++) {
            clearScreen();
            board.printBoard('0', ' ');
            try {
                Thread.sleep(frameTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            board.updateBoard();
        }

    }
}
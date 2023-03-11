package conway;

import java.util.*;

public class Conway {

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

        scan.nextLine();

        System.out.println("How to show alive cells:");
        String aliveSymbol = scan.nextLine();

        System.out.println("How to show dead cells:");
        String deadSymbol = scan.nextLine();

        int rows = 0;
        int columns = 0;

        while (rows < 1 || columns < 1){
            System.out.println("How many rows of simulation");
            rows = scan.nextInt();
            System.out.println("How many columns of simulation");
            columns = scan.nextInt();
        }

        Board board = new Board(rows,columns, alive, aliveSymbol, deadSymbol);

        while (true){
            Tools.clearScreen();
            board.printBoard(aliveSymbol, deadSymbol);
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

        Tools.clearScreen();
        board.printBoard(aliveSymbol, deadSymbol);
        for (int i = 0; i < turns; i++) {
            try {
                Thread.sleep(frameTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            board.updateBoard();
        }

    }
}
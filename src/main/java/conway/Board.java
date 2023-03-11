package conway;

import java.util.*;

public class Board {

    public int column;
    public int row;

    public String aliveSymbol;
    public String deadSymbol;
    public Cell[][] board;
    public Board(int row, int column, List<int[]> alive, String aliveSymbol, String deadSymbol) {
        this.column = column;
        this.row = row;
        this.aliveSymbol = aliveSymbol;
        this.deadSymbol = deadSymbol;
        this.board = new Cell[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.board[i][j] = new Cell();
            }
        }
        for (int[] pair:alive) {
            this.board[pair[0]][pair[1]].setState(true);
        }

    }

    int checkBorder(int row, int column){
        int borderPos = 0;
        if (column == 0){
            borderPos += 2;
        }
        if (column == this.column - 1){
            borderPos += 1;
        }
        if (row == 0){
            borderPos += 8;
        }
        if (row == this.row - 1){
            borderPos += 4;
        }
        return borderPos;
    }

    int checkNeighbours(int border, int row, int column){
        int neighbourAmount = 0;
        switch (border) {
            case 0 -> {
                neighbourAmount += this.board[row - 1][column - 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row - 1][column].getState() ? 1 : 0;
                neighbourAmount += this.board[row - 1][column + 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row][column - 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row][column + 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row + 1][column - 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row + 1][column].getState() ? 1 : 0;
                neighbourAmount += this.board[row + 1][column + 1].getState() ? 1 : 0;
            }
            case 1 -> {
                neighbourAmount += this.board[row - 1][column - 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row - 1][column].getState() ? 1 : 0;
                neighbourAmount += this.board[row][column - 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row + 1][column - 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row + 1][column].getState() ? 1 : 0;
            }
            case 2 -> {
                neighbourAmount += this.board[row - 1][column].getState() ? 1 : 0;
                neighbourAmount += this.board[row - 1][column + 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row][column + 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row + 1][column].getState() ? 1 : 0;
                neighbourAmount += this.board[row + 1][column + 1].getState() ? 1 : 0;
            }
            case 4 -> {
                neighbourAmount += this.board[row - 1][column - 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row - 1][column].getState() ? 1 : 0;
                neighbourAmount += this.board[row - 1][column + 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row][column - 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row][column + 1].getState() ? 1 : 0;
            }
            case 5 -> {
                neighbourAmount += this.board[row - 1][column - 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row - 1][column].getState() ? 1 : 0;
                neighbourAmount += this.board[row][column - 1].getState() ? 1 : 0;
            }
            case 6 -> {
                neighbourAmount += this.board[row - 1][column].getState() ? 1 : 0;
                neighbourAmount += this.board[row - 1][column + 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row][column + 1].getState() ? 1 : 0;
            }
            case 8 -> {
                neighbourAmount += this.board[row][column - 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row][column + 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row + 1][column - 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row + 1][column].getState() ? 1 : 0;
                neighbourAmount += this.board[row + 1][column + 1].getState() ? 1 : 0;
            }
            case 9 -> {
                neighbourAmount += this.board[row][column - 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row + 1][column - 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row + 1][column].getState() ? 1 : 0;
            }
            case 10 -> {
                neighbourAmount += this.board[row][column + 1].getState() ? 1 : 0;
                neighbourAmount += this.board[row + 1][column].getState() ? 1 : 0;
                neighbourAmount += this.board[row + 1][column + 1].getState() ? 1 : 0;
            }
        }
        return neighbourAmount;
    }

    public void flipCellState(int row, int column){
        if (row >= 0 && row < this.row && column >=0 && column < this.column){
            if (this.board[row][column].getState()){
                this.board[row][column].setState(false);
            } else {
                this.board[row][column].setState(true);
            }
        }
    }

    public void updateBoard(){
        List<int[]> deadNextTurn = new ArrayList<int[]>();
        List<int[]> aliveNextTurn = new ArrayList<int[]>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int neighbours = checkNeighbours(checkBorder(i,j),i,j);
                if ((neighbours == 3 && !this.board[i][j].getState()) || ((neighbours == 3 || neighbours == 2) && this.board[i][j].getState())){
                    aliveNextTurn.add(new int[] {i, j});
                } else {
                    deadNextTurn.add(new int[] {i, j});
                }
            }
        }
        for (int[] pair:deadNextTurn) {
            this.board[pair[0]][pair[1]].setState(false);
        }
        for (int[] pair:aliveNextTurn) {
            this.board[pair[0]][pair[1]].setState(true);
        }
        Tools.clearScreen();
        printBoard(this.aliveSymbol, this.deadSymbol);
        if (aliveNextTurn.isEmpty()){
            System.out.println("All cells died");
            System.exit(0);
        }
    }

    public void printBoard(String aliveSymbol, String deadSymbol){
        System.out.print("+---");
        for (int i = 0; i < this.row; i++) {
            System.out.printf("%4d", i);
        }
        System.out.print("+");
        System.out.print('\n');
        for (int i = 0; i < this.row; i++) {
            System.out.printf("|%3d", i);
            for (int j = 0; j < this.column; j++) {
                if (this.board[i][j].getState()){
                    System.out.printf("%3s ", aliveSymbol);
                } else {
                    System.out.printf("%3s ", deadSymbol);
                }
            }
            System.out.print('|');
            System.out.print("\n");
        }
        System.out.print('+');
        for (int i = 0; i < (this.row+1)*4-1; i++) {
            System.out.print('-');
        }
        System.out.print('+');
        System.out.print("\n");
    }
}

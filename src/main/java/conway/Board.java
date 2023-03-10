package conway;

import java.util.*;

public class Board {

    public int column;
    public int row;
    public Cell[][] board;
    public Board(int row, int column, int[][] alive) {
        this.column = column;
        this.row = row;
        this.board = new Cell[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.board[i][j] = new Cell();
            }
        }
        for (int i = 0; i < alive.length; i++) {
            this.board[alive[i][0]][alive[i][1]].setState(true);
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
    }

    public void printBoard(char aliveSymbol, char deadSymbol){
        System.out.print('+');
        for (int i = 0; i < this.row*2; i++) {
            System.out.print('-');
        }
        System.out.print('+');
        System.out.print('\n');
        for (int i = 0; i < this.row; i++) {
            System.out.print('|');
            for (int j = 0; j < this.column; j++) {
                if (this.board[i][j].getState()){
                    System.out.print(aliveSymbol + " ");
                } else {
                    System.out.print(deadSymbol + " ");
                }
            }
            System.out.print('|');
            System.out.print("\n");
        }
        System.out.print('+');
        for (int i = 0; i < this.row*2; i++) {
            System.out.print('-');
        }
        System.out.print('+');
        System.out.print("\n");
    }
}
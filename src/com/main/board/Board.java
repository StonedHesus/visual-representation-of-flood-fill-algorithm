package com.main.board;

import com.main.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Board{

    // Attributes of the class.
    private Cell[][] board;

    // Constructors of the class.
    public Board(int size) {this.board = new Cell[size][size]; this.initialise();}

    // Getters of the class.
    public String getStringRepresentation(int x, int y) {return this.board[x][y].toString();}

    // Methods of the class.
    private int numberOfValidPaths(){

        // Find how many valid paths are there which enable the user to travel from the upper left corner to the
        // bottom right one.
        return -1;
    }


    private int find(){

        // Find the number of obstacles for which the number of solutions in proportional with the configuration of the
        // board.

        int numberOfObstacles = 1;
        return numberOfObstacles;
    }


/*    private int quantifyingFill(int x, int y){

        if((x < this.board.length && x >= 0) && (y < this.board[0].length && y >= 0) ) {

            if (this.board[x][y] instanceof Empty) {

                this.board[x][y] = new Colour();

                if(x == this.board.length - 1 && y == this.board[0].length){

                    return quantifyingFill(x - 1, y) + 1; // North
                    return quantifyingFill(x, y + 1) + 1; // West
                    return quantifyingFill(x + 1, y) + 1; // South
                    return quantifyingFill(x, y - 1) + 1; // East
                } else{

                    quantifyingFill(x - 1, y); // North
                    quantifyingFill(x, y + 1); // West
                    quantifyingFill(x + 1, y); // South
                    quantifyingFill(x, y - 1); // East
                }


            }
        }

    }*/

    public void updateIfNecessary(){

        if((this.board[0][0] instanceof Occupied)){

            this.board[0][0] = new Empty();
        }
    }


    public boolean checkValidityOfPosition(int x, int y){

        return this.board[x][y] instanceof Empty;
    }

    public boolean isColour(int x, int y){

        return this.board[x][y] instanceof Colour;
    }

    public void fill(){

        fill(0, 0);
    }


    private void fill(int x, int y){

        if((x < this.board.length && x >= 0) && (y < this.board[0].length && y >= 0) ) {

            if (this.board[x][y] instanceof Empty) {

                this.board[x][y] = new Colour();



                fill(x - 1, y); // North
                fill(x, y + 1); // West
                fill(x + 1, y); // South
                fill(x, y - 1); // East
            }
        }
    }

    public void reset() {this.initialise();}

    private void initialise(){

        // This function also doubles as the reset one.
        for(int i = 0 ; i < this.board.length ; ++i){

            for(int j = 0 ; j < this.board[0].length ; ++j){

                this.board[i][j] = new Empty();
            }
        }
    }

    public void print(){

        for(int i = 0 ; i < this.board.length ; ++i){

            for(int j = 0 ; j < this.board[0].length ; ++j){

                System.out.print(this.board[i][j].toString() + " ");
            }

            System.out.print("\n");
        }
    }

    public void addRandomObstacles(int numberOfObstacles){

        while(numberOfObstacles > 0){


            this.board[(int)(Math.random()*(this.board.length - 1))]
                    [(int)(Math.random()*(this.board[0].length - 1))] = new Occupied();

            numberOfObstacles -= 1;
        }
    }
}

package com.main.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.main.board.Board;

public class MainView extends JFrame{

    // Attributes of the class.
    //private JButton[][] cells;
    private Board board;
    private final int size;
    public static final int tileSize = 32;
    private final int delay = 500;

    // Constructor of the class.
    public MainView(int size){

       // this.cells = new JButton[size][size];
        this.size = size;
        this.board = new Board(size);

        this.initialiseView();
        this.initialiseBoard();

        JPanel display = new Display();

        add(display);


       // this.initialiseRepresentation();


    }

    private class Display extends JPanel implements ActionListener {

        // Attributes of the class.
        private Timer timer;

        // Constructors of the class.
        public Display(){

            setSize(new Dimension(size*tileSize, size*tileSize));

            timer = new Timer(delay, this);
            timer.start();
        }

        // Methods of the class.
        public void actionPerformed(ActionEvent event){

            board.fill();

            repaint();
        }

        public void drawBackground(Graphics graphics){

            for(int i = 0 ; i < size ; ++i){

                for(int j = 0 ; j < size ; ++j){

                    if(board.checkValidityOfPosition(i, j)){

                        graphics.setColor(new Color(255, 255, 255));
                    } else if(board.isColour(i, j)){

                        graphics.setColor(new Color(143, 248, 0));
                    } else {

                        graphics.setColor(new Color(254, 39, 0));
                    }
                    graphics.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
                }
            }
        }

        @Override
        public void paintComponent(Graphics graphics){

            super.paintComponent(graphics);
            drawBackground(graphics);

            Toolkit.getDefaultToolkit().sync();
        }
    }

    // Methods of the class.
    private void initialiseBoard(){

        this.board.addRandomObstacles(this.size * (this.size/2));
    }


    private void initialiseView(){

        setTitle("Fill Algorithm");
        setSize(new Dimension(tileSize * size, tileSize *(size + 1)));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

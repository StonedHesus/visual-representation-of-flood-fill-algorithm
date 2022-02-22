package com.main.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

        setLayout(new GridLayout());

        JPanel display = new Display();

        add(display);

        JPanel controlCentre = new ControlCentre();
        add(controlCentre);


       // this.initialiseRepresentation()

    }

    private class ControlCentre extends JPanel implements ActionListener{

        // Attributes of the class.
        ArrayList<JButton> myButtons;
        boolean isInInitialState = true; // Only allow the usage of Undo if at least one step was taken.
        boolean isInFinalState   = false;

        // Constructors of the class.
        public ControlCentre(){

            setSize(new Dimension(size * tileSize, size * tileSize));
            this.myButtons = new ArrayList<>();

            this.addControlCentre();
        }

        // Methods of the class.
        @Override
        public void actionPerformed(ActionEvent event){

            if(isInInitialState){

                myButtons.get(2).setEnabled(true);
            }

            if(isInFinalState){

                myButtons.get(0).setEnabled(false);
                myButtons.get(1).setEnabled(false);
            }

            myButtons.get(0).addActionListener((actionEvent) -> {

                    // Activate the fill algorithm.
                    board.fill();
                    isInInitialState = false;
                    repaint();
            });

            myButtons.get(1).addActionListener((actionEvent) ->{

                // Perform one step of the fill algorithm, in addition I ought to also be able to know when
                // the algorithm has ended so as to disable this button.

                isInInitialState = false;
            });

            myButtons.get(2).addActionListener((actionEvent) -> {


                if(isInInitialState){

                    isInInitialState = false;
                }
            });


        }

        private void addButton(JComponent location, String label){

            JButton temporary = new JButton(label);
            temporary.addActionListener((event) ->{

                actionPerformed(event);
            });

            if( label.equals("Undo") ){

                temporary.setEnabled(false);
            }

            myButtons.add(temporary);
            location.add(temporary);
        }

        private void addControlCentre(){


            this.addButton(this, "Fill");
            this.addButton(this, "Do");
            this.addButton(this, "Undo");
        }

    }

    private class Display extends JPanel implements ActionListener {

        // Attributes of the class.
        private Timer timer;

        // Constructors of the class.
        public Display(){

            setSize(new Dimension(size * tileSize, size * tileSize));


            timer = new Timer(delay, this);
            timer.start();


        }

        // Methods of the class.
        @Override
        public void actionPerformed(ActionEvent event){

            //board.fill();

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

            board.updateIfNecessary();
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
        setSize(new Dimension(tileSize * size * 2, tileSize *(size + 1)));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

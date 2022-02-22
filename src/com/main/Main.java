package com.main;

import com.main.gui.MainView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        SwingUtilities.invokeLater(() ->{

            new MainView(12);
        });
    }
}

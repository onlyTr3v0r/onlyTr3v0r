package main.java;

import javax.swing.*;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello world :D");
	System.out.println("An update to this java file!");
	System.out.println("pretend theres an emergency here idk");

	JFrame frame = new JFrame("first git repo");
	JLabel label = new JLabel("this is a small program in my first git repo");
	JButton button = new JButton("this is a test button");
	frame.add(button);
	frame.add(label);
	frame.setSize(400, 500);
	frame.setVisible(true);

	System.out.println("just realised i misspelt the swing branch as wing LMAO");
    }
}

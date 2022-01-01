
//imports
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.event.*;

public class UI extends proccessing implements ActionListener {

    public static void main(String[] args) {
        String[] blocks = {"Select a block", "Ice", "Blue Ice", "Soulsand"};
        String[] movement = {"Select a movement style", "Walk", "Run"};
        JLabel moveLabel = new JLabel();
        JLabel blockLabel = new JLabel();
        blockLabel.setText(blocks[0]);

        // creating window
        JFrame frame = new JFrame("MC Speed Calc");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("speed_calc_icon_2.png").getImage());

        JComboBox<String> getMove = new JComboBox<String>(movement);
        JComboBox<String> getBlock = new JComboBox<String>(blocks);
        frame.add(getBlock, BorderLayout.AFTER_LINE_ENDS);
        frame.add(blockLabel, BorderLayout.PAGE_END);
        frame.add(getMove, BorderLayout.BEFORE_LINE_BEGINS);
        frame.add(moveLabel, BorderLayout.CENTER);
        
        //event listener for block combo-box
        getBlock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (event.getSource().equals(getBlock)) {
                    JComboBox combo = (JComboBox) event.getSource();
                    blockLabel.setText(combo.getSelectedItem().toString());
                    
                }
            }
        });

        //event listener for movement combo-box
        getMove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<String> combo = (JComboBox) event.getSource();
                blockLabel.setText(combo.getSelectedItem().toString());
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}

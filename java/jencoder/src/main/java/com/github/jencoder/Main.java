package main.java.com.github.jencoder;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.util.Map;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Main extends JFrame {
    public static void main(String[] args) {
       new Main();
    }

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final HashMap<Character, Character> CODE = generateCode();
    private static LinkedHashMap<Character, Character> invCode;

    private JSplitPane splitPane;
    private JScrollPane rightPane;
    private JScrollPane leftPane;
    private JTextArea rightTextArea;
    private JTextArea leftTextArea;

    private JFileChooser exportFileChooser;
    private JFileChooser openFileChooser;
    private JButton swapTranslation;
    private JTextField inputText;
    private JButton exportFile;
    private JButton enterText;
    private JPanel bottombar;
    private JButton openFile;
    private JButton clear;

    public static HashMap<Character, Character> generateCode() {
        HashMap<Character, Character> result = new HashMap<>();
        ArrayList<Character> characters = new ArrayList<>();
        ArrayList<Character> charactersShuffled = new ArrayList<>();

        for (int i = 0; i <= 255; i++) {
            char ch = (char) i;
            charactersShuffled.add(ch);
            characters.add(ch);
        }

        Collections.shuffle(charactersShuffled);
        for (int i = 0; i < characters.size(); i++)
            result.put(characters.get(i), charactersShuffled.get(i));

        return result;
    }

    public static void reverseCode() {
        LinkedHashMap<Character, Character> inversed = new LinkedHashMap<>();
        CODE.forEach((key, value) -> inversed.put(value, key));
        invCode = inversed;
    }

    public Main() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Encoder and Decoder Demo");
        setSize(1400, 700);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        reverseCode();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException
               | ClassCastException     | NullPointerException exception) { // So many exceptions!
            LOGGER.log(Level.SEVERE, "Error: ", exception);
        }

        rightTextArea = new JTextArea();
        leftTextArea = new JTextArea();
        rightTextArea.setEditable(false);
        leftTextArea.setEditable(false);

        rightPane = new JScrollPane(rightTextArea);
        leftPane = new JScrollPane(leftTextArea);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
        splitPane.setDividerLocation(700);

        rightPane.setMinimumSize(new Dimension(200, 700));
        leftPane.setMinimumSize(new Dimension(200, 700));
        add(splitPane, BorderLayout.CENTER);

        swapTranslation = new JButton("Swap");
        swapTranslation.addActionListener(new ClickHandler());
        inputText = new JTextField(100);
        openFile = new JButton("Open File");
        openFile.addActionListener(new ClickHandler());
        exportFile = new JButton("Export File");
        exportFile.addActionListener(new ClickHandler());
        enterText = new JButton("Enter Text");
        enterText.addActionListener(new ClickHandler());
        clear = new JButton("Clear");
        clear.addActionListener(new ClickHandler());

        inputText.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER)
                    addText();
            }

            @Override
            public void keyTyped(KeyEvent event) {
            /**/}

            @Override
            public void keyReleased(KeyEvent event) {
            /**/}
        });

        bottombar = new JPanel();
        bottombar.setLayout(new FlowLayout());
        bottombar.add(inputText);
        bottombar.add(enterText);
        bottombar.add(clear);
        bottombar.add(swapTranslation);
        bottombar.add(openFile);
        bottombar.add(exportFile);
        add(bottombar, BorderLayout.SOUTH);

        exportFileChooser = new JFileChooser();
        exportFileChooser.setFileFilter(new FileNameExtensionFilter("Encoder File Format", "eff"));
        openFileChooser = new JFileChooser();
        openFileChooser.setFileFilter(new FileNameExtensionFilter("Text File", "txt", "eff"));

        loadFile("C:\\Users\\FiercePC\\Desktop\\thing.eff");
        setVisible(true);
    }

    public void addText() {
        if (!inputText.getText().isEmpty()) {
            addText(inputText.getText());
            inputText.setText(null);
        }
    }

    public void addText(String toAdd) {
        leftTextArea.append(toAdd.strip() + System.lineSeparator());
    }

    public void loadFile(String path) {
        loadFile(new File(path));
    }

    public void loadFile(File file) {
        // read thru file
        // split on ,
        // evaluate code on split[0]
        // display text on split[1]

        // StringBuilder sb = new StringBuilder();
        // try (Scanner scanner = new Scanner(file)) {
        //     while (scanner.hasNextLine())
        //     // if (encryptedFile) {
        //         sb.append(scanner.nextLine() + System.lineSeparator());
        //     // } else {
        //     //     addText(scanner.nextLine());
        //     // }
        // } catch (IOException exception) {
        //     LOGGER.log(Level.SEVERE, "Error: ", exception);
        // }

        boolean encryptedFile = file.getName().endsWith(".eff");

        if (encryptedFile) {
            StringBuilder sb = new StringBuilder();
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine())
                    sb.append(scanner.nextLine() + System.lineSeparator());

                String[] split = sb.toString().split(",");
                LOGGER.info("Parsing EFF code!");
                parseEffCode(split[0]);

                rightTextArea.append(split[1]);
            } catch (IOException exception) {
                LOGGER.log(Level.SEVERE, "Error: ", exception);
            }
        } else {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine())
                    addText(scanner.nextLine());

            } catch (IOException exception) {
                LOGGER.log(Level.SEVERE, "Error: ", exception);
            }
        }
    }

    public void parseEffCode(String raw) {
        // LOGGER.info(raw);
        Scanner scanner = new Scanner(raw);
        scanner.useDelimiter("\\.");
        List<Character> codes = new ArrayList<>();
        while (scanner.hasNext()) {
            String next = scanner.next();
            // LOGGER.info(next);
            try {
                codes.add((char) Integer.parseInt(next));
            } catch (NumberFormatException exception) {/**/}
        }

        CODE.clear();
        for (int i = 0; i < codes.size() / 2; i++) {
            CODE.put(codes.get(i), codes.get(codes.size() - i - 1));
        }

        // invCode.clear();
        reverseCode();
        // LOGGER.info(CODE.toString());
        scanner.close();
    }

    public void exportFile(File file) {
        StringBuilder sb = new StringBuilder();
        for (Character ch : CODE.keySet()) {
            sb.append(Integer.toString((int) ch) + ".");
        }

        for (int i = CODE.values().size() - 1; i > 0; i--) {
            LOGGER.info(String.valueOf((char) CODE.values().toArray()[i]));
            sb.append(Integer.toString((int) (char) CODE.values().toArray()[i]) + ".");
        }

        sb.append("," + System.lineSeparator() + rightTextArea.getText());

        LOGGER.info(sb.toString());

        try (FileWriter writer = new FileWriter(file);) {
            writer.write(sb.toString());
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Failed to export file", exception);
        }
        // System.out.println(sb.toString());
    }

    public void swap() {
        String right = rightTextArea.getText();
        String left = leftTextArea.getText();
        displayText(leftTextArea, right, false);
        displayText(rightTextArea, left, true);
    }

    public void displayText(JTextArea area, String content, boolean encode) {
        area.setText("");
        Map<Character, Character> code = (encode) ? CODE : invCode;
        for (int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);
            if (ch == '\n') {
                area.append(System.lineSeparator());
            } else if (ch == '\r') {
                // To stop \r characters from being appended
            } else {
                area.append(String.valueOf(code.get(ch)));
            }
        }
    }

    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == enterText)
                addText();
            if (event.getSource() == clear)
                leftTextArea.setText("");
            if (event.getSource() == swapTranslation)
                swap();
            if (event.getSource() == openFile) {
                int fcCODE = openFileChooser.showOpenDialog(Main.this);
                if (fcCODE == JFileChooser.APPROVE_OPTION) {
                    File file = openFileChooser.getSelectedFile();
                    loadFile(file);
                    LOGGER.info(String.format("Opening file: %s at path %s!", file.getName(), file.getAbsolutePath()));
                }
            }
            if (event.getSource() == exportFile) {
                int fcCODE = exportFileChooser.showSaveDialog(Main.this);
                if (fcCODE == JFileChooser.APPROVE_OPTION) {
                    File file = exportFileChooser.getSelectedFile();
                    exportFile(file);
                    LOGGER.info(file.getAbsolutePath());
                }
            }
        }
    }
}
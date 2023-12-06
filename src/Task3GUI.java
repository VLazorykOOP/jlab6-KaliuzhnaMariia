import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Task3GUI extends JFrame {
    private JTextField filePathTextField;
    private JTextArea resultTextArea;

    public Task3GUI() {
        setTitle("Task Change GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel filePathLabel = new JLabel("File Path:");
        filePathTextField = new JTextField(20);
        JButton processButton = new JButton("Process");
        inputPanel.add(filePathLabel);
        inputPanel.add(filePathTextField);
        inputPanel.add(processButton);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        add(inputPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processFile();
            }
        });
    }

    private void processFile() {
        String filePath = filePathTextField.getText();
        try {
            int[][] inputData = readInputFromFile(filePath);
            int[][] result = taskChange(inputData);

            StringBuilder resultText = new StringBuilder();
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    resultText.append(result[i][j]).append("\t");
                }
                resultText.append("\n");
            }
            resultTextArea.setText(resultText.toString());

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File not found: " + filePath, "Error", JOptionPane.ERROR_MESSAGE);

        } catch (InvalidDataFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid data format in the file", "Error", JOptionPane.ERROR_MESSAGE);

        } catch (CustomDataException ex) {
            JOptionPane.showMessageDialog(this, "Invalid data: number of cols not equal to the number or rows", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int[][] readInputFromFile(String filePath) throws FileNotFoundException, InvalidDataFormatException, CustomDataException {
        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String sizeLine = reader.readLine();
            String[] sizeTokens = sizeLine.split("\\s+");

            if (sizeTokens.length != 2) {
                throw new InvalidDataFormatException("Invalid data format: incorrect array size");
            }

            int rows = Integer.parseInt(sizeTokens[0]);
            int cols = Integer.parseInt(sizeTokens[1]);

            int[][] array = new int[rows][cols];

            for (int i = 0; i < rows; i++) {
                String line = reader.readLine();
                String[] tokens = line.split("\\s+");

                if (tokens.length != cols) {
                    throw new CustomDataException("Invalid data: number of cols not equal to the number or rows");
                }

                for (int j = 0; j < cols; j++) {
                    array[i][j] = Integer.parseInt(tokens[j]);
                }
            }

            return array;

        } catch (IOException e) {
            throw new FileNotFoundException("File not found: " + filePath);
        } catch (NumberFormatException e) {
            throw new InvalidDataFormatException("Invalid data format: non-integer value found");
        }
    }

    static class InvalidDataFormatException extends Exception {
        public InvalidDataFormatException(String message) {
            super(message);
        }
    }

    public static class CustomDataException extends RuntimeException {
        public CustomDataException(String message) {
            super(message);
        }
    }

    static int[][] taskChange(int[][] a) {
        int maxr = 0;
        int maxel = a[0][0];
        int[][] rez = new int[a.length][a.length];
        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < a.length; j++){
                if (a[i][j] > maxel){
                    maxel = a[i][j];
                    maxr = i;
                }
            }
        }
        for (int i = 0; i < a.length; i++){
            int newr = (i - maxr + a.length) % a.length;
            for (int j = 0; j < a.length; j++){
                rez[newr][j] = a[i][j];
            }
        }
        return rez;
    }
}


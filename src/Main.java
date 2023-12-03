import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of task: ");
        int choice = s.nextInt();
        switch (choice){
            case 1: {
                Task1();
                break;
            }
            case 2:{
                Task2();
                break;
            }
            default:{
                System.out.println("No such task. Please choose from 1 to 2!");
            }
        }

    }

    public static void Task1(){
        new Task1().setVisible(true);
    }

    public static void Task2(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Task3GUI().setVisible(true);
            }
        });
    }
}

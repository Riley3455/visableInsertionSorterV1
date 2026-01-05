import java.awt.*;
import java.util.Random;
import javax.swing.*;
//insertion loop method
public class Main {
    public static void insertionSort(int[] arr, SortPanel panel) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;

                panel.repaint();
                sleep();
            }

            arr[j + 1] = key;
            panel.repaint();
            sleep();
        }
    }

    // each iteration duration
    private static void sleep() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //main method
    public static void main(String[] args) {
//random integer for the index  4 - 10

        Random random = new Random();
        int size = random.nextInt(4, 10);
        int[] numbers = new int[size];
// each item could be 1 - 100

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100);
        }
// creating the visuals
        JFrame frame = new JFrame("Riley Baptiste Insertion Sort Visualizer");
        SortPanel panel = new SortPanel(numbers);

        frame.add(panel);
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        panel.setBackground(Color.lightGray);

        new Thread(() -> {
            insertionSort(numbers, panel);
        }).start();
    }
}

class SortPanel extends JPanel {

    private int[] array;

    public SortPanel(int[] array) {
        this.array = array;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int barWidth = getWidth() / array.length;

        for (int i = 0; i < array.length; i++) {
            int barHeight = (int) ((array[i] / 100.0) * getHeight());
            g.fillRect(
                    i * barWidth,
                    getHeight() - barHeight,
                    barWidth - 2,
                    barHeight
            );
        }
    }
}
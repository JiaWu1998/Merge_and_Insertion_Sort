import java.util.Random;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.BorderLayout;

public class Test {
    // initialize timing variables
    static long start_time, end_time, total_time;

    // initialize n-array
    static int seed = 600, n = 100;
    static Integer[] array = new Integer[n];
    static boolean stop = false; /////////////
    
    public static void initialize_array(int seed, Integer[] input, int size) {
        Random random_generator = new Random(seed);

        // input random numbers in n-array
        for (int i = 0; i < size; i++) {
            input[i] = random_generator.nextInt(101);
        }
    }

    public static void doSome() {
        stop = true;
    }

    public static void main(String[] args) throws InterruptedException {
        initialize_array(seed, array, n);

        // initialize GUI frame and constants
        JFrame frame = new JFrame("Sorting Animation");
        int SCREEN_HEIGHT = 500;
        int SCREEN_WIDTH = 900;

        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // initialize drawing panel
        Drawer draw = new Drawer();
        draw.setMaxNum(100);

        // add buttons to switch between sorts
        JPanel button_panel_left = new JPanel();
        JPanel button_panel_right = new JPanel();
        JButton ibutton = new JButton("Insertion Sort");
        JButton mbutton = new JButton("Merge Sort");
        ibutton.setMaximumSize(new Dimension(100, 100));
        ibutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doSome();
            }
        });
        button_panel_left.add(ibutton);
        mbutton.setMaximumSize(new Dimension(100,100));
        button_panel_right.add(mbutton);

        frame.add(button_panel_right,BorderLayout.EAST);
        frame.add(button_panel_left,BorderLayout.WEST);
        
        
        // use InsertionSort --------------------------------------------------------------------------
        InsertionSort insert = new InsertionSort(array.clone());

        insert.insertion_sort(); // uses insertion sort
        ArrayList<ArrayList<Integer>> all_snap_insert = insert.show_snaps(); //stores all snapshots of original array
        
        
        for(int i = 0; i < all_snap_insert.size(); i++){
            if(!stop){
                frame.repaint();
                draw.setSnap(all_snap_insert.get(i));
                frame.add(draw,BorderLayout.CENTER);
                Thread.sleep(50);
            }
        }


        //use MergeSort ------------------------------------------------------------------------------
        // MergeSort merge = new MergeSort(array.clone());
        // merge.merge_sort();
        // ArrayList<ArrayList<Integer>> all_snap_merge = merge.show_snaps(); //stores all snapshots of original array
        
        // for(int i = 0; i < all_snap_merge.size(); i++){
        //     frame.repaint();
        //     draw.setSnap(all_snap_merge.get(i));
        //     frame.add(draw);
        //     Thread.sleep(50);
        // }

    
    }
}

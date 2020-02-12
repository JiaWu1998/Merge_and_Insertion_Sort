import java.util.Random;
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.util.ArrayList;

public class Test{
    public static void initialize_array(int seed,Integer[] input, int size){
        Random random_generator = new Random(seed);

        //input random numbers in n-array
        for(int i = 0; i < size; i ++){
            input[i] = random_generator.nextInt(101);
        }
    }

    public static void main(String[] args) throws InterruptedException{
        // initialize timing variables
        // long start_time, end_time, total_time;

        // initialize n-array
        int seed = 600;
        int n = 100;
        Integer[] array = new Integer[n];
        initialize_array(seed, array, n);

        // initialize GUI frame and constants 
        JPanel starting_panel = new JPanel();
        JFrame frame = new JFrame("Sorting Animation");
        int SCREEN_HEIGHT = 500;
        int SCREEN_WIDTH = 900;

        frame.setVisible(true);
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // initialize drawing tool
        Drawer draw = new Drawer();
        draw.setDimension(SCREEN_WIDTH, SCREEN_HEIGHT, n);
        
        //add buttons to switch between sorts
        // JButton ibutton = new JButton("Insertion Sort");
        // JButton mbutton = new JButton("Merge Sort");
        // ibutton.setMaximumSize(new Dimension(100,100));
        // starting_panel.add(ibutton);
        // mbutton.setMaximumSize(new Dimension(100,100));
        // starting_panel.add(mbutton);
        // frame.add(starting_panel);
        
        
        // use InsertionSort --------------------------------------------------------------------------
        // InsertionSort insert = new InsertionSort(array.clone());

        // insert.insertion_sort(); // uses insertion sort
        // ArrayList<ArrayList<Integer>> all_snap_insert = insert.show_snaps(); //stores all snapshots of original array
        
        
        // for(int i = 0; i < all_snap_insert.size(); i++){
        //     frame.repaint();
        //     draw.setSnap(all_snap_insert.get(i));
        //     frame.add(draw);
        //     Thread.sleep(50);
        // }


        //use MergeSort ------------------------------------------------------------------------------
        MergeSort merge = new MergeSort(array.clone());
        merge.merge_sort();
        ArrayList<ArrayList<Integer>> all_snap_merge = merge.show_snaps(); //stores all snapshots of original array
        
        for(int i = 0; i < all_snap_merge.size(); i++){
            frame.repaint();
            draw.setSnap(all_snap_merge.get(i));
            frame.add(draw);
            Thread.sleep(50);
        }

    
    }
}

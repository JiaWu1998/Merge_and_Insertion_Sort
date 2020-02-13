/* 
Jiaxing Wu 
Seat: 63
A20398273

Yik Yu Lau
Seat 37
A20397022

*/ 

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
    static int seed = 600, n = 1000;
    static Integer[] array = new Integer[n];
    static boolean sorting_method = true;
    static boolean stop = false;

    public static void initialize_array(int seed, Integer[] input, int size) {
        Random random_generator = new Random(seed);

        // input random numbers in n-array
        for (int i = 0; i < size; i++) {
            input[i] = random_generator.nextInt(101);
        }
    }

    public static void sorting_switch(){
        sorting_method = !sorting_method;
    }

    public static void stop_switch(){
        stop = !stop;
    }

    public static void main(String[] args) throws InterruptedException {
        initialize_array(seed, array, n);

        // use InsertionSort --------------------------------------------------------------------------
        InsertionSort insert = new InsertionSort(array.clone());
        System.out.println("Computing Insertion Sort.");
        insert.insertion_sort(); // uses insertion sort
        ArrayList<ArrayList<Integer>> all_snap_insert = insert.show_snaps(); //stores all snapshots of original array
        System.out.println("Finished Insertion Sort.");

        //use MergeSort ------------------------------------------------------------------------------
        MergeSort merge = new MergeSort(array.clone());
        System.out.println("Computing Merge Sort.");
        merge.merge_sort();
        System.out.println("Finished Merge Sort.");
        ArrayList<ArrayList<Integer>> all_snap_merge = merge.show_snaps(); //stores all snapshots of original array

        // initialize GUI frame and constants
        JFrame frame = new JFrame("Merge Sort");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // initialize drawing panel
        Drawer draw = new Drawer();
        draw.setMaxNum(100);

        // add buttons to switch between sorts and Text field to change problem size
        JPanel button_panel_left = new JPanel();
        JButton switch_button = new JButton("Switch to Insertion");
        switch_button.setMaximumSize(new Dimension(100, 100));
        switch_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(frame.getTitle().equals("Insertion Sort")){
                    frame.setTitle("Merge Sort    ");
                }else{
                    frame.setTitle("Insertion Sort");
                }

                if(switch_button.getText().equals("Switch to Insertion")){
                    switch_button.setText("Switch to Merge    ");
                }else{
                    switch_button.setText("Switch to Insertion");
                }

                sorting_switch();
            }
        });
        button_panel_left.add(switch_button);
        
        frame.add(button_panel_left,BorderLayout.WEST);

        //animation 
        while(true){
            if(sorting_method){
                for(int i = 0; i < all_snap_merge.size(); i++){
                    if(sorting_method && !stop){
                        frame.repaint();
                        draw.setSnap(all_snap_merge.get(i));
                        frame.add(draw);
                        Thread.sleep(1);  
                    }else{
                        break;
                    }
                }
            }
            if(!sorting_method){
                for(int i = 0; i < all_snap_insert.size(); i++){
                    if(!sorting_method && !stop){
                        frame.repaint();
                        draw.setSnap(all_snap_insert.get(i));
                        frame.add(draw,BorderLayout.CENTER);
                        Thread.sleep(1);
                    }else{
                        break;
                    }
                } 
            }
        }

    }
}

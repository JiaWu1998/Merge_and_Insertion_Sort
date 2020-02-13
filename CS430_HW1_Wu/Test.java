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
import java.awt.Component;
import javax.swing.BoxLayout;

public class Test {
    // initialize timing variables
    static long start_time, end_time, total_time;

    // initialize variables
    static int seed = 600, n = 100, swap_speed = 30;
    static Integer[] array = new Integer[n];
    static boolean sorting_method = true;
    static ArrayList<ArrayList<Integer>> all_snap_merge;
    static ArrayList<ArrayList<Integer>> all_snap_insert;
    static InsertionSort insert;
    static MergeSort merge;

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

    public static void main(String[] args) throws InterruptedException {
        initialize_array(seed, array, n);

        // use InsertionSort --------------------------------------------------------------------------
        insert = new InsertionSort(array.clone());
        System.out.println("Computing Insertion Sort.");
        insert.insertion_sort(); // uses insertion sort
        all_snap_insert = insert.show_snaps(); //stores all snapshots of original array
        System.out.println("Finished Insertion Sort.");

        //use MergeSort ------------------------------------------------------------------------------
        merge = new MergeSort(array.clone());
        System.out.println("Computing Merge Sort.");
        merge.merge_sort();
        System.out.println("Finished Merge Sort.");
        all_snap_merge = merge.show_snaps(); //stores all snapshots of original array

        // initialize GUI frame and constants
        JFrame frame = new JFrame("Merge Sort");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // This panel stores everything other than the sorting animation
        JPanel panel_left = new JPanel();
        panel_left.setLayout(new BoxLayout(panel_left, BoxLayout.Y_AXIS));

        // add a button to switch between sorts
        JPanel button_panel = new JPanel();
        JButton switch_button = new JButton("Switch to Insertion"); 
        switch_button.setMaximumSize(new Dimension(100, 100));
        switch_button.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        button_panel.add(switch_button);
        panel_left.add(button_panel);
        
        // add Text field to change problem size
        JPanel text_field_panel = new JPanel();
        JLabel problem_size_label = new JLabel("Enter Problem Size:");
        JTextField problem_size = new JTextField();
        
        problem_size.setAlignmentX(Component.CENTER_ALIGNMENT);
        problem_size.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) throws IllegalArgumentException{
                try{
                    int temp_n = Integer.parseInt(problem_size.getText());
                    if(temp_n > 0){
                        n = temp_n;
                        array = new Integer[n];
                        initialize_array(seed, array, n);

                        insert = new InsertionSort(array.clone());
                        System.out.println("Computing Insertion Sort.");
                        insert.insertion_sort();
                        System.out.println("Finished Insertion Sort.");
                        all_snap_insert = insert.show_snaps();

                        merge = new MergeSort(array.clone());
                        System.out.println("Computing Merge Sort.");
                        merge.merge_sort();
                        System.out.println("Finished Merge Sort.");
                        all_snap_merge = merge.show_snaps();
                    }else{
                        System.out.print("Invalid problem size");
                    }
                }catch(IllegalArgumentException a){
                    System.out.print("Invalid problem size");
                }
            }
        });
        text_field_panel.add(problem_size_label);
        text_field_panel.add(problem_size);
        panel_left.add(text_field_panel);


        frame.add(panel_left,BorderLayout.WEST);

        // initialize drawing panel
        Drawer draw = new Drawer();
        draw.setMaxNum(100);

        //animation 
        while(true){
            if(sorting_method){
                for(int i = 0; i < all_snap_merge.size(); i++){
                    if(sorting_method){
                        frame.repaint();
                        draw.setSnap(all_snap_merge.get(i));
                        frame.add(draw);
                        Thread.sleep(swap_speed);  
                    }else{
                        break;
                    }
                }
            }
            if(!sorting_method){
                for(int i = 0; i < all_snap_insert.size(); i++){
                    if(!sorting_method){
                        frame.repaint();
                        draw.setSnap(all_snap_insert.get(i));
                        frame.add(draw,BorderLayout.CENTER);
                        Thread.sleep(swap_speed);
                    }else{
                        break;
                    }
                } 
            }
        }

    }
}

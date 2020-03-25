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
import java.awt.Component;
import java.awt.BorderLayout;

public class Test {
    // initialize timing variables
    static long start_time, end_time, total_time;

    // initialize variables
    static int seed = 600, n = 100, swap_speed = 30;
    static Integer[] array = new Integer[n];
    static String sorting_method = "heap";
    static ArrayList<ArrayList<Integer>> all_snap_heap;
    static ArrayList<ArrayList<Integer>> all_snap_quick;
    static QuickSort quick;
    static HeapSort heap;

    public static void initialize_array(int seed, Integer[] input, int size) {
        Random random_generator = new Random(seed);

        // input random numbers in n-array
        for (int i = 0; i < size; i++) {
            input[i] = random_generator.nextInt(101);
        }
    }

    // public static void sorting_switch(){
    //     //switchs sorting methods 
    //     //when sorting_method is true, it is using heap sort.
    //     //when sorting_method is false, it is using quick sort.
    //     sorting_method = !sorting_method;
    // }

    public static void main(String[] args) throws InterruptedException {
        initialize_array(seed, array, n);

        //use QuickSort and get all snap_shots
        quick = new QuickSort(array.clone());
        System.out.println("Computing quick Sort.");
        start_time = System.nanoTime();
        quick.sort(); // uses quick sort
        end_time = System.nanoTime();
        System.out.println(end_time-start_time);
        System.out.println("Finished quick Sort.");
        all_snap_quick = quick.show_snaps(); //stores all snapshots of original array

        //use HeapSort and get all snap_shots
        heap = new HeapSort(array.clone());
        System.out.println("Computing heap Sort.");
        start_time = System.nanoTime();
        heap.sort(); // uses heap sort
        end_time = System.nanoTime();
        System.out.println(end_time - start_time);
        System.out.println("Finished heap Sort.");
        all_snap_heap = heap.show_snaps(); //stores all snapshots of original array

        // initialize GUI frame and constants
        JFrame frame = new JFrame("heap Sort");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // This left panel stores everything other than the sorting animation
        JPanel panel_left = new JPanel();
        panel_left.setLayout(new BoxLayout(panel_left, BoxLayout.Y_AXIS));

        // add a button the left panel to switch between sorts
        JPanel button_panel = new JPanel();
        button_panel.setLayout(new BoxLayout(button_panel,BoxLayout.Y_AXIS));

        JButton heap_button = new JButton("Heap Sort"); 
        heap_button.setMaximumSize(new Dimension(100, 100));
        heap_button.setAlignmentX(Component.CENTER_ALIGNMENT);
        heap_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Heap Sort");
                sorting_method = "heap";
            }
        });
        button_panel.add(heap_button);
        
        JButton quick_sort = new JButton("Quick Sort"); 
        quick_sort.setMaximumSize(new Dimension(100, 100));
        quick_sort.setAlignmentX(Component.CENTER_ALIGNMENT);
        quick_sort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Quick Sort");
                sorting_method = "quick";
            }
        });
        button_panel.add(quick_sort);


        panel_left.add(button_panel);
        
        // add Text field to the left panel to change problem size
        JPanel text_field_panel = new JPanel();
        JLabel problem_size_label = new JLabel("Enter Problem Size:");
        JTextField problem_size = new JTextField(10);

        problem_size.setAlignmentX(Component.CENTER_ALIGNMENT);
        problem_size.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) throws IllegalArgumentException{
                try{
                    int temp_n = Integer.parseInt(problem_size.getText());
                    if(temp_n > 0){
                        n = temp_n;
                        array = new Integer[n];
                        initialize_array(seed, array, n);
                        
                        quick.clear_history(); 
                        quick.set_array(array.clone());
                        System.out.println("Computing quick Sort.");
                        quick.sort();
                        System.out.println("Finished quick Sort.");
                        all_snap_quick = quick.show_snaps();

                        heap.clear_history();
                        heap.set_array(array.clone());
                        System.out.println("Computing heap Sort.");
                        heap.sort();
                        System.out.println("Finished heap Sort.");
                        all_snap_heap = heap.show_snaps();

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

        //add the left panel to the frame
        frame.add(panel_left,BorderLayout.WEST);

        // initialize drawing panel
        Drawer draw = new Drawer();
        draw.setMaxNum(100);

        //add the drawing panel to the frame. aka: animation
        while(true){

            switch(sorting_method){
                case "heap":
                    for(int i = 0; i < all_snap_heap.size(); i++){
                        if(sorting_method.equals("heap")){
                            frame.repaint();
                            draw.setSnap(all_snap_heap.get(i));
                            frame.add(draw);
                            Thread.sleep(swap_speed);  
                        }else{
                            break;
                        }
                    }
                break;
                case "quick":
                    for(int i = 0; i < all_snap_quick.size(); i++){
                        if(sorting_method.equals("quick")){
                            frame.repaint();
                            draw.setSnap(all_snap_quick.get(i));
                            frame.add(draw,BorderLayout.CENTER);
                            Thread.sleep(swap_speed);
                        }else{
                            break;
                        }
                    } 
                break;
            }
            
        }

    }
}

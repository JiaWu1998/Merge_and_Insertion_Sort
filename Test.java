/* 
Jiaxing Wu 
Seat: 63
A20398273

Yik Yu Lau
Seat 37
A20397022

Samuel Echevarria
Seat 19
A20397415

Kevin Cho
Seat 13
A20393339

*/

import java.util.Random;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Color;

public class Test {
    // initialize timing variables
    static long start_time, end_time, total_time_merge, total_time_insert, total_time_heap, total_time_radix, total_time_quick, total_time_count;

    // initialize variables
    static int seed = 600, n = 100, swap_speed = 30;
    static Integer[] array = new Integer[n];
    static String sorting_method = "heap";
    static ArrayList<ArrayList<Integer>> all_snap_heap;
    static ArrayList<ArrayList<Integer>> all_snap_quick;
    static ArrayList<ArrayList<Integer>> all_snap_merge;
    static ArrayList<ArrayList<Integer>> all_snap_insert;
    static ArrayList<ArrayList<Integer>> all_snap_radix;
    static ArrayList<ArrayList<Integer>> all_snap_count;
    static QuickSort quick;
    static HeapSort heap;
    static MergeSort merge;
    static InsertionSort insert;
    static RadixSort radix;
    static CountingSort count;

    public static void initialize_array(int seed, Integer[] input, int size) {
        Random random_generator = new Random(seed);

        // input random numbers in n-array
        for (int i = 0; i < size; i++) {
            input[i] = random_generator.nextInt(101);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        initialize_array(seed, array, n);

        //use QuickSort and get all snap_shots
        quick = new QuickSort(array.clone());
        System.out.println("Computing quick Sort.");
        start_time = System.nanoTime();
        quick.sort(); // uses quick sort
        end_time = System.nanoTime();
        total_time_quick = end_time - start_time;
        System.out.println(total_time_quick);
        System.out.println("Finished quick Sort.");
        all_snap_quick = quick.show_snaps(); //stores all snapshots of original array

        //use HeapSort and get all snap_shots
        heap = new HeapSort(array.clone());
        System.out.println("Computing heap Sort.");
        start_time = System.nanoTime();
        heap.sort(); // uses heap sort
        end_time = System.nanoTime();
        total_time_heap = end_time - start_time;
        System.out.println(total_time_heap);
        System.out.println("Finished heap Sort.");
        all_snap_heap = heap.show_snaps(); //stores all snapshots of original array

        merge = new MergeSort(array.clone());
        System.out.println("Computing merge Sort.");
        start_time = System.nanoTime();
        merge.sort();
        end_time = System.nanoTime();
        total_time_merge = end_time - start_time;
        System.out.println(total_time_merge);
        System.out.println("Finished merge Sort");
        all_snap_merge = merge.show_snaps();

        insert = new InsertionSort(array.clone());
        System.out.println("Computing insertion Sort.");
        start_time = System.nanoTime();
        insert.sort();
        end_time = System.nanoTime();
        total_time_insert = end_time - start_time;
        System.out.println(total_time_insert);
        System.out.println("Finished insertion Sort");
        all_snap_insert = insert.show_snaps();

        radix = new RadixSort(array.clone());
        System.out.println("Computing radix Sort.");
        start_time = System.nanoTime();
        radix.sort();
        end_time = System.nanoTime();
        total_time_radix = end_time - start_time;
        System.out.println(total_time_radix);
        System.out.println("Finished radix Sort");
        all_snap_radix = radix.show_snaps();

        count = new CountingSort(array.clone());
        System.out.println("Computing counting Sort.");
        start_time = System.nanoTime();
        count.sort();
        end_time = System.nanoTime();
        total_time_count = end_time - start_time;
        System.out.println(total_time_count);
        System.out.println("Finished counting Sort");
        all_snap_count = count.show_snaps();

        // initialize GUI frame and constants
        JFrame frame = new JFrame("heap Sort");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // This left panel stores everything other than the sorting animation
        JPanel panel_left = new JPanel();
        panel_left.setLayout(new BoxLayout(panel_left, BoxLayout.Y_AXIS));
        panel_left.setBorder(new LineBorder(new Color(0,0,0), 2, true));
        panel_left.setBackground(Color.WHITE);

        // add a button the left panel to switch between sorts
        JPanel button_panel = new JPanel();
        button_panel.setLayout(new BoxLayout(button_panel,BoxLayout.Y_AXIS));
        button_panel.setBackground(Color.white);

        JButton heap_button = new JButton("Heap Sort"); 
        heap_button.setMaximumSize(new Dimension(150, 100));
        heap_button.setAlignmentX(Component.CENTER_ALIGNMENT);
        heap_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Heap Sort");
                sorting_method = "heap";
            }
        });
        heap_button.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
        heap_button.setBackground(Color.WHITE);
        button_panel.add(heap_button);
        
        JButton quick_sort = new JButton("Quick Sort"); 
        quick_sort.setMaximumSize(new Dimension(150, 100));
        quick_sort.setAlignmentX(Component.CENTER_ALIGNMENT);
        quick_sort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Quick Sort");
                sorting_method = "quick";
            }
        });
        quick_sort.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
        quick_sort.setBackground(Color.WHITE);
        button_panel.add(quick_sort);

        JButton merge_sort = new JButton("Merge Sort"); 
        merge_sort.setMaximumSize(new Dimension(150, 100));
        merge_sort.setAlignmentX(Component.CENTER_ALIGNMENT);
        merge_sort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Merge Sort");
                sorting_method = "merge";
            }
        });
        merge_sort.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
        merge_sort.setBackground(Color.WHITE);
        button_panel.add(merge_sort);

        JButton insertion_sort = new JButton("Insertion Sort"); 
        insertion_sort.setMaximumSize(new Dimension(150, 100));
        insertion_sort.setAlignmentX(Component.CENTER_ALIGNMENT);
        insertion_sort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Insertion Sort");
                sorting_method = "insert";
            }
        });
        insertion_sort.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
        insertion_sort.setBackground(Color.WHITE);
        button_panel.add(insertion_sort);

        JButton radix_sort = new JButton("Radix Sort"); 
        radix_sort.setMaximumSize(new Dimension(150, 100));
        radix_sort.setAlignmentX(Component.CENTER_ALIGNMENT);
        radix_sort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Radix Sort");
                sorting_method = "radix";
            }
        });
        radix_sort.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
        radix_sort.setBackground(Color.WHITE);
        button_panel.add(radix_sort);

        JButton counting_sort = new JButton("Counting Sort"); 
        counting_sort.setMaximumSize(new Dimension(150, 100));
        counting_sort.setAlignmentX(Component.CENTER_ALIGNMENT);
        counting_sort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Counting Sort");
                sorting_method = "count";
            }
        });
        counting_sort.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
        counting_sort.setBackground(Color.WHITE);
        button_panel.add(counting_sort);

        panel_left.add(button_panel);
        
        // add Text field to the left panel to change problem size
        JPanel text_field_panel = new JPanel();
        text_field_panel.setBackground(Color.WHITE);
        JLabel problem_size_label = new JLabel("Enter Problem Size:");
        problem_size_label.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
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
                        start_time = System.nanoTime();
                        quick.sort();
                        end_time = System.nanoTime();
                        total_time_quick = end_time - start_time;
                        System.out.println(total_time_quick);
                        System.out.println("Finished quick Sort.");
                        all_snap_quick = quick.show_snaps();

                        heap.clear_history();
                        heap.set_array(array.clone());
                        System.out.println("Computing heap Sort.");
                        start_time = System.nanoTime();
                        heap.sort();
                        end_time = System.nanoTime();
                        total_time_heap = end_time - start_time;
                        System.out.println(total_time_heap);
                        System.out.println("Finished heap Sort.");
                        all_snap_heap = heap.show_snaps();

                        merge.clear_history();
                        merge.set_array(array.clone());
                        System.out.println("Computing merge Sort.");
                        start_time = System.nanoTime();
                        merge.sort();
                        end_time = System.nanoTime();
                        total_time_merge = end_time - start_time;
                        System.out.println(total_time_merge);
                        System.out.println("Finished merge Sort.");
                        all_snap_merge = merge.show_snaps();

                        insert.clear_history();
                        insert.set_array(array.clone());
                        System.out.println("Computing insert Sort.");
                        start_time = System.nanoTime();
                        insert.sort();
                        end_time = System.nanoTime();
                        total_time_insert = end_time - start_time;
                        System.out.println(total_time_insert);
                        System.out.println("Finished insert Sort.");
                        all_snap_insert = insert.show_snaps();

                        radix.clear_history();
                        radix.set_array(array.clone());
                        System.out.println("Computing radix Sort.");
                        start_time = System.nanoTime();
                        radix.sort();
                        end_time = System.nanoTime();
                        total_time_radix = end_time - start_time;
                        System.out.println(total_time_radix);
                        System.out.println("Finished radix Sort.");
                        all_snap_radix = radix.show_snaps();

                        count.clear_history();
                        count.set_array(array.clone());
                        System.out.println("Computing counting Sort.");
                        start_time = System.nanoTime();
                        count.sort();
                        end_time = System.nanoTime();
                        total_time_count = end_time - start_time;
                        System.out.println(total_time_count);
                        System.out.println("Finished counting Sort.");
                        all_snap_count = count.show_snaps();

                    }else{
                        System.out.print("Invalid problem size");
                    }
                }catch(IllegalArgumentException a){
                    System.out.print("Invalid problem size");
                    JOptionPane.showMessageDialog(null, "Try a valid size");
                }
            }
        });
        text_field_panel.add(problem_size_label);
        text_field_panel.add(problem_size);
        panel_left.add(text_field_panel);

        JPanel lblPanel = new JPanel();
        lblPanel.setBackground(Color.WHITE);
        lblPanel.setLayout(new BoxLayout(lblPanel,BoxLayout.Y_AXIS));
        JLabel timeHeap = new JLabel("Heap Sort Time: " + total_time_heap);
        timeHeap.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        JLabel timeQuick = new JLabel("Quick Sort Time: " + total_time_quick);
        timeQuick.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        JLabel timeMerge = new JLabel("Merge Sort Time: " + total_time_merge);
        timeMerge.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        JLabel timeInsert = new JLabel("Insertion Sort Time: " +total_time_insert);
        timeInsert.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        JLabel timeRadix = new JLabel("Radix Sort Time: " + total_time_radix);
        timeRadix.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        JLabel timeCount = new JLabel("Counting Sort Time: "+total_time_count);
        timeCount.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        lblPanel.add(timeHeap);
        lblPanel.add(timeQuick);
        lblPanel.add(timeMerge);
        lblPanel.add(timeInsert);
        lblPanel.add(timeRadix);
        lblPanel.add(timeCount);
        panel_left.add(lblPanel); 

        //add the left panel to the frame
        frame.add(panel_left,BorderLayout.WEST);

        // initialize drawing panel
        Drawer draw = new Drawer();
        draw.setMaxNum(100);
        draw.setBackground(Color.GRAY);

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
                case "merge":
                    for(int i = 0; i < all_snap_merge.size(); i++){
                        if(sorting_method.equals("merge")){
                            frame.repaint();
                            draw.setSnap(all_snap_merge.get(i));
                            frame.add(draw,BorderLayout.CENTER);
                            Thread.sleep(swap_speed);
                        }else{
                            break;
                        }
                    } 
                break;
                case "insert":
                    for(int i = 0; i < all_snap_insert.size(); i++){
                        if(sorting_method.equals("insert")){
                            frame.repaint();
                            draw.setSnap(all_snap_insert.get(i));
                            frame.add(draw,BorderLayout.CENTER);
                            Thread.sleep(swap_speed);
                        }else{
                            break;
                        }
                    } 
                break;
                case "radix":
                    for(int i = 0; i < all_snap_radix.size(); i++){
                        if(sorting_method.equals("radix")){
                            frame.repaint();
                            draw.setSnap(all_snap_radix.get(i));
                            frame.add(draw,BorderLayout.CENTER);
                            Thread.sleep(swap_speed);
                        }else{
                            break;
                        }
                    } 
                break;
                case "count":
                    for(int i = 0; i < all_snap_count.size(); i++){
                        if(sorting_method.equals("count")){
                            frame.repaint();
                            draw.setSnap(all_snap_count.get(i));
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

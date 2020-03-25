import java.util.ArrayList;
import java.util.Arrays;

public class CountingSort{
    // COUNTING SORT RESTRICTION: CAN ONLY SORT FROM 0 to N 

    public static Integer[] array; 
    public ArrayList<ArrayList<Integer>> snap_shots = new ArrayList<ArrayList<Integer>>();

    public CountingSort(Integer[] input_array){
        array = input_array;
    }

    public void set_array(Integer[] input_array){
        array = input_array;
    }

    public Integer[] get_array(){
        return array;
    }

    public ArrayList<ArrayList<Integer>> show_snaps(){
        return snap_shots;
    }

    public void clear_history(){
        snap_shots = new ArrayList<ArrayList<Integer>>();
    }

    public void sort(){
        Integer[] temp = array;
    
        int max_range = 0;
        for(int i=0; i<array.length; i++){
            if (array[i] > max_range){
                max_range = array[i];
            }
            if (array[i] < 0){
                System.out.print("Counting Sort Restriction: You cannot have a negative number.");
                return;
            }            
        }

        Integer[] index = new Integer[max_range];

        for(int i=0; i<index.length; i++){
            index[i] = 0;
        } 

        for(int i=0; i<array.length; i++){
            index[array[i]] += 1;
        }

        for(int i=1; i<index.length; i++){
            index[i] += index[i-1];
        }

        for(int i=0; i<array.length; i++){
            temp[index[array[i]]] = array[i];
            index[array[i]] -= 1;
        }

        array = temp;
    }
}